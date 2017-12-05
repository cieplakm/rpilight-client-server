package com.mmc.rpilight.client;

import com.mmc.rpilight.Config;
import com.mmc.rpilight.serialization.JSON2Object;
import com.mmc.rpilight.OnResponseListener;
import com.mmc.rpilight.server.Request;
import com.mmc.rpilight.server.Response;

import java.io.*;
import java.net.*;

public class ClientImplementation implements Client {

    private InetAddress address;
    private DatagramSocket socket = null;
    private OnResponseListener onResponseListener;
    private Config config;
    private JSON2Object json2Object;

    public ClientImplementation(Config config, JSON2Object json2Object) {
        this.config = config;
        this.json2Object = json2Object;

        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        try {
            address = InetAddress.getByName(this.config.getIP());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void request(Request command) {

        String json = json2Object.getJson(command);

        byte[] buf = json.getBytes();

        DatagramPacket packet = new DatagramPacket( buf, buf.length, address, this.config.getPort() );

        send(packet);

        wait4Response();

    }

    @Override
    public void setOnReciveListener(OnResponseListener onResponseListener) {
        this.onResponseListener = onResponseListener;
    }

    private void send(DatagramPacket packet){
        try {
            socket.send(packet);
            System.out.println("ClientImplementation :: Request sended " + packet.getAddress().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void wait4Response(){
        System.out.println("ClientImplementation :: Waiting 4 response...");

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
        System.out.println("ClientImplementation :: Ooo! Serwer responsed!");

        String json = new String(packet.getData(), 0, packet.getLength());

        Response response = json2Object.getObject(json, Response.class);

        onResponseListener.onResponse(response);

    }

    public void close(){
        socket.close();
    }



}