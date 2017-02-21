package game.battleship.ui;

import game.battleship.model.GameState;
import game.battleship.service.GameService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Developer: Ben Oeyen
 * Date: 17/02/2017
 */
public class CustomMenuBar extends JMenuBar {

    public CustomMenuBar() {
        super();
        JMenu menu = new JMenu("Battleship");
        this.add(menu);
        menu.add(buildNewGameMenuItem());
        menu.add(buildSaveGameMenuItem());
        menu.add(buildLoadGameMenuItem());
        menu.addSeparator();
        menu.add(buildHighScoreMenuItem());
        menu.addSeparator();
        menu.add(buildExitGameMenuItem());
    }

    private JMenuItem buildNewGameMenuItem() {
        JMenuItem jMenuItem = new JMenuItem("New Game");
        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameP1 = "Ben"; //getStringInput("Player 1 name"); //JOptionPane.showInputDialog("Player 1 name");
                String nameP2 = "Elien"; //getStringInput("Player 2 name"); //
                int gridSize = 6; //getIntInput("Grid size"); //JOptionPane.showInputDialog("Grid Size");
                GameService.newGame(gridSize,nameP1, nameP2);
                BattleshipFrame.getInstance().showState();
            }
        });
        return jMenuItem;
    }


    private JMenuItem buildSaveGameMenuItem() {
        JMenuItem jMenuItem = new JMenuItem("Save Game");
        return jMenuItem;
    }

    private JMenuItem buildLoadGameMenuItem() {
        JMenuItem jMenuItem = new JMenuItem("Highscores");
        return jMenuItem;
    }

    private JMenuItem buildHighScoreMenuItem() {
        JMenuItem jMenuItem = new JMenuItem("Load Game");
        return jMenuItem;
    }

    private JMenuItem buildExitGameMenuItem() {
        JMenuItem jMenuItem = new JMenuItem("Exit Game");
        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return jMenuItem;
    }
}
