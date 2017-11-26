package com.mmc.rpilight.server;


import com.mmc.rpilight.OnReciveListener;

import java.net.UnknownHostException;

public interface Server {
	
	
    interface ServerListener{
        void onMessageRecive(String message);
    }
    
    void start();

    void response(Response response);

    void setOnReciveListener(OnReciveListener onReciveListener);

}
