package com.mmc.rpilight;

import com.mmc.rpilight.server.Request;

public interface OnRequestListener {
    void onRequest(Request request);
}
