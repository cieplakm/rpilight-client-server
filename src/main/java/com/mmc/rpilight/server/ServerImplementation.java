package com.mmc.rpilight.server; /**
 * Created by mcieplak on 2017-11-24.
 */

import com.google.gson.Gson;
import com.mmc.rpilight.Config;
import com.mmc.rpilight.OnRequestListener;

import java.io.*;
import java.net.*;


public class ServerImplementation extends Thread implements Server {

    protected DatagramSocket socket = null;
    private OnRequestListener onRequestListener;


    public ServerImplementation()  {

        try {
            socket = new DatagramSocket(Config.PORT);
        } catch (SocketException e) {
            e.printStackTrace();

        }

    }

    public void setOnRequestListener(OnRequestListener onRequestListener){
        this.onRequestListener = onRequestListener;
    }

    public void run() {
       while (true)
           startListening();
    }


    private void startListening() {
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

        Gson gson = new Gson();

        Request request = gson.fromJson(data, Request.class);

        request.setAddress(packet.getAddress());
        request.setPort(packet.getPort());

        onRequestListener.onRequest(request);

    }


    @Override
    public void response(Response response)  {
        System.out.println("ServerImplementation :: Responsing ...");


        InetAddress address = response.getAddress();
        int port = response.getPort();


        Gson gson = new Gson();
        String json = gson.toJson(response);

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