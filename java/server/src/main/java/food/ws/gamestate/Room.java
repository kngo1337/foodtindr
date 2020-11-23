package food.ws.gamestate;

import food.YelpAPI;
import food.ws.gamestate.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class Room{

    ArrayList<Player> players = new ArrayList<>();
    String roomCode = "";
    public boolean gameStarted = false;
    HashMap<String, Integer> restaurantMap = new HashMap<>();;
    ArrayList<YelpAPI.Restaurant> restaurantToImage = new ArrayList<>();
    boolean winner = false;

    public Room(){ }

    public void addPlayer(String playerName){
        Player person = new Player(playerName);
        players.add(person);
    }





//    public static ArrayList<Player> getPlayers(){
//        ArrayList<Player> temp = new ArrayList<Player>();
//        temp = players;
//        return temp;
//    }
}
