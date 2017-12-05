package com.mmc.rpilight.server;

import com.mmc.rpilight.Config;
import com.mmc.rpilight.serialization.JSON2Object;
import com.mmc.rpilight.OnRequestListener;

import java.io.*;
import java.net.*;


public class ServerImplementation extends Thread implements Server {

    protected DatagramSocket socket = null;
    private OnRequestListener onRequestListener;
    private JSON2Object json2Object;


    public ServerImplementation(Config config, JSON2Object json2Object)  {
        this.json2Object = json2Object;

        try {
            socket = new DatagramSocket(config.getPort());
        } catch (SocketException e) {
            e.printStackTrace();

        }

    }

    public void setOnRequestListener(OnRequestListener onRequestListener){
        this.onRequestListener = onRequestListener;
    }

    public void run() {
       while (true)
           listening();
    }


    private void listening() {
        System.out.println("ServerImplementation :: I am ready for connect client");

        byte[] buf = new byte[1024];
        final DatagramPacket packet = new DatagramPacket(buf, buf.length);

        try {
            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }

       new Thread(new Runnable() {
           @Override
           public void run() {
               onRecived(packet);
           }
       }).start();

    }


    private void onRecived(DatagramPacket packet) {
        System.out.println("ServerImplementation :: O! Something recived!");

        String data = new String(packet.getData(), 0, packet.getLength());


        Request request = json2Object.getObject(data, Request.class);

        request.setAddress(packet.getAddress());
        request.setPort(packet.getPort());

        if (onRequestListener != null)
            onRequestListener.onRequest(request);

    }


    @Override
    public void response(Response response)  {
        System.out.println("ServerImplementation :: Responsing ...");


        InetAddress address = response.getAddress();
        int port = response.getPort();



        String json = json2Object.getJson(response);

        byte[] buf = json.getBytes();

        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);

        System.out.println("ServerImplementation :: Responsed ready to send to " + address.toString());

        sendPacket(packet);
    }



    private void sendPacket(DatagramPacket packet) {
        try {
            System.out.println("ServerImplementation :: Answer sending ...");

            socket.send(packet);

            System.out.println("ServerImplementation :: Answer sended" + packet.getAddress() );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}