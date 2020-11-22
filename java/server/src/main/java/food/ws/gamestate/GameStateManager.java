package food.ws.gamestate;

import java.util.ArrayList;

public class GameStateManager {

    Room gameRoom = new Room();
    boolean gameStarted = false;
    ArrayList<String> names = new ArrayList<String>();
    int count = 0;

    public GameStateManager(){
        //Room gameRoom = new Room();
        //Room.players = new ArrayList<Player>();
    }

    private String createNewGame(String playerName){
        String randomString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuffer newGameCode = new StringBuffer(4);//Creates StringBuffer of size 4

        /*
         * for the length of the StringBuffer newGameCode, randomly chooses a character
         * in the String randomString and appends it to newGameCode
         */
        for(int i = 0; i < newGameCode.length(); i++){
            for(int j = 0; j < randomString.length(); i++) {
                newGameCode.append(randomString.charAt(j));
            }
        }
        //Sets the Room gameRoom's code to newGameCode
        gameRoom.roomCode = newGameCode.toString();
        return newGameCode.toString();
    }

    private void addPlayers(String roomCode, String playerName){
        Player person = new Player(playerName);
        if(roomCode.equals(gameRoom.roomCode)) {
            gameRoom.players.add(person);
        } else{
            System.out.println("Fuck you Error");
        }

    }

    void startGame(String roomCode){
        gameStarted = true;

        //Get yelp API and store list of restaurant in Room
        gameRoom.restaurants.add("Yelp APi");

    }

    private boolean confirmRestaurants(String roomCode, String playerName, String restaurant, boolean confirm){
        String chooseRestaurant = restaurant;
        if(!gameRoom.roomCode.equals(roomCode)) {
            return false;
        }
        if(!(count > gameRoom.restaurants.size() / 2)) {
            for (int i = 0; i < gameRoom.restaurants.size(); i++) {
                if (confirm) {
                    if (chooseRestaurant.equals(gameRoom.restaurants.indexOf(i))) {

                        count++;
                    }
                }
            }
        } else{
            return true;
        }
        return false;
    }


    Object getGameState(){
        Object hi = new Object();
        if(gameStarted){
            return hi;
        }
        return hi;
    }
}
