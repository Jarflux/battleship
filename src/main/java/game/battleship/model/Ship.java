package game.battleship.model;

/**
 * Developer: Ben Oeyen
 * Date: 16/02/2017
 */

public class Ship {

    public enum Orientation {
        HORIZONTAL,
        VERTICAL;
    }

    private int length;

    public Ship(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

}
