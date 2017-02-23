package game.battleship.ui;

import game.battleship.model.HighScore;
import game.battleship.service.GameService;
import game.battleship.service.HighScoreService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
                String nameP1 = getStringInput("Player 1 name");
                String nameP2 = getStringInput("Player 2 name");
                int gridSize = getIntInput("Grid size");
                GameService.newGame(gridSize, nameP1, nameP2);
                BattleshipFrame.getInstance().showState();
            }
        });
        return jMenuItem;
    }

    private JMenuItem buildSaveGameMenuItem() {
        JMenuItem jMenuItem = new JMenuItem("Save Game");
        return jMenuItem;
    }

    private JMenuItem buildHighScoreMenuItem() {
        JMenuItem jMenuItem = new JMenuItem("Highscores");
        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<HighScore> highScores = HighScoreService.getAllHighscores();
                String message = "";
                if (highScores != null && !highScores.isEmpty()) {
                    int ranking = 0;
                    for (HighScore highScore : highScores) {
                        message += "" + ++ranking + "\t" + highScore.getPlayerName() + "\t\t" + highScore.getScore() + "\n";
                    }
                } else {
                    message = "No High Scores";
                }

                JOptionPane.showMessageDialog(BattleshipFrame.getInstance(),
                        message,
                        "Highscores",
                        JOptionPane.PLAIN_MESSAGE);

            }
        });
        return jMenuItem;
    }

    private JMenuItem buildLoadGameMenuItem() {
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

    private static String getStringInput(String messsage) {
        //TODO add error handling
        return JOptionPane.showInputDialog(messsage);
    }

    private static int getIntInput(String messsage) {
        //TODO add error handling
        return Integer.parseInt(JOptionPane.showInputDialog(messsage));
    }
}
