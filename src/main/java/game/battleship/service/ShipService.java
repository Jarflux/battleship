package game.battleship.service;

import game.battleship.model.GameState;
import game.battleship.model.Sea;
import game.battleship.model.Ship;
import game.battleship.model.ShipConfig;

import java.util.Random;

/**
 * Developer: Gilles Plaquet
 * Date: 21/02/2017
 */
public class ShipService {

    public static void randomizeShips() {
        placeShipsRandomOnSea(GameState.getInstance().getShipConfig(), GameState.getInstance().getSea1());
        placeShipsRandomOnSea(GameState.getInstance().getShipConfig(), GameState.getInstance().getSea2());
    }

    private static void placeShipsRandomOnSea(ShipConfig shipConfig, Sea sea) {
        Random random = new Random();
        for (Ship ship : shipConfig.getShips()) {
            boolean shipPlaced = false;
            while (!shipPlaced) {
                if (random.nextBoolean()) {
                    if (sea.getGridSize() == ship.getLength()) { // This prevents random.nextInt(0) which gives an exception
                        shipPlaced = sea.addSchip(0, random.nextInt(sea.getGridSize()), ship, Ship.Orientation.HORIZONTAL);
                    } else {
                        shipPlaced = sea.addSchip(random.nextInt(sea.getGridSize() - ship.getLength()), random.nextInt(sea.getGridSize()), ship, Ship.Orientation.HORIZONTAL);
                    }
                } else {
                    if (sea.getGridSize() == ship.getLength()) { // This prevents random.nextInt(0) which gives an exception
                        shipPlaced = sea.addSchip(random.nextInt(sea.getGridSize()), 0, ship, Ship.Orientation.VERTICAL);
                    } else {
                        shipPlaced = sea.addSchip(random.nextInt(sea.getGridSize()), random.nextInt(sea.getGridSize() - ship.getLength()), ship, Ship.Orientation.VERTICAL);
                    }
                }
            }
        }
    }

}
