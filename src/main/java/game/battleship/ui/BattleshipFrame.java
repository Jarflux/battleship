package game.battleship.ui;

import game.battleship.model.GameState;
import game.battleship.model.HighScore;
import game.battleship.model.Player;
import game.battleship.service.HighScoreService;

import javax.swing.*;
import java.awt.*;

/**
 * Developer: Gilles Plaquet
 * Date: 20/02/2017
 */
public class BattleshipFrame extends JFrame {

    public static final boolean DEBUG = false;

    private static final String COVER = "/image/cover.jpeg";

    private static BattleshipFrame frame;

    public static BattleshipFrame getInstance() {
        if (frame == null) {
            frame = new BattleshipFrame();
        }
        return frame;
    }

    private BattleshipFrame() {
        super("Battleship");
    }

    public void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setJMenuBar(new CustomMenuBar());
        showCover();
    }

    public void showCover() {
        ImageIcon image = new ImageIcon(BattleshipFrame.class.getResource(COVER));
        JLabel label = new JLabel("", image, JLabel.CENTER);
        JPanel jPanel = new JPanel();
        jPanel.add(label);
        setContentPane(jPanel);
        pack();
        setLocationRelativeTo(null);
    }

    public void showState() {
        JPanel jPanel = new JPanel(new BorderLayout(0, 0));
        jPanel.add(new PlayerSidePanel(GameState.getInstance().getP1(), GameState.getInstance().getSea1(), GameState.getInstance().getSea2(), Color.GREEN), BorderLayout.WEST);
        jPanel.add(new PlayerSidePanel(GameState.getInstance().getP2(), GameState.getInstance().getSea2(), GameState.getInstance().getSea1(), Color.RED), BorderLayout.EAST);
        setContentPane(jPanel);
        pack();

        Player victor = GameState.getInstance().getVictor();
        if (victor != null) {
            HighScoreService.addScoreToHighScore(victor);
            JOptionPane.showMessageDialog(this, victor.getName() + " won the game!");
        }
    }
}
