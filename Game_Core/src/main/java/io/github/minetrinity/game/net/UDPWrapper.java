package io.github.minetrinity.game.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class UDPWrapper {

    protected DatagramSocket udpsocket;

    private Deque<DatagramPacket> inlist = new ArrayDeque<>();
    int maxSize;

    public UDPWrapper(){
        try {
            udpsocket = new DatagramSocket();
            boolean recsmal = udpsocket.getReceiveBufferSize() < udpsocket.getSendBufferSize();
            maxSize = recsmal ? udpsocket.getReceiveBufferSize() : udpsocket.getSendBufferSize();
            System.out.println(maxSize);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }


    public void send(DatagramPacket packet) {

    }


    public void receive() {

    }


    public void connect(SocketAddress ip) {

    }


    public void run() {

    }
}
