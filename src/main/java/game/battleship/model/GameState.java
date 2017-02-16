package game.battleship.model;

/**
 * Developer: Ben Oeyen
 * Date: 16/02/2017
 */
// test test MOOIE TEST HOOR ;-)
public class GameState {

    Player p1;
    Player p2;

    Sea sea1;
    Sea sea2;

    public GameState( int gridSize, String nameP1, String nameP2 ){
        sea1 = new Sea(gridSize);
        sea2 = new Sea(gridSize);
        p1 = new Player(nameP1);
        p2 = new Player(nameP2);
    }


}
