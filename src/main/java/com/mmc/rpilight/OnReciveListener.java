package com.mmc.rpilight;

import com.mmc.rpilight.server.Request;

import java.net.UnknownHostException;

/**
 * Created by Moni on 2017-11-25.
 */

public interface OnReciveListener {
    void onRecive(Request request);
}
