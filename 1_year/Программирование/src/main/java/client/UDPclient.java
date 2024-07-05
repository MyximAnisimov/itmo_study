package client;

import client.utility.UserHandler;
import common.exceptions.ConnectionErrorException;
import common.exceptions.OutOfLimitsException;
import common.utility.Request;
import common.utility.Response;
import org.gradle.internal.impldep.com.google.common.primitives.Bytes;
import org.gradle.internal.impldep.org.apache.commons.lang.ArrayUtils;
import org.gradle.internal.impldep.org.apache.commons.lang.SerializationUtils;

import common.utility.CustomConsole;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;

public class UDPclient {
        private final String host;
        private final int port;
        private byte[] data;
        private final int MAX_SIZE = 1024;
        private final int reconnectionTimeout;
        private int reconnectionAttempts;
        private final int maxReconnectionAttempts;
        private DatagramChannel client;
        private final InetSocketAddress addr;
        private CustomConsole customConsole;
        private final UserHandler userHandler;
        private int serverWriter;
        private int serverReader;

        public UDPclient(InetAddress address, String host, int port, int reconnectionTimeout, int maxReconnectionAttempts, UserHandler userHandler) {
            this.host = host;
            this.addr = new InetSocketAddress(address, port);
            this.port = port;
            this.reconnectionTimeout = reconnectionTimeout;
            this.maxReconnectionAttempts = maxReconnectionAttempts;
            this.userHandler = userHandler;
        }

        /**
         * Begins client operation.
         */
        public void run() {
            try {
                boolean processingStatus = true;
                while (processingStatus) {
                    try {
                        connectToServer();
                        processingStatus = processRequestToServer();
                    }
                    catch (OutOfLimitsException e){
                        System.out.println("Ошибка");
                    }
                    catch (ConnectionErrorException exception) {
                        if (reconnectionAttempts >= maxReconnectionAttempts) {
                            CustomConsole.printError("Exceeded the number of connection attempts!");
                            break;
                        }

                        try {
                            Thread.sleep(reconnectionTimeout);
                        } catch (IllegalArgumentException timeoutException) {
                            CustomConsole.printError("Connection waiting time'" + reconnectionTimeout +
                                    "' is beyond the limits of possible values!");
                            CustomConsole.printLn("Reconnection will be performed immediately.");
                        } catch (Exception timeoutException) {
                            CustomConsole.printError("An error occurred while trying to wait for connection!");
                            CustomConsole.printLn("Reconnection will be performed immediately.");
                        }
                    }
                    reconnectionAttempts++;
                }
                if (client != null) client.close();
                CustomConsole.printLn("The client's work has been successfully completed.");
            } catch (IOException exception) {
                CustomConsole.printError("An error occurred while trying to terminate the connection with the server!");
            }
        }

        /**
         * Connecting to server.
         */
        private void connectToServer() throws ConnectionErrorException, OutOfLimitsException {
            try {
                if (reconnectionAttempts >= 1) CustomConsole.printLn("Reconnecting to the server...");
                client = client.open().bind(null).connect(addr);
                CustomConsole.printLn("The connection to the server has been successfully established.");
                CustomConsole.printLn("Waiting for permission to exchange data...");

                CustomConsole.printLn("Permission to exchange data has been received.");}
             catch (IllegalArgumentException exception) {
                CustomConsole.printError("The server address is entered incorrectly!");
                throw new OutOfLimitsException();
            } catch (IOException exception) {
                CustomConsole.printError("An error occurred while connecting to the server!");
                throw new ConnectionErrorException();
            }
        }

        /**
         * Server request process.
         */
        private boolean processRequestToServer() {
            Request requestToServer = null;
            Response serverResponse = null;
            do {
                try {
                    requestToServer = serverResponse != null ? userHandler.handle(serverResponse.responseResult()) :
                            userHandler.handle(null);
                    serverResponse = this.sendAndReceiveCommand(new Request());
                    CustomConsole.print(serverResponse.getResponseBody());
                } catch (InvalidClassException | NotSerializableException exception) {
                    CustomConsole.printError("An error occurred while sending data to the server!");
                    CustomConsole.printError(exception);
                    CustomConsole.printError(serverReader);
                } catch (IOException exception) {
                    CustomConsole.printError("The connection to the server is broken!");
                    try {
                        reconnectionAttempts++;
                        connectToServer();
                    } catch (ConnectionErrorException | OutOfLimitsException reconnectionException) {
                        if (requestToServer.getCommandName().equals("exit"))
                            CustomConsole.printLn("The command will not be registered on the server.");
                        else CustomConsole.printLn("Try to repeat the command later.");
                    }
                }
            } while (!requestToServer.getCommandName().equals("exit"));
            return false;
        }

        public Response sendAndReceiveCommand(Request request) throws IOException {
            var data = SerializationUtils.serialize(request);
            var responseBytes = sendAndReceiveData(data);

            Response response = (Response) SerializationUtils.deserialize(responseBytes);
            CustomConsole.printLn("Получен ответ от сервера:  " + response);
            return response;
        }

        private void sendData(byte[] data) throws IOException {
            byte[][] ret = new byte[(int)Math.ceil(data.length / (double)MAX_SIZE)][MAX_SIZE];

            int start = 0;
            for(int i = 0; i < ret.length; i++) {
                ret[i] = Arrays.copyOfRange(data, start, start + MAX_SIZE);
                start += MAX_SIZE;
            }

            CustomConsole.printLn("Отправляется " + ret.length + " чанков...");

            for(int i = 0; i < ret.length; i++) {
                var chunk = ret[i];
                if (i == ret.length - 1) {
                    var lastChunk = Bytes.concat(chunk, new byte[]{1});
                    client.send(ByteBuffer.wrap(lastChunk), addr);
                    CustomConsole.printLn("Последний чанк размером " + lastChunk.length + " отправлен на сервер.");
                } else {
                    var answer = Bytes.concat(chunk, new byte[]{0});
                    client.send(ByteBuffer.wrap(answer), addr);
                    CustomConsole.printLn("Чанк размером " + answer.length + " отправлен на сервер.");
                }
            }

            CustomConsole.printLn("Отправка данных завершена.");
        }

        private byte[] receiveData() throws IOException {
            var received = false;
            var result = new byte[0];

            while(!received) {
                var data = receiveData(MAX_SIZE);
                CustomConsole.printLn("Получено \"" + new String(data) + "\"");
                CustomConsole.printLn("Последний байт: " + data[data.length - 1]);

                if (data[data.length - 1] == 1) {
                    received = true;
                    CustomConsole.printLn("Получение данных окончено");
                }
                result = Bytes.concat(result, Arrays.copyOf(data, data.length - 1));
            }

            return result;
        }

        private byte[] receiveData(int bufferSize) throws IOException {
            var buffer = ByteBuffer.allocate(bufferSize);
            SocketAddress address = null;
            while(address == null) {
                address = client.receive(buffer);
            }
            return buffer.array();
        }

        private byte[] sendAndReceiveData(byte[] data) throws IOException {
            sendData(data);
            return receiveData();
        }
    }
