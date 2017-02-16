package game.battleship.ui;

import game.battleship.model.GameState;

import javax.swing.*;

/**
 * Developer: Ben Oeyen
 * Date: 16/02/2017
 */
public class Battleship {


    public static void main(String[] args) {

        String nameP1 = JOptionPane.showInputDialog("Player 1 name");
        String nameP2 = JOptionPane.showInputDialog("Player 2 name");
        String gridSize = JOptionPane.showInputDialog("Grid Size");

        GameState gameState = new GameState(Integer.parseInt(gridSize),nameP1,nameP2);

        JOptionPane.showMessageDialog(null, "Done");
    }


}
