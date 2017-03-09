package game.battleship.model;

import java.io.Serializable;

/**
 * Developer: Gilles Plaquet
 * Date: 16/02/2017
 */

public class Player implements Serializable {
    private String name;
    private int shots = 0;
    private int hits = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getShots() {
        return shots;
    }

    public void incrementShots(){
        shots++;
    }

    public int getHits() {
        return hits;
    }

    public void incrementHits(){
        hits++;
    }

    public int getHitPercentage() {
        if (shots != 0) {
            return (int) (((double) hits) / ((double) shots) * 100);
        } else {
            return 0;
        }
    }

    public int getScore(){
        return getHitPercentage();  // Just a way to determine a score
    }


}
