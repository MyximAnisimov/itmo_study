package client.clientUDP;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class UDPclient {
    private DatagramSocket socket;
    private InetAddress InetAddress;
    private int Port;
    private final int MAX_PACKET_SIZE = 1024;
    private byte[] Buffer = new byte[MAX_PACKET_SIZE];
    private BlockingQueue<byte []> Queue = new LinkedBlockingQueue<>();

    private int [] timeouts = {11, 29, 73, 277, 997};

    public UDPclient(InetAddress InetAddress, int Port) throws IOException {
this.InetAddress=InetAddress;
    }
    public InetAddress getInetSocketAddress(){
        return InetAddress;
    }
public void run(){
    System.out.println("Client starts");
    while(true){
        try{
byte [] message=Queue.take();
            DatagramPacket packetToServer = new DatagramPacket(message, message.length, InetAddress,Port);
            for(int i=0; i< timeouts.length; i++){
                socket.setSoTimeout(timeouts[i]);
                socket.send(packetToServer);
                DatagramPacket packetFromServer = new DatagramPacket(Buffer, Buffer.length);
                System.out.println(new String(packetFromServer.getData()));
            }break;
        }
        catch(InterruptedException e){
            System.out.println("Ошибка!");
        }
        catch(SocketException e){
            System.out.println("Ошибка!");
        }
        catch (IOException e){
            System.out.println("Ошибка!");
        }
}

}}
