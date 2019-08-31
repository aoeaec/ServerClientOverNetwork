package com.company.client;

import com.company.object.BmiObject;

import java.net.*;
import java.io.*;

public class Client {
    private final String SERVERNAME = "localhost";
    private final int PORT = 4040;
    private Socket client ;
    ClientGUI clientGUI;
    String serverResponse;


    public Client(){

        clientGUI = new ClientGUI(this);
    }

    public void connectToServer(double weight, double height){

        try {
            client = new Socket(SERVERNAME, PORT);
            OutputStream outToServer = client.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(outToServer);

            out.writeObject(new BmiObject(weight, height));
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);

            serverResponse = in.readUTF();
            System.out.println("Server says " + serverResponse);
            clientGUI.appendText(serverResponse);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Client();
    }
}