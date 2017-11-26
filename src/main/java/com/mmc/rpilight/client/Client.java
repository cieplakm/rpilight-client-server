package com.mmc.rpilight.client;

import com.mmc.rpilight.OnResponseListener;
import com.mmc.rpilight.server.Request;

public interface Client {
    void request(Request request);
    void setOnReciveListener(OnResponseListener onResponseListener);
}
