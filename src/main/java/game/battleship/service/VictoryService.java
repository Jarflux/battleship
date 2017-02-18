package game.battleship.service;

import game.battleship.model.GameState;
import game.battleship.model.Player;

/**
 * Developer: Ben Oeyen
 * Date: 18/02/2017
 */
public class VictoryService {

    public static Player getVictor(GameState gameState){
        if(!gameState.getSea1().containsActiveShip()){
            return gameState.getP2();
        }else if(!gameState.getSea2().containsActiveShip()){
            return gameState.getP1();
        }
        return null;
    }

}
