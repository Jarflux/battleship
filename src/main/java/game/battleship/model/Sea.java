package game.battleship.model;

import static game.battleship.model.Sea.State.SHIP;

/**
 * Developer: Ben Oeyen
 * Date: 16/02/2017
 */

// todo eens uitleggen hoe je hieraan komt, na skiverlof :)
//het aanmaken van Het spelveld + toevoegen van Boten + Bekijken of er nog boten aanwezig zijn op het speelveld...

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

    private void setState(int X, int Y, State state) {
        if (grid != null) {
            grid[X][Y] = state;
        }
    }

    public boolean fire(int X, int Y) {
        switch (grid[X][Y]) {
            case SHIP:
                setState(X, Y, State.HIT);
                return true;
            default:
                setState(X, Y, State.MISS);
                return false;
        }
    }

    public void addSchip(int X, int Y, Ship ship) {
        switch (ship.getOrientation()) {
            case HORIZONTAL:
                for (int i = 0; i < ship.getLength(); i++) {
                    setState(X + i, Y, SHIP);
                }
                break;
            case VERTICAL:
                for (int i = 0; i < ship.getLength(); i++) {
                    setState(X, Y + i, SHIP);
                }
                break;
        }
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


}
