package game.battleship.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Developer: Ben Oeyen
 * Date: 16/02/2017
 */
public class Sea {

    List<List<SeaState>> grid;


    public Sea(int gridSize) {
        grid = new ArrayList<List<SeaState>>();
        for (int i = 0; i < gridSize; i++) {
            ArrayList<SeaState> column = new ArrayList<SeaState>();
            for (int j = 0; j < gridSize; j++) {
                column.add(SeaState.EMPTY);
            }
            grid.add(column);
        }
    }

    public SeaState getState(int coordinateX, int coordinateY) {
        if (grid != null && grid.get(coordinateX) != null && grid.get(coordinateX).get(coordinateY) != null) {
            return grid.get(coordinateX).get(coordinateY);
        }
        return SeaState.EMPTY;
    }

    public void setState(int coordinateX, int coordinateY, SeaState seaState) {
        grid.get(coordinateX).set(coordinateY, seaState);
    }

    public boolean fire(int coordinateX, int coordinateY) {
        switch (grid.get(coordinateX).get(coordinateY)) {
            case SHIP:
                setState(coordinateX, coordinateY, SeaState.HIT);
                return true;
            default:
                setState(coordinateX, coordinateY, SeaState.MISS);
                return false;
        }
    }

    public void addSchip(int coordinateX, int coordinateY, Ship ship) {
        switch (ship.getOrientation()) {
            case HORIZONTAL:
                for (int i = 0; i < ship.getLength(); i++) {
                    setState(coordinateX + i, coordinateY, SeaState.SHIP);
                }
                break;
            case VERTICAL:
                for (int i = 0; i < ship.getLength(); i++) {
                    setState(coordinateX, coordinateY + 1, SeaState.SHIP);
                }
                break;
        }
    }

    public int getWidth() {
        return grid.size();
    }

    public int getHeight() {
        return grid.get(0).size();
    }
}
