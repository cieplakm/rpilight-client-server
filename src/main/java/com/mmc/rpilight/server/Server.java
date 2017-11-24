package com.mmc.rpilight.server; /**
 * Created by mcieplak on 2017-11-24.
 */

import java.io.*;
import java.net.*;


public class Server extends Thread{

    protected DatagramSocket socket = null;

    public Server()  {

        try {
            socket = new DatagramSocket(4445);
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

    public void run() {
        while (true)
            startListening();
    }







    private void startListening() {
        System.out.println("Server :: I am ready for connect client");

        byte[] buf = new byte[256];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);

        try {
            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        startAnsweringInNewThread(packet);

    }

    private void startAnsweringInNewThread(DatagramPacket packet) {
        new Thread( () -> onRecived(packet) ).start();
    }

    private void onRecived(DatagramPacket packet) {
        System.out.println("Server :: O! Something recived!");
        sendanswer( prepareAnswer(packet) );
    }

    private DatagramPacket prepareAnswer(DatagramPacket inputPacket) {
        System.out.println("Server :: Preparing answer...");

        String dString = getAnswer();

        byte[] buf = dString.getBytes();

        InetAddress address = inputPacket.getAddress();
        int port = inputPacket.getPort();

        inputPacket = new DatagramPacket(buf, buf.length, address, port);

        System.out.println("Server :: Answer prepared");
        return inputPacket;
    }

    private void sendanswer(DatagramPacket packet) {
        try {
            System.out.println("Server :: Answer sending ...");

            socket.send(packet);

            System.out.println("Server :: Answer sended");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAnswer() {
        return "ODPOWIEDZ_Z_SERWERA";
    }
}