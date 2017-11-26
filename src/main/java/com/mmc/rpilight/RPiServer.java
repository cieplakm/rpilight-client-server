package com.mmc.rpilight;

import com.mmc.rpilight.server.Request;
import com.mmc.rpilight.server.Response;
import com.mmc.rpilight.server.Server;
import com.mmc.rpilight.server.ServerImplementation;

/**
 * Created by Moni on 2017-11-26.
 */

public class RPiServer {

    public static void main(String[] args){

        Server server = new ServerImplementation();
        server.setOnReciveListener(new OnReciveListener() {
            @Override
            public void onRecive(Request request) {
                Response response = new Response(request);
                response.setMessage("O");
                server.response(response);
            }
        });
        server.start();
    }
}
