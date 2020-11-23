package food.gamestate;

import food.YelpAPI;

import java.util.ArrayList;
import java.util.HashMap;

public class Room{

    public ArrayList<Player> players = new ArrayList<>();
    String roomCode = "";
    public boolean gameStarted = false;
    HashMap<String, Integer> restaurantMap = new HashMap<>();;
    ArrayList<YelpAPI.Restaurant> restaurantToImage = new ArrayList<>();
    String winningRestaurant = null;

    public Room(){ }

    public void addPlayer(String playerName){
        Player person = new Player(playerName);
        players.add(person);
    }

}
