package com.example.chattools;

import javafx.beans.property.SimpleStringProperty;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketReceiver extends Thread{
    private ServerSocket serverSocket=null;
    private BufferedReader br=null;
    private Socket server=null;

    public SimpleStringProperty message = new SimpleStringProperty();

    public void run() {
        ChatModel data=ChatModel.getData();
        System.out.println("Socket Thread-");

        try {
            serverSocket=new ServerSocket(Integer.parseInt(ChatModel.getData().getLPort()));
            server=serverSocket.accept();
            br=new BufferedReader(new InputStreamReader(server.getInputStream()));
            System.out.println("客户端:"+server.getInetAddress().getLocalHost()+"已连接到服务器");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while(true){
            System.out.println("listening");
            try {
                data.receive_buf= br.readLine();
                message.set(data.receive_buf);
                System.out.println(data.receive_buf);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
