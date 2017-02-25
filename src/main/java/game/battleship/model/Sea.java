package game.battleship.model;

import java.util.ArrayList;
import java.util.List;

import static game.battleship.model.Sea.State.SHIP;

/**
 * Developer: Ben Oeyen
 * Date: 16/02/2017
 */

public class Sea {

    public enum State {
        EMPTY,
        SHIP,
        HIT,
        MISS;
    }

    private State[][] grid;

    public Sea(int gridSize) {
        grid = new State[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = State.EMPTY;
            }
        }
    }


    public State getState(int coordinateX, int coordinateY) {
        if (grid != null) {
            return grid[coordinateX][coordinateY];
        }
        return State.EMPTY;
    }

    private void setState(Position position, State state) {
        if (grid != null) {
            grid[position.getX()][position.getY()] = state;
        }
    }

    public boolean shoot(Position position) {
        switch (grid[position.getX()][position.getY()]) {
            case SHIP:
                setState(position, State.HIT);
                return true;
            default:
                setState(position, State.MISS);
                return false;
        }
    }

    public boolean addSchip(int X, int Y, Ship ship, Ship.Orientation orientation) {
        switch (orientation) {
            case HORIZONTAL:
                for (int i = 0; i < ship.getLength(); i++) {
                    if (getState(X + i, Y).equals(SHIP)) {
                        return false;
                    }
                }
                for (int i = 0; i < ship.getLength(); i++) {
                    setState(new Position(X + i, Y), SHIP);
                }
                return true;
            case VERTICAL:
                for (int i = 0; i < ship.getLength(); i++) {
                    if (getState(X, Y + i).equals(SHIP)) {
                        return false;
                    }
                }
                for (int i = 0; i < ship.getLength(); i++) {
                    setState(new Position(X, Y + i), SHIP);
                }
                return true;
        }
        return false;
    }

    public boolean containsActiveShip() {
        for (State[] gridColumn : grid) {
            for (State gridCell : gridColumn) {
                if (gridCell.equals(SHIP)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getWidth() {
        return grid.length;
    }

    public int getHeight() {
        return grid[0].length;
    }

    public List<Position> getUnShotPositions() {
        List<Position> emptyPositionList = new ArrayList<>();
        for (int X = 0; X < grid.length; X++) {
            for (int y = 0; y < grid[0].length; y++) {
                if(!State.MISS.equals(grid[X][y])
                        && !State.HIT.equals(grid[X][y])){
                    emptyPositionList.add(new Position(X,y));
                }
            }
        }
        return emptyPositionList;
    }


}
