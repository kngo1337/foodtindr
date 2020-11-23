package food.gamestate;

public class Player {
    String name = "";
    String restaurant = "";
    public Player(String playerName){
        this.name = playerName;
    }
    public Player(String playerName, String restaurant){
        this.name = playerName;
        this.restaurant = restaurant;
    }
}
