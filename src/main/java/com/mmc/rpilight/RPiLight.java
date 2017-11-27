package com.mmc.rpilight;

import com.mmc.rpilight.client.Client;
import com.mmc.rpilight.client.ClientImplementation;
import com.mmc.rpilight.server.Server;
import com.mmc.rpilight.server.ServerImplementation;


public class RPiLight {
    public static Client clientInstance(String ip){
        return new ClientImplementation(ip);
    }

    public static Server serverInstance(){
        return new ServerImplementation();
    }
}
