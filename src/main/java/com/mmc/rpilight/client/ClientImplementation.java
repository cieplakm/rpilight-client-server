package com.mmc.rpilight.client; /**
 * Created by mcieplak on 2017-11-24.
 */

import com.google.gson.Gson;
import com.mmc.rpilight.OnReciveListener;
import com.mmc.rpilight.OnResponseListener;
import com.mmc.rpilight.server.Request;
import com.mmc.rpilight.server.Response;
import com.mmc.rpilight.server.Server;

import java.io.*;
import java.net.*;

import static com.mmc.rpilight.Config.PORT;

public class ClientImplementation implements Client {

    private InetAddress address;
    private DatagramSocket socket = null;
    private OnResponseListener onResponseListener;

    public ClientImplementation(String addressStr) {
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

    @Override
    public void request(Request command){

        Gson gson = new Gson();
        String json = gson.toJson(command);

        byte[] buf = json.getBytes();

        DatagramPacket packet = new DatagramPacket( buf, buf.length, address, PORT );

        send(packet);

        startWaiting4Response();

    }

    @Override
    public void setOnReciveListener(OnResponseListener onResponseListener) {

        this.onResponseListener = onResponseListener;
    }


    public void close(){
        socket.close();
    }

    private void send(DatagramPacket packet){
        try {
            socket.send(packet);
            System.out.println("ClientImplementation :: Request sended " + packet.getAddress().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startWaiting4Response(){
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

        Gson gson = new Gson();

        String json = new String(packet.getData(), 0, packet.getLength());

        Response response = gson.fromJson(json, Response.class);

        onResponseListener.onRecive(response);

    }
}