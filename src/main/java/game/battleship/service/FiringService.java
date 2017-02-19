package game.battleship.service;

import game.battleship.model.GameState;
import game.battleship.model.Player;
import game.battleship.model.Sea;

/**
 * Developer: Ben Oeyen
 * Date: 17/02/2017
 */
public class FiringService {

    public static void shoot(GameState gameState, Player player, int X, int Y){
        Player p1 = gameState.getP1();
        Player p2 = gameState.getP2();
        Player playerToFire = gameState.getPlayerToFire();

        if(p1.equals(player) && p1.equals(playerToFire)){
            fire(player, gameState.getSea2(), X, Y);
            gameState.setPlayerToFire(p2);

        }else if(p2.equals(player) && p2.equals(playerToFire)){
            fire(player, gameState.getSea1(), X, Y);
            gameState.setPlayerToFire(p1);
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
