package com.example.chattools;

import java.io.*;
import java.net.Socket;

public class SocketSender {
    private BufferedWriter bw=null;
    private Socket client=null;

    public void getConnected() throws Exception {
        ChatModel data=ChatModel.getData();
        client=new Socket(data.getIP(),Integer.parseInt(data.getPort()));
        System.out.println("客户端:"+client.getInetAddress().getLocalHost()+"已连接到服务器");

        bw=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
    }

    public void sendMsg(){
        ChatModel data=ChatModel.getData();
        try {
            bw.write(data.send_buf);
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
