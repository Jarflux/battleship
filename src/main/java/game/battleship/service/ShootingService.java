package game.battleship.service;

import game.battleship.model.*;

/**
 * Developer: Ben Oeyen
 * Date: 17/02/2017
 */
public class ShootingService {

    public static void shoot(GameState gameState, Player player, int X, int Y){
        Player p1 = gameState.getP1();
        Player p2 = gameState.getP2();
        Player playerToFire = gameState.getPlayerToFire();

        if(p1.equals(player) && p1.equals(playerToFire)){
            executeShotAndPossibleJarvisShot(gameState, player, X, Y, p2, gameState.getSea2(), gameState.getSea1());
        }else if(p2.equals(player) && p2.equals(playerToFire)){
            executeShotAndPossibleJarvisShot(gameState, player, X, Y, p1, gameState.getSea1(), gameState.getSea2());
        }
    }

    private static void executeShotAndPossibleJarvisShot(GameState gameState, Player player, int X, int Y, Player p1, Sea sea1, Sea sea2) {
        shoot(player, sea1, X, Y);
        if (p1 instanceof Jarvis) {
            Position position = JarvisService.calculateNextShot(sea2);
            shoot(p1, sea2, position.getX(), position.getY());
        } else {
            gameState.setPlayerToFire(p1);
        }
    }

    private static void shoot(Player player, Sea sea, int X, int Y) {
        boolean hit = sea.shoot(X, Y);
        player.incrementShots();
        if(hit){
           player.incrementHits();
        }
    }
}
