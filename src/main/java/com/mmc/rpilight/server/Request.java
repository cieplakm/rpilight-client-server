package com.mmc.rpilight.server;


import java.net.InetAddress;

public class Request {
    public String cmd;

    public InetAddress getAddress() {
        return address;
    }

    private transient  InetAddress address;

    public void setAddress(InetAddress address) {
        this.address = address;
    }
}
