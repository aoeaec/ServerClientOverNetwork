package com.company.server;


import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ServerGUI extends Frame {

    private TextArea textArea ;


    ServerGUI(String serverName) {
        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setBounds(50,50, 420,420);
        add (textArea);
        setSize(500, 500);
        setLayout(null);
        setTitle(serverName);
        setVisible(true);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
    void appendText(String text){
        textArea.appendText(text);
        textArea.append("\n");
    }
}