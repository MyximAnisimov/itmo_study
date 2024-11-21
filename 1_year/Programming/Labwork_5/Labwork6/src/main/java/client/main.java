package client;

import client.clientUDP.UDPclient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class main {
    public static void main (String [] args){
        try{
        UDPclient client = new UDPclient(InetAddress.getLocalHost(), 1234);
        client.run();
    }
       catch(
    UnknownHostException e){
        System.out.println("Ошибка Host!");
    }
       catch(
    IOException e){
        System.out.println("Ошибка IO!");
    }
    }
}
