package game.battleship.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Developer: Ben Oeyen
 * Date: 16/02/2017
 */
public class Sea {

    List<List<String>> grid;

    public Sea(int gridSize) {
        grid = new ArrayList<List<String>>();
        for(int i = 0 ; i < gridSize ; i++){
            ArrayList<String> column = new ArrayList<String>();
            for(int j = 0 ; j < gridSize ; j++){
                column.add("EMPTY");
            }
            grid.add(column);
        }
    }

    public String getState(int coordinateX, int coordinateY){
        if(grid != null && grid.get(coordinateX) != null && grid.get(coordinateX).get(coordinateY)!=null){
            return grid.get(coordinateX).get(coordinateY);
        }
        return "";
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
