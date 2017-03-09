package game.battleship.ui;

import game.battleship.model.HighScore;
import game.battleship.model.Jarvis;
import game.battleship.service.GameService;
import game.battleship.service.HighScoreService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Developer: Gilles Plaquet
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
                int gameMode = getIntInput("[1] Player vs Player\n[2] Player vs Easy AI\n[3] Player vs Regular AI\n[4] Player vs Hard AI");
                String nameP1;
                String nameP2;
                switch(gameMode){
                    case 1:
                        nameP1 = getStringInput("Player 1 name");
                        nameP2 = getStringInput("Player 2 name");
                        GameService.newGamePlayerVsPlayer(10, nameP1, nameP2);
                        break;
                    case 2:
                        nameP1 = getStringInput("Player 1 name");
                        GameService.newGamePlayerVsJarvis(10, nameP1, Jarvis.IntelligenceLevel.STUPID);
                        break;
                    case 3:
                        nameP1 = getStringInput("Player 1 name");
                        GameService.newGamePlayerVsJarvis(10, nameP1, Jarvis.IntelligenceLevel.REGULAR);
                        break;
                    case 4:
                        nameP1 = getStringInput("Player 1 name");
                        GameService.newGamePlayerVsJarvis(10, nameP1, Jarvis.IntelligenceLevel.SMART);
                        break;
                }
                BattleshipFrame.getInstance().showState();
            }
        });
        return jMenuItem;
    }

    private JMenuItem buildSaveGameMenuItem() {
        JMenuItem jMenuItem = new JMenuItem("Save Game");
        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filename = getStringInput("Save game filename");
                GameService.saveGame(filename);
            }
        });
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
        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filename = getStringInput("Load game filename");
                GameService.loadGame(filename);
                BattleshipFrame.getInstance().showState();
            }
        });
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
