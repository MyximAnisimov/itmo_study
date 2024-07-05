package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Main {
    public static void main(String[] args) {
        byte arr[]=new byte[10];
        int len = arr.length;
        DatagramSocket ds;
        DatagramPacket dp;
        InetAddress host;
        int port = 6789;
        try {
            ds = new DatagramSocket(port);
            dp = new DatagramPacket(arr, len);
            ds.receive(dp);
            for (int j = 0; j < len; j++) {
                arr[j] *= 2;
            }
            host = dp.getAddress();
            port = dp.getPort();
            dp = new DatagramPacket(arr, len, host, port);
            ds.send(dp);
        }
        catch(SocketException e){
            System.out.println("Ошибка!");
        }
        catch(IOException e){
            System.out.println("Ошибка!");
        }
    }
}