package food.ws.gamestate;

import food.ws.gamestate.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class Room{

    ArrayList<Player> players;
    String roomCode = "";
    public boolean gameStarted = false;
    HashMap<String, Integer> restaurantMap;
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
