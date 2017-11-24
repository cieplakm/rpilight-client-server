package com.mmc.rpilight.client; /**
 * Created by mcieplak on 2017-11-24.
 */

import java.io.*;
import java.net.*;

public class Client {
    public final static int PORT = 4445;
    private InetAddress address;
    private DatagramSocket socket = null;
    String addressStr;
    private ServerListener serverListener;

    interface ServerListener{
        void onMessageRecive(String message);
    }

    public Client(String addressStr, ServerListener serverListener ) {
        this(addressStr);
        this.serverListener = serverListener;
    }


    public Client(String addressStr) {
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        try {
            address = InetAddress.getByName(addressStr);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void request(String command){
        byte[] buf = command.getBytes();

        DatagramPacket packet = new DatagramPacket( buf, buf.length, address, PORT );

        send(packet);
        startWaiting4Response();

    }


    public void close(){
        socket.close();
    }

    private void send(DatagramPacket packet){
        try {
            socket.send(packet);
            System.out.println("Client :: Request sended");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startWaiting4Response(){
        System.out.println("Client :: Waiting 4 response...");
        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);

        try {
            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        onRecive(packet);


    }

    private void onRecive(DatagramPacket packet) {
        System.out.println("Client :: Ooo! Serwer responsed!");

        if (serverListener != null)
            serverListener.onMessageRecive(new String(packet.getData(), 0, packet.getLength()));

    }
}