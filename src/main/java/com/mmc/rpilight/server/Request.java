package com.mmc.rpilight.server;


import java.net.DatagramPacket;
import java.net.InetAddress;

public class Request {
    public String cmd;
    private transient  InetAddress address;


    public InetAddress getAddress() {
        return address;
    }
    public void setAddress(InetAddress address) {
        this.address = address;
    }



}
