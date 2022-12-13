package com.example.chattools;

public class ChatModel {
    private static final ChatModel data=new ChatModel();
    private ChatModel(){};

    public static ChatModel getData(){
        return data;
    }

    private String local_port=null;
    private String obj_IP=null;
    private String obj_port=null;
    public String send_buf=null;
    public String receive_buf=null;

    public void setIP(String ip){obj_IP=ip;}
    public void setPort(String port){obj_port=port;}
    public void setLPort(String port){local_port=port;}
    public String getIP(){return obj_IP;}
    public String getPort(){return obj_port;}

    public String getLPort(){return local_port;}


}
