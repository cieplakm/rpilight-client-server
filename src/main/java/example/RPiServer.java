package example;

import com.mmc.rpilight.OnResponseListener;
import com.mmc.rpilight.client.Client;
import com.mmc.rpilight.server.Request;
import com.mmc.rpilight.server.Response;
import com.mmc.rpilight.server.Server;
import com.mmc.rpilight.server.ServerImplementation;

/**
 * Created by Moni on 2017-11-26.
 */

public class RPiServer {

    public static void main(String[] args){

        Server server = new Server.Builder()
                .setPort(8080)
                .build();

        server.start();


        new Thread(new Runnable() {
            @Override
            public void run() {



            while(true){
                Client client = new Client.Builder()
                        .setIP("localhost")
                        .setPort(8080)
                        .build();

                client.setOnReciveListener(new OnResponseListener() {
                    @Override
                    public void onResponse(Response response) {
                        System.out.println("Server responsed! Lamp is ON: " + response.isLampOn());
                    }
                });

                client.request(new Request(true)); //request to change state
                client.request(new Request()); //request to get info

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
