package game.battleship.model;

/**
 * Developer: Ben Oeyen
 * Date: 16/02/2017
 */

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

    public Player getP1() {
        return p1;
    }

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public Player getP2() {
        return p2;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
    }

    public Sea getSea1() {
        return sea1;
    }

    public void setSea1(Sea sea1) {
        this.sea1 = sea1;
    }

    public Sea getSea2() {
        return sea2;
    }

    public void setSea2(Sea sea2) {
        this.sea2 = sea2;
    }

    public void shoot(Player player, int coordinateX, int coordinateY){
        if (p1.equals(player)){
            sea2.fire(coordinateX,coordinateY);
        }else{
            sea1.fire(coordinateX,coordinateY);
        }
    }
}
