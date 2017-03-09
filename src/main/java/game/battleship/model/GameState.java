package game.battleship.model;

import java.io.Serializable;

/**
 * Developer: Gilles Plaquet
 * Date: 16/02/2017
 */

public class GameState implements Serializable {
    private static GameState gameState;
    private ShipConfig shipConfig;
    private Player p1;
    private Player p2;
    private Sea sea1;
    private Sea sea2;
    private Player playerToFire;
    private GameMode gameMode;

    public enum GameMode {
        CLASSIC,
        REPEAT,
        SALVO;
    }

    public static GameState getInstance(){
        if(gameState == null){
            gameState = new GameState();
        }
        return gameState;
    }

    private GameState() {
    }

    public void newGame(int gridSize, Player player1, Player player2 ){
        shipConfig = new ShipConfig();
        sea1 = new Sea(gridSize);
        sea2 = new Sea(gridSize);
        p1 = player1;
        p2 = player2;
        playerToFire = p1;
    }

    public void loadGame(GameState gameState){
        this.p1 = gameState.getP1();
        this.p2 = gameState.getP2();
        this.sea1 = gameState.getSea1();
        this.sea2 = gameState.getSea2();
        this.playerToFire = gameState.getPlayerToFire();
        this.shipConfig = gameState.getShipConfig();
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

    public ShipConfig getShipConfig() {
        return shipConfig;
    }

    public Player getVictor() {
        if(!gameState.getSea1().containsActiveShip()){
            return p2;
        }else if(!gameState.getSea2().containsActiveShip()){
            return p1;
        }
        return null;
    }

}
