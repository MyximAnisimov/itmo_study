package server;

import common.utility.CustomConsole;
import common.utility.Request;
import common.utility.Response;
import com.google.common.primitives.Bytes;
import org.gradle.internal.impldep.org.antlr.v4.runtime.misc.Pair;
import org.gradle.internal.impldep.org.apache.commons.lang.ArrayUtils;
import org.gradle.internal.impldep.org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.net.*;

public class UDPserver {
    public int port = 1234;
    private final int MAX_SIZE = 1024;
    private byte[] buffer;
    DatagramSocket datagramSocket;
    DatagramPacket datagramPacket;
    private InetAddress host;
    private CommandExecutor commandExecutor;
    public UDPserver(InetAddress host, int port,CommandExecutor commandExecutor){
        new InetSocketAddress(host,port);
        this.commandExecutor = commandExecutor;
    }
    public Pair<Byte[], SocketAddress> receiveData() throws IOException{

            var received = false;
            var result = new byte[0];
            SocketAddress addr = null;
            while(!received){
                buffer = new byte[MAX_SIZE];
                datagramPacket = new DatagramPacket(buffer,MAX_SIZE);
                datagramSocket.receive(datagramPacket);
                if (buffer[buffer.length - 1] == 1) {
                    received = true;
                    CustomConsole.printLn("Получение данных от " + dp.getAddress() + " окончено");
                }
                result = Bytes.concat(result, Arrays.copyOf(data, data.length - 1));
            }
        return new ImmutablePair<>(ArrayUtils.toObject(result), addr);
    }
            }
            return ArrayUtils.toObject(result);}
    public void run(){
        System.out.println("Сервак запущен");
        Request request;
        try {
            host = InetAddress.getLocalHost();
            datagramSocket = new DatagramSocket(port);
            var dataFromClient = receiveData();
            connectToClient(datagramPacket.getSocketAddress());

            datagramSocket.send(datagramPacket);
            request = (Request) SerializationUtils.deserialize(ArrayUtils.toPrimitive(dataFromClient));

            Response response = commandExecutor.executeCommand(request);
            var data = SerializationUtils.serialize(response);

            sendData(data, datagramPacket.getSocketAddress());
            CustomConsole.printLn("Отправлен ответ клиенту " + datagramPacket.getSocketAddress());
        }
        catch(UnknownHostException e){
            System.out.println("Ошибка хоста!");
        }
        catch(IOException e){
            System.out.println("Ошибка receive");
        }


}
    public void connectToClient(SocketAddress socketAddress)throws SocketException {
        datagramSocket.connect(socketAddress);
    }
    public void disconnectFromClient(){
        datagramSocket.disconnect();
    }
    public void sendData(byte[] data, SocketAddress addr) throws IOException{
        datagramSocket.send(datagramPacket);
    }
}
