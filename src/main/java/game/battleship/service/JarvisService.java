package game.battleship.service;

import game.battleship.model.Position;
import game.battleship.model.Sea;

import java.util.List;
import java.util.Random;

/**
 * Developer: Ben Oeyen
 * Date: 25/02/2017
 */
public class JarvisService {

    public static Position calculateNextShot(Sea sea){
        List<Position> emptyPositionList = sea.getUnShotPositions();
        Random random = new Random();
        return emptyPositionList.get(random.nextInt(emptyPositionList.size()));

    }
}
