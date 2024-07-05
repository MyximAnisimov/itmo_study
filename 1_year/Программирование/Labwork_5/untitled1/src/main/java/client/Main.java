package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Main {
    public static void main(String [] args){
        byte arr[] = {0,1,2,3,4,5,6};
        int len = arr.length;
        DatagramSocket ds;
        DatagramPacket dp;
        InetAddress host;
        int port;
        try {
            ds = new DatagramSocket();
            host = InetAddress.getLocalHost();
            port = 6789;
            dp = new DatagramPacket(arr, len, host, port);
ds.send(dp);
            dp = new DatagramPacket(arr,len);
            ds.receive(dp);
            for(byte j : arr){
                System.out.println(j);
            }
        }
        catch(SocketException e){
            System.out.println("Ошибка!");
        }
        catch(IOException e){
            System.out.println("Ошибка!");
        }
    }
}
