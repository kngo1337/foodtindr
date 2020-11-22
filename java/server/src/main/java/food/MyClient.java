package food;

import com.google.gson.Gson;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class MyClient {

    public static void main(String[] args) {

        ClientConfig clientConfig = new ClientConfig();
//        clientConfig.register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME), Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000));
        Client client = ClientBuilder.newBuilder().withConfig(clientConfig).build();


        WebTarget resouce =
                client.target("https://na1.api.riotgames.com/lol/summoner/v4/summoners/by-name/mrswagbagtag");
        Response response = resouce.request(MediaType.APPLICATION_JSON)
                .header("X-Riot-Token", "RGAPI-d152e4c2-ec12-45ed-9070-cfc06c25cb71")
                .get();

        String ret = response.readEntity(String.class);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed HTTP error code: " + response.getStatus() + ", " + ret);
        }






//        Gson gson = new Gson();
//        Player p = gson.fromJson(ret, Player.class);
//        System.out.println(ret);
//        System.out.println(p);
    }
}
