package game.battleship.model;

/**
 * Developer: Ben Oeyen
 * Date: 16/02/2017
 */

public class Player {
    private String name;
    private int number;
    private int shots = 0;
    private int hits = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getShots() {
        return shots;
    }

    public void setShots(int shots) {
        this.shots = shots;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public double getHitPercentage(){
        if(shots!=0){
            return ((double) hits) / ((double) shots);
        }else{
            return 0.0;
        }
    }

}
