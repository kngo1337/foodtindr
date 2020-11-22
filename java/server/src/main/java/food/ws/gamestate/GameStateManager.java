package food.ws.gamestate;

import java.util.ArrayList;

public class GameStateManager {

    ArrayList<Room> gameRoom = new ArrayList<Room>();

    public GameStateManager(){

    }

    private String createNewGame(String playerName){
        Room newGame = new Room();
        String randomString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuffer newGameCode = new StringBuffer(6);//Creates StringBuffer of size 4

        /*
         * for the length of the StringBuffer newGameCode, randomly chooses a character
         * in the String randomString and appends it to newGameCode
         */
        for(int i = 0; i < 6; i++){
            int index = (int)(Math.random() * randomString.length());
            newGameCode.append(randomString.charAt(index));
        }
        //Sets the Room gameRoom's code to newGameCode
        newGame.roomCode = newGameCode.toString();
        newGame.addPlayer(playerName);
        gameRoom.add(newGame);

        return newGameCode.toString();
    }

    private void addPlayers(String roomCode, String playerName){

        for(Room game: gameRoom){
            if(game.roomCode.equals(roomCode)){
                game.addPlayer(playerName);
                return;
            }
        }
        throw new RuntimeException("Can't find room");
    }

    void startGame(String roomCode){
        for(Room game: gameRoom){
            if(game.roomCode.equals(roomCode)){
                // TODO: 11/22/2020 yelp API sets restaurants
                game.gameStarted = true;
                return;
            }
        }

    }

    private void confirmRestaurants(String roomCode, String playerName, String restaurant){
        for(Room game: gameRoom){
            if(game.roomCode.equals(roomCode)){
                Integer temp = game.restaurantMap.get(restaurant);
                temp++;
                game.restaurantMap.put(restaurant,temp);
                if(temp == game.players.size()){
                    game.winner = true;
                }
            }
        }
    }


    Object getGameState(){
        Object hi = new Object();

        return hi;
    }

    public static void main(String[] args){
        GameStateManager game = new GameStateManager();
        String code = game.createNewGame("Tina");
        System.out.println(code);
        game.addPlayers(code, "Colin");
    }
}
