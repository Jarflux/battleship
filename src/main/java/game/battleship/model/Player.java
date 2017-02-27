package game.battleship.model;

import java.io.Serializable;

/**
 * Developer: Ben Oeyen
 * Date: 16/02/2017
 */

public class Player implements Serializable {
    private int id;
    private String name;
    private int shots = 0;
    private int hits = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHitPercentage() {
        if (shots != 0) {
            return (int) (((double) hits) / ((double) shots) * 100);
        } else {
            return 0;
        }
    }

    public int getScore(){
        return 100 - shots + (5 * hits);  // Just a way to determine a score
    }


}
