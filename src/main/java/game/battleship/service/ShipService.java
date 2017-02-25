package game.battleship.service;

import game.battleship.model.GameState;
import game.battleship.model.Sea;
import game.battleship.model.Ship;
import game.battleship.model.ShipConfig;

import java.util.Random;

/**
 * Developer: Ben Oeyen
 * Date: 21/02/2017
 */
public class ShipService {

    public static void randomizeShips(){
        placeShipsRandomOnSea(GameState.getInstance().getShipConfig(), GameState.getInstance().getSea1());
        placeShipsRandomOnSea(GameState.getInstance().getShipConfig(), GameState.getInstance().getSea2());
    }

    private static void placeShipsRandomOnSea(ShipConfig shipConfig, Sea sea) {
        Random random = new Random();
        for(Ship ship: shipConfig.getShips()){
            boolean shipPlaced = false;
            while(!shipPlaced){
                if(random.nextBoolean()){
                    shipPlaced = sea.addSchip(random.nextInt(sea.getWidth() - ship.getLength()), random.nextInt(sea.getHeight()), ship, Ship.Orientation.HORIZONTAL);
                }else{
                    shipPlaced = sea.addSchip(random.nextInt(sea.getWidth()), random.nextInt(sea.getHeight() - ship.getLength()), ship, Ship.Orientation.VERTICAL);
                }
            }
        }
    }

}
