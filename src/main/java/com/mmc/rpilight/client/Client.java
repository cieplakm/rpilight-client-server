package com.mmc.rpilight.client;

import com.mmc.rpilight.Config;
import com.mmc.rpilight.OnResponseListener;
import com.mmc.rpilight.server.Request;

public interface Client {
    void request(Request request);
    void setOnReciveListener(OnResponseListener onResponseListener);

    class Builder {
        String ip = "localhost";
        int port = 8080;

        public Builder setPort(int port){
            this.port = port;

            return this;
        }

        public Builder setIP(String ip){
            this.ip = ip;

            return this;
        }

        public Client build(){
            return new ClientImplementation(new Config() {
                @Override
                public String getIP() {
                    return ip;
                }

                @Override
                public int getPort() {
                    return port;
                }
            });
        }
    }
}
