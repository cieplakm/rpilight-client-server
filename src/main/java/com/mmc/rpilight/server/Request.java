package com.mmc.rpilight.server;


import java.net.DatagramPacket;
import java.net.InetAddress;

public class Request {
    public String cmd;
    private transient  InetAddress address;

    public int getPort() {
        return port;
    }

    private transient int port;


    public InetAddress getAddress() {
        return address;
    }
    public void setAddress(InetAddress address) {
        this.address = address;
    }


    public void setPort(int port) {
        this.port = port;
    }
}
