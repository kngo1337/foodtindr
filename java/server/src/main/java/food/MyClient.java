package food;

import com.google.gson.*;
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
                client.target("https://api.yelp.com/v3/businesses/search?latitude=39.0839973&longitude=-77.1527578");
        Response response = resouce.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer oEjwi6RPIDsjF-OxQa_6-dfl9jtXgIcvm0BjweF5x02kFb_sVzq9ilqwx64gBcIoBAnb-KxKjEpH0aevJqiNKrB703Z-MsyIWaKuxZ-ML-xGmBPo-sIZ-bzRSNK6X3Yx")
                .get();

        String ret = response.readEntity(String.class);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed HTTP error code: " + response.getStatus() + ", " + ret);
        }

        JsonObject obj = new Gson().fromJson(ret, JsonObject.class);
        JsonArray jsonArray = obj.getAsJsonArray("businesses");
        for (JsonElement jsonElement : jsonArray) {
            String restaurantName = jsonElement.getAsJsonObject().get("name").getAsString();
            String imageUrl = jsonElement.getAsJsonObject().get("image_url").getAsString();
            System.out.println(restaurantName + ", " + imageUrl);
        }

//        Gson gson = new Gson();
//        Player p = gson.fromJson(ret, Player.class);
//        System.out.println(ret);
//        System.out.println(p);
    }
}
