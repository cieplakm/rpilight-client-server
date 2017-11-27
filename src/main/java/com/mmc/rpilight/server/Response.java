package com.mmc.rpilight.server;

import java.net.InetAddress;

/**
 * Created by Moni on 2017-11-26.
 */

public class Response {
    private transient InetAddress address;
    private transient int port;

    private boolean isLampOn;

    public Response(Request request) {
        port = request.getPort();
        address = request.getAddress();
    }

    public void setLampOn(boolean lampOn) {
        isLampOn = lampOn;
    }
    public boolean isLampOn() {
        return isLampOn;
    }

    public InetAddress getAddress() {
        return address;
    }
    public int getPort() {
        return port;
    }




}
