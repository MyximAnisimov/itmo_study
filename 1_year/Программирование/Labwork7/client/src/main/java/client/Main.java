package client;

import client.commands.Login;
import client.utility.Runner;
import common.utility.CustomConsole;
import common.utility.UserIDNumber;
import common.utility.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;
import java.net.InetAddress;

public class Main {

    private static final int port = 11111;
    public static void main(String[] args) {

        try {
            UserIDNumber UIN = new UserIDNumber(0);

            var client = new UDPclient(InetAddress.getLocalHost(), port);
            var runner = new Runner(client);
            runner.interactiveMode();
            UserIDNumber.id.setId(Response.ID.countable);
                if(UserIDNumber.id.getID()==1){
                  runner.interactiveMode2();}
                else{
                    runner.interactiveMode3();
                }
        } catch (IOException e) {
            CustomConsole.printLn("Невозможно подключиться к серверу.");
        }
    }}