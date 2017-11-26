package com.mmc.rpilight;

import com.mmc.rpilight.server.Request;
import com.mmc.rpilight.server.Response;

/**
 * Created by Moni on 2017-11-26.
 */

public interface OnResponseListener {
        void onRecive(Response response);

}
