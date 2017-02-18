package game.battleship.service;

import game.battleship.model.GameState;
import game.battleship.model.Player;
import game.battleship.model.Sea;

/**
 * Developer: Ben Oeyen
 * Date: 17/02/2017
 */
public class FiringService {

    public static void shoot(GameState gameState, Player player, int coordinateX, int coordinateY){
        if(gameState.getP1().equals(player) && gameState.getP1().equals(gameState.getPlayerToFire())){
            fire(player, gameState.getSea2(), coordinateX, coordinateY);
            gameState.setPlayerToFire(gameState.getP2());
        }else if(gameState.getP2().equals(player) && gameState.getP2().equals(gameState.getPlayerToFire())){
            fire(player, gameState.getSea1(), coordinateX, coordinateY);
            gameState.setPlayerToFire(gameState.getP1());
        }
    }


    private static void fire(Player player, Sea sea, int coordinateX, int coordinateY) {
        boolean hit = sea.fire(coordinateX, coordinateY);
        player.incrementShots();
        if(hit){
           player.incrementHits();
        }
    }
}
