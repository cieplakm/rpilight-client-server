# rpi-light-communication-lib

Simple lib to create Server and Client communication


### Using

```
public class RPiServerAndClient {

    public static void main(String[] args){
		//server site
        Server server = RPiLight.serverInstance();

        server.setOnRequestListener(new OnRequestListener() {
            @Override
            public void onRequest(Request request) {

                System.out.println(request.getTypeDescription());

                Response response = new Response(request);

                if (request.isAction()){
                    // do operation with lamp
                    // and response
                   response.setLampOn(request.shouldOn());
                }else {
                    //return lamp state
                    response.setLampOn(true);
                }

                server.response(response);

            }
        });

        server.start();

        //client site
        new Thread(new Runnable() {
            @Override
            public void run() {

                Client client = RPiLight.clientInstance("localhost");

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
```

