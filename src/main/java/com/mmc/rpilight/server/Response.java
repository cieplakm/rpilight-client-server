package com.mmc.rpilight.server;

import java.net.InetAddress;

/**
 * Created by Moni on 2017-11-26.
 */

public class Response {
    private transient InetAddress address;

    public void setAddress(InetAddress address) {
        this.address = address;
    }



    public InetAddress getAddress() {
        return address;
    }
}
