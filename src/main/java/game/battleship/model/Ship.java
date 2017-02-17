package game.battleship.model;

import sun.security.util.Length;

/**
 * Developer: Ben Oeyen
 * Date: 16/02/2017
 */
public class Ship {
    private int length;
    private Orientation orientation = Orientation.HORIZONTAL;

    public Ship(int length) {
        this.length = length;

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
        switch(orientation){
            case HORIZONTAL: this.orientation = Orientation.VERTICAL;break;
            case VERTICAL: this.orientation = Orientation.HORIZONTAL;break;
        }

    }
}
