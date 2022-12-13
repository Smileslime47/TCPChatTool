package com.example.chattools;

import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;

public class WindowController{
    public SocketReceiver Receiver;
    public SocketSender Sender;

    @FXML
    public TextArea sendWin;
    @FXML
    public TextArea receiveWin;
    @FXML
    public TextField local_port;
    @FXML
    private TextField obj_IP;
    @FXML
    private TextField obj_port;
    @FXML
    private TextArea status;

    private void updateReceiveWin(Observable observable) {
        receiveWin.appendText(LocalDate.now()+":"+Receiver.message.get()+'\n');
    }

    @FXML
    public void linkClick(ActionEvent actionEvent) {
        ChatModel data=ChatModel.getData();
        data.setIP(obj_IP.getText());
        data.setPort(obj_port.getText());

        Sender=new SocketSender();
        status.setText(status.getText()+'\n'+"IP:"+data.getIP());
        status.setText(status.getText()+'\n'+"Bind Port:"+data.getPort());
        try{
            Sender.getConnected();
            status.setText(status.getText()+'\n'+"Connected Success");
        } catch (Exception e) {
            status.setText(status.getText()+'\n'+"Connected Error");
        }
    }
    @FXML
    public void sendClick(ActionEvent actionEvent) throws IOException {
        ChatModel data=ChatModel.getData();
        sendWin.appendText("\n");
        try{
            data.send_buf=sendWin.getText();
            Sender.sendMsg();
            status.setText(status.getText()+'\n'+"Send Success");
        } catch (Exception e) {
            status.setText(status.getText()+'\n'+"Send Error");
        }
        sendWin.clear();
    }

    public void listenClick(ActionEvent actionEvent) {
        ChatModel data=ChatModel.getData();
        data.setLPort(local_port.getText());
        Receiver=new SocketReceiver();
        Receiver.start();
        Receiver.message.addListener(this::updateReceiveWin);
        status.setText("Listening Port:"+data.getLPort());
    }
}