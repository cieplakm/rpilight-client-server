package com.mmc.rpilight.server;


import java.net.DatagramPacket;
import java.net.InetAddress;

public class Request {
    private static final int INFO = 0;
    private static final int ACTION = 1;

    private int type;



    private boolean swithOn;

    private transient  InetAddress address;


    private Request(int type) {
        this.type = type;
    }

    public Request(){
        this(INFO);
    }

    public Request(boolean swithOn){
        this(ACTION);
        this.swithOn = swithOn;
    }


    public boolean shouldOn() {
        return swithOn;
    }



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

    public String  getTypeDescription(){
        if (type == ACTION){
            return "This is Action!";
        }else if (type==INFO){
            return "This is Ask about state!";
        }
        return "Unknown request";
    }

    public boolean isAction() {
        return type == ACTION;
    }
}
