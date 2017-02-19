package game.battleship.model;

import java.util.Random;

/**
 * Developer: Ben Oeyen
 * Date: 17/02/2017
 */
public enum SeaState {
    EMPTY,
    SHIP,
    HIT,
    MISS;

    public static SeaState getRandom(){
        //todo : waarom hier een random ?
        Random rd = new Random();
        switch(rd.nextInt(4)){
            case 0: return SeaState.MISS;
            case 1: return SeaState.HIT;
            case 2: return SeaState.SHIP;
            default: return SeaState.EMPTY;
        }
    }
}
