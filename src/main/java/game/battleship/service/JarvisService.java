package game.battleship.service;

import game.battleship.model.Jarvis;
import game.battleship.model.Position;
import game.battleship.model.Sea;

import java.util.List;
import java.util.Random;

/**
 * Developer: Gilles Plaquet
 * Date: 25/02/2017
 */
public class JarvisService {

    public static Position calculateNextShot(Sea sea, Jarvis.IntelligenceLevel intelligenceLevel){
        switch(intelligenceLevel){
            case REGULAR: return calculateNextShotForRegularJarvis(sea);
            case SMART: return calculateNextShotForSmartJarvis(sea);
            default: return calculateNextShotForStupidJarvis(sea);
        }
    }

    public static Position calculateNextShotForStupidJarvis(Sea sea){
        List<Position> unShotPositionList = sea.getUnShotPositions();
        Random random = new Random();
        return unShotPositionList.get(random.nextInt(unShotPositionList.size()));
    }


    public static Position calculateNextShotForRegularJarvis(Sea sea){
        List<Position> unShotPositionsNextToHitPositionsList = sea.getUnShotPositionsWithPotential();
        if(unShotPositionsNextToHitPositionsList!= null && !unShotPositionsNextToHitPositionsList.isEmpty()){
            Random random = new Random();
            return unShotPositionsNextToHitPositionsList.get(random.nextInt(unShotPositionsNextToHitPositionsList.size()));
        }else{
            return calculateNextShotForStupidJarvis(sea);
        }
    }

    public static Position calculateNextShotForSmartJarvis(Sea sea){
        Random random = new Random();
        int randomInt = random.nextInt(100);
        if(randomInt < 15){
            List<Position> unShotShipPositionsList = sea.getUnShotShipPositions();
            return unShotShipPositionsList.get(random.nextInt(unShotShipPositionsList.size()));
        }else{
            return calculateNextShotForRegularJarvis(sea);
        }
    }


}
