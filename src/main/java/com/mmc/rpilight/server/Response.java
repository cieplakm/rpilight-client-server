package com.mmc.rpilight.server;

import java.net.InetAddress;

/**
 * Created by Moni on 2017-11-26.
 */

public class Response {
    private transient InetAddress address;
    private transient int port;
    private String msg;

    public Response(Request request) {
        port = request.getPort();
        address = request.getAddress();
    }

    public String getMessage() {
        return msg;
    }

    public InetAddress getAddress() {
        return address;
    }
    public int getPort() {
        return port;
    }

    public void setMessage(String message) {
        this.msg = message;
    }
}
