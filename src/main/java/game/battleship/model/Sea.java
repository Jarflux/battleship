package game.battleship.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Developer: Ben Oeyen
 * Date: 16/02/2017
 */
public class Sea {

    List<List<SeaState>> grid;

    public Sea(int gridSize) {
        grid = new ArrayList<List<SeaState>>();
        for(int i = 0 ; i < gridSize ; i++){
            ArrayList<SeaState> column = new ArrayList<SeaState>();
            for(int j = 0 ; j < gridSize ; j++){
                column.add(SeaState.getRandom());
            }
            grid.add(column);
        }
    }

    public SeaState getState(int coordinateX, int coordinateY){
        if(grid != null && grid.get(coordinateX) != null && grid.get(coordinateX).get(coordinateY)!=null){
            return grid.get(coordinateX).get(coordinateY);
        }
        return SeaState.EMPTY;
    }

    public void setState(int coordinateX, int coordinateY, String state){
        //TODO implement
    }

    public int getWidth() {
        return grid.size();
    }

    public int getHeight() {
        return grid.get(0).size();
    }
}
