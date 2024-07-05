package server;

import common.utility.CustomConsole;
import common.utility.VisibilityArgument;
import common.utility.requests.Request;
import common.utility.response.Response;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public abstract class UDPserver {
    private final InetSocketAddress addr;
    private final CommandExecutor commandExecutor;
    private Runnable afterHook;
    private DBhandler dbhandler;
    private final Logger logger = Main.logger;
    private boolean running = true;
    private static final int READ_POOL_SIZE=4;
    private final ForkJoinPool service;
    private VisibilityArgument VA;
    private Collection<Pair<Byte[], SocketAddress>> synchColl = Collections.synchronizedList(new ArrayList<>());
    private final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private final ArrayBlockingQueue synchColl1 = new ArrayBlockingQueue<Pair<ArrayList<Byte[]>, SocketAddress>>(10000);
    private final ArrayBlockingQueue synchColl2 = new ArrayBlockingQueue<Pair<Response, SocketAddress>>(10000);

    public UDPserver(InetSocketAddress addr, CommandExecutor commandExecutor) {
        this.addr = addr;
        this.commandExecutor = commandExecutor;
        this.service= new ForkJoinPool();
    }

    public InetSocketAddress getAddr() {
        return addr;
    }

    /**
     * Получает данные с клиента.
     * Возвращает пару из данных и адреса клиента
     */
    public abstract Pair<Byte[], SocketAddress> receiveData() throws IOException;

    /**
     * Отправляет данные клиенту
     */
    public abstract void sendData(byte[] data, SocketAddress addr) throws IOException;

    public abstract void connectToClient(SocketAddress addr) throws SocketException;

    public abstract void disconnectFromClient();
    public abstract void close();


    public void run(){
        System.out.println("Сервак запущен");
        while (running) {
            new ForkJoinPool().execute(()->{
                while (true) {
                    try {
                        synchColl1.add(receiveData());
                    } catch (Exception e) {
                        CustomConsole.printLn("Ошибка получения данных");
                        disconnectFromClient();
                        continue;
                    }
                }
            });
new Thread(()-> {
            var dataFromClient =(Pair) synchColl1.poll();
            var getData = (Byte) dataFromClient.getKey();

            var clientAddr = (Pair) synchColl1.poll();
            var getAddr =(SocketAddress) clientAddr.getValue();

            try {
                connectToClient(getAddr);
                CustomConsole.printLn("Соединено с " + clientAddr);
            } catch (Exception e) {
                CustomConsole.printLn("Ошибка соединения с клиентом"+ e.toString());
            }

            Request request=null;
            try {
                request = SerializationUtils.deserialize((byte[]) ArrayUtils.toPrimitive(getData));
                CustomConsole.printLn("Обработка " + request + " из " + clientAddr);
            }
            catch (SerializationException e){
                CustomConsole.printError("Невозможно десериализовать объект");
                disconnectFromClient();
            }
            Response response = null;
    assert request != null;
    response= commandExecutor.handle(request);
                synchColl2.add(new ImmutablePair<>(response, getAddr));});

Executors.newCachedThreadPool().execute(()->{
    var pair = (ImmutablePair<Response, SocketAddress>) synchColl2.poll();
    assert pair != null;
    var response = pair.getKey();
    var getAddr = pair.getRight();
    var data = SerializationUtils.serialize(response);
    CustomConsole.printLn("Ответ: " + response);

            try {
                sendData(data, getAddr);
                CustomConsole.printLn("Отправлен ответ клиенту ");
            } catch (Exception e) {
                CustomConsole.printLn("Ошибка ввода-вывода");
            }


            disconnectFromClient();
            CustomConsole.printLn("Отключение от клиента ");
        close();});}

  }}


