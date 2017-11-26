package com.mmc.rpilight.client;

import com.mmc.rpilight.OnReciveListener;
import com.mmc.rpilight.OnResponseListener;
import com.mmc.rpilight.server.Request;

/**
 * Created by Moni on 2017-11-24.
 */

public interface Client {
    void request(Request request);
    void setOnReciveListener(OnResponseListener onResponseListener);
}
