package game.battleship.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static game.battleship.model.Sea.State.*;

/**
 * Developer: Ben Oeyen
 * Date: 16/02/2017
 */

public class Sea implements Serializable {

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
                setState(position, MISS);
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

    public int getGridSize() {
        return grid.length;
    }

    public List<Position> getUnShotPositions() {
        List<Position> emptyPositionList = new ArrayList<>();
        for (int X = 0; X < grid.length; X++) {
            for (int Y = 0; Y < grid[0].length; Y++) {
                if (!MISS.equals(grid[X][Y])
                        && !State.HIT.equals(grid[X][Y])) {
                    emptyPositionList.add(new Position(X, Y));
                }
            }
        }
        return emptyPositionList;
    }

    public List<Position> getUnShotShipPositions() {
        List<Position> unshotShipPositionList = new ArrayList<>();
        for (int X = 0; X < grid.length; X++) {
            for (int Y = 0; Y < grid[0].length; Y++) {
                if (State.SHIP.equals(grid[X][Y])) {
                    unshotShipPositionList.add(new Position(X, Y));
                }
            }
        }
        return unshotShipPositionList;
    }


    public List<Position> getUnShotPositionsWithPotential() {
        List<Position> unshotPotentialPositionList = new ArrayList<>();
        for (int X = 0; X < grid.length; X++) {
            for (int Y = 0; Y < grid[0].length; Y++) {
                if (State.HIT.equals(grid[X][Y])) {
                    unshotPotentialPositionList.addAll(getUnShotPositionsAroundPosition(new Position(X, Y)));
                }
            }
        }
        return unshotPotentialPositionList;
    }

    public List<Position> getUnShotPositionsAroundPosition(Position position) {
        List<Position> unshotPotentialPositionList = new ArrayList<>();
        AddIfValidUnshotPosition(unshotPotentialPositionList, position.getX(), position.getY() - 1);
        AddIfValidUnshotPosition(unshotPotentialPositionList, position.getX() + 1, position.getY());
        AddIfValidUnshotPosition(unshotPotentialPositionList, position.getX(), position.getY() + 1);
        AddIfValidUnshotPosition(unshotPotentialPositionList, position.getX() - 1, position.getY());
        return unshotPotentialPositionList;
    }

    private void AddIfValidUnshotPosition(List<Position> unshotPotentialPositionList, int x, int y) {
        Position left = new Position(x, y);
        if (isValidUnShotPosition(left)) {
            unshotPotentialPositionList.add(left);
        }
    }

    private boolean isValidUnShotPosition(Position position) {
        return position.getX() > 0
                && position.getX() < grid.length
                && position.getY() > 0
                && position.getY() < grid[0].length
                && grid[position.getX()][position.getY()] != HIT
                && grid[position.getX()][position.getY()] != MISS;
    }

}
