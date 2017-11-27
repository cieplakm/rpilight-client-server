package com.mmc.rpilight.server;


import com.mmc.rpilight.OnRequestListener;

public interface Server {
	
	
    interface ServerListener{
        void onMessageRecive(String message);
    }
    
    void start();

    void response(Response response);

    void setOnRequestListener(OnRequestListener onRequestListener);

}
