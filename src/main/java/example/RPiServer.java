package example;

import com.mmc.rpilight.OnRequestListener;
import com.mmc.rpilight.OnResponseListener;
import com.mmc.rpilight.RPiLight;
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




        new Thread(new Runnable() {
            @Override
            public void run() {

                Client client = RPiLight.clientInstance("192.168.1.15");

                client.setOnReciveListener(new OnResponseListener() {
                    @Override
                    public void onResponse(Response response) {
                        System.out.println("Server responsed! Lamp is ON: " + response.isLampOn());
                    }
                });

                client.request(new Request()); //request to get info
                client.request(new Request(false)); //request to change state
                client.request(new Request());

               try {
                   Thread.sleep(5000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

            }
        }).start();
    }
}
