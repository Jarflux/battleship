package game.battleship.ui;

import game.battleship.model.GameState;

import javax.swing.*;

/**
 * Developer: Ben Oeyen
 * Date: 17/02/2017
 */
public class CustomMenuBar extends JMenuBar {

    public CustomMenuBar() {
        super();
        JMenu menu = new JMenu("Battleship");
        this.add(menu);
        JMenuItem jMenuItemNewGame = new JMenuItem("New Game");
        jMenuItemNewGame.addActionListener(event -> {
            String nameP1 = "Ben"; //getStringInput("Player 1 name"); //JOptionPane.showInputDialog("Player 1 name");
            String nameP2 = "Elien"; //getStringInput("Player 2 name"); //
            int gridSize = 6; //getIntInput("Grid size"); //JOptionPane.showInputDialog("Grid Size");
            GameState.getInstance().newGame(gridSize,nameP1, nameP2);
            BattleshipFrame.getInstance().showState();
        });

        menu.add(new JMenuItem("New Game"));
        menu.add(new JMenuItem("Save Game"));
        menu.add(new JMenuItem("Load Game"));
        menu.addSeparator();
        menu.add(new JMenuItem("Highscores"));
        menu.addSeparator();

        JMenuItem jMenuItem = new JMenuItem("Exit Game");
        jMenuItem.addActionListener(event -> System.exit(0));
        menu.add(new JMenuItem("Exit Game"));
    }
}
