package game.battleship.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Developer: Ben Oeyen
 * Date: 25/02/2017
 */
public class ShipConfig implements Serializable {

    private ArrayList<Ship> ships;
    private ArrayList<Ship> defaultShips;

    public ShipConfig() {
        defaultShips = new ArrayList<>();
        defaultShips.add(new Ship(5));
        defaultShips.add(new Ship(4));
        defaultShips.add(new Ship(3));
        defaultShips.add(new Ship(3));
        defaultShips.add(new Ship(2));
        resetToDefault();
    }

    public void resetToDefault(){
        ships = new ArrayList<>(defaultShips);
    }

    public void reset(){
        ships = new ArrayList<>();
    }

    public void removeShip(Ship ship){
        ships.remove(ship);
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }
}
