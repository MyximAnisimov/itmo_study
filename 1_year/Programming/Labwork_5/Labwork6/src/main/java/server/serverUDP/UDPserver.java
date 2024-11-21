package server.serverUDP;

import common.request.Request;
import org.gradle.internal.impldep.com.amazonaws.Response;
import org.gradle.internal.impldep.org.apache.commons.lang.ArrayUtils;
import org.gradle.internal.impldep.org.apache.commons.lang.SerializationUtils;
import server.managers.CommandExecuteManager;

import java.io.IOException;
import java.net.*;

public class UDPserver {
    private final int MAX_PACKET_SIZE = 1024;
private CommandExecuteManager CEM;
private byte[] data;
InetSocketAddress inetSocketAddress;
private int port;
    public UDPserver(CommandExecuteManager CEM, int port) throws IOException {
        this.inetSocketAddress = new InetSocketAddress(port);
        this.CEM=CEM;
    }

    public void run(){
        System.out.println("Сервер запущен!");
        while(true){
            Request request;
            Response response;
                request =(Request) SerializationUtils.deserialize(data);
                CEM.commandExecute(request);

        }
    }
}
