package game.battleship.service;

import game.battleship.model.GameState;
import game.battleship.model.Ship;

/**
 * Developer: Ben Oeyen
 * Date: 21/02/2017
 */
public class ShipService {

    public static void addRandomShips(){
        GameState.getInstance().getSea1().addSchip(1, 1, new Ship(3, Ship.Orientation.HORIZONTAL));
        GameState.getInstance().getSea2().addSchip(1, 1, new Ship(5, Ship.Orientation.VERTICAL));
    }

}
