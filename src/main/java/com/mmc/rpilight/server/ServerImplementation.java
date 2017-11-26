package com.mmc.rpilight.server; /**
 * Created by mcieplak on 2017-11-24.
 */

import com.google.gson.Gson;
import com.mmc.rpilight.Config;
import com.mmc.rpilight.OnReciveListener;

import java.io.*;
import java.net.*;

import static com.mmc.rpilight.Config.PORT;


public class ServerImplementation extends Thread implements Server {

    protected DatagramSocket socket = null;
    private OnReciveListener onReciveListener;


    public ServerImplementation()  {



        try {
            socket = new DatagramSocket(Config.PORT);
        } catch (SocketException e) {
            e.printStackTrace();

        }

    }

    @Override
    public void setOnReciveListener(OnReciveListener onReciveListener){
        this.onReciveListener = onReciveListener;
    }

    public void run() {
       while (true)
           startListening();


    }


    private void startListening() {
        System.out.println("ServerImplementation :: I am ready for connect client" +  socket.getLocalAddress());

        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);

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
       });


    }


    private void onRecived(DatagramPacket packet) {
        
        System.out.println("ServerImplementation :: O! Something recived!");

//        String data = new String(packet.getData(), 0, packet.getLength());
//
//        Gson gson = new Gson();
//
//        Request request = gson.fromJson(data, Request.class);
//
//        request.setAddress(packet.getAddress());

        //onReciveListener.onRecive(request);

        byte[] buf = "dfasdf".getBytes();

        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        packet = new DatagramPacket(buf, buf.length, address, port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void response(Response response)  {
        System.out.println("ServerImplementation :: Responsing ...");
        Gson gson = new Gson();

        byte[] buf = gson.toJson(response).toString().getBytes();

        InetAddress address = response.getAddress();
        System.out.println("ServerImplementation :: Responsing ..." + address.toString());


        String d = gson.toJson(response);

        buf = d.getBytes();



       // InetAddress address = response.getDataPacket().getAddress();

        //int port = response.getDataPacket().getPort();

        DatagramPacket packet;
        packet = new DatagramPacket(buf, buf.length, address, PORT);

//
//        DatagramPacket outputPacket = null;
//
//        outputPacket = new DatagramPacket(buf, buf.length, address, PORT);


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