package com.mmc.rpilight.server;


import com.google.gson.Gson;
import com.mmc.rpilight.Config;
import com.mmc.rpilight.serialization.JSONAdapter;
import com.mmc.rpilight.OnRequestListener;

public interface Server {
	
	
    interface ServerListener{
        void onMessageRecive(String message);
    }
    
    void start();

    void response(Response response);

    void setOnRequestListener(OnRequestListener onRequestListener);



    class Builder {
        int port = 8080;

        public Server.Builder setPort(int port){
            this.port = port;

            return this;
        }

        public Server build(){
            return new ServerImplementation(new Config() {
                @Override
                public String getIP() {
                    return null;
                }

                @Override
                public int getPort() {
                    return port;
                }
            }, new JSONAdapter(new Gson()));
        }
    }

}
