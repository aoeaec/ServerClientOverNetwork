package com.company;

import com.company.client.Client;
import com.company.server.Server;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.startServer();
        main.startClient();
    }


    private void startServer() {
        int port = Integer.parseInt("4040");
        try {
            Thread t = new Server(port);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startClient() {
        new Client();
    }
}
