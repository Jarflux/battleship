package game.battleship.service;

import game.battleship.model.*;

/**
 * Developer: Ben Oeyen
 * Date: 17/02/2017
 */
public class ShootingService {

    public static boolean shoot(GameState gameState, Player player, Position position){
        Player p1 = gameState.getP1();
        Player p2 = gameState.getP2();
        Player playerToFire = gameState.getPlayerToFire();

        if(p1.equals(player) && p1.equals(playerToFire)){
            return executeShotAndPossibleJarvisShot(gameState, player, position, p2, gameState.getSea2(), gameState.getSea1());
        }else if(p2.equals(player) && p2.equals(playerToFire)){
            return executeShotAndPossibleJarvisShot(gameState, player, position, p1, gameState.getSea1(), gameState.getSea2());
        }
        return false;
    }

    private static boolean executeShotAndPossibleJarvisShot(GameState gameState, Player player, Position position, Player opponentPlayer, Sea sea1, Sea sea2) {
        boolean hit = shoot(player, sea1, position);
        if (opponentPlayer instanceof Jarvis) {
            shoot(opponentPlayer, sea2, JarvisService.calculateNextShot(sea2, ((Jarvis) opponentPlayer).getIntelligenceLevel()));
        } else {
            gameState.setPlayerToFire(opponentPlayer);
        }
        return hit;
    }

    private static boolean shoot(Player player, Sea sea, Position position) {
        boolean hit = sea.shoot(position);
        player.incrementShots();
        if(hit){
           player.incrementHits();
        }
        return hit;
    }
}
