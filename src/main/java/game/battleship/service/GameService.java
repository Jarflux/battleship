package game.battleship.service;

import game.battleship.model.GameState;

/**
 * Developer: Ben Oeyen
 * Date: 21/02/2017
 */
public class GameService {


    public static void newGame(int gridsize, String playerName1, String playerName2){
        GameState.getInstance().newGame(gridsize, playerName1, playerName2);
        ShipService.addRandomShips();
    }
}
