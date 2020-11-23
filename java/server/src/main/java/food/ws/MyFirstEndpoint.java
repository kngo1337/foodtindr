package food.ws;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.JsonAdapter;
import food.YelpAPI;
import food.gamestate.GameStateManager;
import food.gamestate.Player;
import food.gamestate.Room;
import food.model.WsJoinRoom;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Path("")
public class MyFirstEndpoint {
    //    @Get
//    @Path("1")
//    @Produces(MediaType.APPLICATION_JSON)
//    public BasicStringJson basicStringJson() {
//        return BasicStringJson;
//    }
    @GET
    public String basicStringJson() {
        return "HELLO WORLD";
    }

    @GET
    @Path("createRoom")
    public Response createRoom(@QueryParam("playerName") String playerName) {
        System.out.println("create room. " + playerName);
        String roomCode = GameStateManager.gameStateManager.createNewGame(playerName);
        JsonObject roomObject = new JsonObject();
        roomObject.addProperty("roomCode",roomCode);
        System.out.println(roomObject.toString());
        return Response.ok(roomObject.toString(), MediaType.APPLICATION_JSON_TYPE).build();
    }

    @GET
    @Path("joinRoom")
    public Response joinRoom(@QueryParam("roomCode") String roomCode, @QueryParam("playerName") String playerName) {
        System.out.println("join room: " + roomCode + ", " + playerName);
        GameStateManager.gameStateManager.addPlayers(roomCode, playerName);
        return Response.ok().build();
    }

    @GET
    @Path("getPlayers")
    public Response getPlayers(@QueryParam("roomCode") String roomCode) {
        WsJoinRoom wsJoinRoom = new WsJoinRoom();

        Room room = GameStateManager.gameStateManager.getRoom(roomCode);
        for (Player p : GameStateManager.gameStateManager.getRoom(roomCode).players) {
            wsJoinRoom.players.add(p.name);
        }
        wsJoinRoom.gameStarted = room.gameStarted;

        String s = new Gson().toJson(wsJoinRoom);

        return Response.ok(s, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @GET
    @Path("startGame")
    public Response startGame(@QueryParam("roomCode") String roomCode) {
        System.out.println("Start game: " + roomCode);
        GameStateManager.gameStateManager.startGame(roomCode);
        return Response.ok().build();
    }

    @GET
    @Path("getRestaurantList")
    public Response getRestaurantList(@QueryParam("roomCode") String roomCode) {
        System.out.println("get restaurant list: " + roomCode);
        List<YelpAPI.Restaurant> restaurants = GameStateManager.gameStateManager.getRestaurants(roomCode);

        String s = new Gson().toJson(restaurants);
        return Response.ok(s, MediaType.APPLICATION_JSON_TYPE).build();
    }

    @GET
    @Path("confirmRestaurants")
    public Response confirmRestaurants(@QueryParam("roomCode") String roomCode,
                                       @QueryParam("playerName") String playerName,
                                       @QueryParam("restaurant") String restaurant) {
        System.out.println("confirm restaurants: " + roomCode + ", " + playerName + ", " + restaurant);
        GameStateManager.gameStateManager.confirmRestaurants(roomCode, playerName, restaurant);
        return Response.ok().build();
    }

    @GET
    @Path("winningRestaurant")
    public Response winningRestaurant(@QueryParam("roomCode") String roomCode) {
        String winningRestaurant = GameStateManager.gameStateManager.getWinningRestaurant(roomCode);

        JsonObject restaurantObject = new JsonObject();
        restaurantObject.addProperty("winningRestaurant", Objects.requireNonNullElse(winningRestaurant, ""));
        return Response.ok(restaurantObject.toString(), MediaType.APPLICATION_JSON_TYPE).build();
    }

}