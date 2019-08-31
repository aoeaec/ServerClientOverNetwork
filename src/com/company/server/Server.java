package com.company.server;// File Name GreetingServer.java
import com.company.object.BmiObject;

import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server extends Thread {
    private ServerSocket serverSocket;
    private SimpleDateFormat sdf = new SimpleDateFormat("E MMM HH:mm:ss z yyyy");
    private final String SERVER_NAME = "Exercise33_01Server";

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(0);
    }

    public void run() {
        ServerGUI serverGUI = new ServerGUI(SERVER_NAME);
        serverGUI.appendText(SERVER_NAME + " started at " + sdf.format(new Date()));
        StringBuilder serverString ;
        while(true) {
            try {
                serverString = new StringBuilder();

                Socket server = serverSocket.accept();
                server.setKeepAlive(true);

                ObjectInputStream in = new ObjectInputStream(server.getInputStream());

                BmiObject o = (BmiObject)in.readObject();


                serverGUI.appendText("Connected to a client at " + sdf.format(new Date()));
                serverString.append("Weight: ").append(String.valueOf(o.getWeight())).append("\n");
                serverString.append("Height: ").append(String.valueOf(o.getHeight())).append("\n");
                serverString.append(getBMIString(o.getWeight(), o.getHeight())).append("\n");

                serverGUI.appendText(serverString.toString());

                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF(serverString.toString());


            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    private String getBMIString(double weight, double height){
        StringBuilder returnString = new StringBuilder();
        int scale = (int) Math.pow(10, 2);
        double bmi =  (double) Math.ceil(weight/(height*height)*703*scale)/scale;
        returnString.append("BMI is ").append(bmi).append(".");
        if (bmi >= 40) {
            returnString.append( "Serious obesity");
        } else if (bmi >= 30) {
            returnString.append(  "Obesity");
        } else if (bmi >= 25) {
            returnString.append(  "Overweight");
        } else if (bmi >= 18) {
            returnString.append(  "Normal");
        } else {
            returnString.append(  "Underweight");
        }
        return returnString.toString();
    }


    public static void main(String[] args) {
            int port = Integer.parseInt("4040");
            try {
                Thread t = new Server(port);
                t.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}