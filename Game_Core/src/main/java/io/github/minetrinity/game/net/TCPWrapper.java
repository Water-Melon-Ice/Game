package io.github.minetrinity.game.net;

import io.github.minetrinity.game.Game;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;

public class TCPWrapper {

    protected Socket tcpsocket;

    private InputStream in;
    private OutputStream out;

    public TCPWrapper(Socket socket){
        tcpsocket = socket;
    }

    public void send(byte[] data) {
        try {
            tcpsocket.getOutputStream().write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void receive() {
        try {
            BufferedReader typereader = new BufferedReader(new InputStreamReader(tcpsocket.getInputStream()));
            String type = typereader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void run() {
        while(!tcpsocket.isClosed()){
            receive();
        }
    }


    public void connect(SocketAddress ip) {
        try {
            tcpsocket.connect(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
