package food.gamestate;

public class Driver {
    public static void main(String[] args){
        GameStateManager game = GameStateManager.gameStateManager;
        String code = game.createNewGame("Tina");
        System.out.println(code);
        game.addPlayers(code, "Colin");
        game.startGame(code);
        game.confirmRestaurants(code, "Tina", "Il Pizzico");
        game.confirmRestaurants(code, "Colin", "Il Pizzico");
        if(game.getRoom(code).winningRestaurant != null){
            System.out.println("true");
        } else{ System.out.println("false"); }
    }
}
