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
    private Orientation orientation;

    public Ship(int length, Orientation orientation) {
        this.length = length;
        this.orientation = orientation;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void rotate() {
        switch (orientation) {
            case HORIZONTAL:
                this.orientation = Orientation.VERTICAL;
                break;
            case VERTICAL:
                this.orientation = Orientation.HORIZONTAL;
                break;
        }

    }

}
