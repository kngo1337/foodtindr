package food.ws;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.JsonAdapter;
import food.gamestate.GameStateManager;
import food.gamestate.Player;
import food.model.WsJoinRoom;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

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
        for (Player p : GameStateManager.gameStateManager.getRoom(roomCode).players) {
            wsJoinRoom.players.add(p.name);
        }
        String s = new Gson().toJson(wsJoinRoom);

        return Response.ok(s, MediaType.APPLICATION_JSON_TYPE).build();
    }

}