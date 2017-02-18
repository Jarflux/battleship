package game.battleship.model;

/**
 * Developer: Ben Oeyen
 * Date: 16/02/2017
 */

public class GameState {
    private Player p1;
    private Player p2;
    private Sea sea1;
    private Sea sea2;
    private Player playerToFire;
    private Player victor;

    public GameState( int gridSize, String nameP1, String nameP2 ){
        sea1 = new Sea(gridSize);
        sea2 = new Sea(gridSize);
        p1 = new Player(nameP1);
        p2 = new Player(nameP2);
        playerToFire = p1;
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

    public Player getPlayerToFire() {
        return playerToFire;
    }

    public void setPlayerToFire(Player playerToFire) {
        this.playerToFire = playerToFire;
    }

    public Player getVictor() {
        return victor;
    }

    public void setVictor(Player victor) {
        this.victor = victor;
    }
}
