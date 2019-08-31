package com.company.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientGUI extends Frame implements ActionListener {
    private TextField tf1,tf2;
    private Label l1,l2;
    private TextArea textArea ;
    private int DEFAULT_HEIGHT = 20;
    private Client client;
    Button b1;
    public ClientGUI(Client client){
        this.client = client;
        l1 = new Label();
        l1.setText("Weight in pounds");
        l1.setBounds(20,50,100,20);
        tf1=new TextField();
        tf1.setBounds(l1.getX() + l1.getWidth(),l1.getY(),60,20);
        l2= new Label();
        l2.setText("height in inches");
        l2.setBounds(20,70,100,20);
        tf2=new TextField();
        tf2.setBounds(l2.getX() + l2.getWidth(),l2.getY(),60,20);
        b1=new Button("Submit");
        b1.setBounds(tf2.getX() + tf2.getWidth(),tf2.getY(),50,20);
        b1.addActionListener(this);
        textArea = new TextArea();
        textArea.setBounds(20, 90,280,210);
        add(tf1);add(tf2);add(b1);
        add(l1);
        add(l2);
        add(textArea);
        setSize(300,300);
        setLayout(null);
        setVisible(true);
        setTitle("Exercise33_01Client");

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            client.connectToServer(Double.parseDouble(tf1.getText()), Double.parseDouble(tf2.getText()));
        }
        catch(NumberFormatException nfe){
            appendText("Invalid input");
        }
    }

    void appendText(String text){
        textArea.appendText(text);
        textArea.append("\n");
    }
}
