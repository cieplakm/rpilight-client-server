package com.mmc.rpilight;

import com.mmc.rpilight.client.Client;
import com.mmc.rpilight.client.ClientImplementation;
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
        server.setOnRequestListener(new OnRequestListener() {
            @Override
            public void onRequest(Request request) {
                System.out.println(request.getTypeDescription());
                Response response = new Response(request);
                response.setMessage("To jest oficjalana odpowiedź z serwera");

                server.response(response);
            }
        });
        server.start();




        new Thread(new Runnable() {
            @Override
            public void run() {
               while(true){
                   Client client = new ClientImplementation("localhost");
                   client.setOnReciveListener(new OnResponseListener() {
                       @Override
                       public void onResponse(Response response) {
                           System.out.println("Server odpowiedział!!!!!!!!!!");
                           System.out.println(response.getMessage());
                       }
                   });



                   client.request(new Request());
                   client.request(new Request(true));
                   client.request(new Request(false));
                   client.request(new Request());


                   try {
                       Thread.sleep(5000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
            }
        }).start();
    }
}
