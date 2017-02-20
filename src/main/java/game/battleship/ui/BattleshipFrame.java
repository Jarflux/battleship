package game.battleship.ui;

import game.battleship.model.GameState;
import game.battleship.model.Player;
import game.battleship.model.Sea;
import game.battleship.service.FiringService;
import game.battleship.service.VictoryService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Developer: Ben Oeyen
 * Date: 20/02/2017
 */
public class BattleshipFrame extends JFrame {

    public static final boolean DEBUG = true;

    private static BattleshipFrame frame;

    public static BattleshipFrame getInstance(){
        if(frame == null){
            frame = new BattleshipFrame();
        }
        return frame;
    }

    private BattleshipFrame(){
        super("Battleship");
    }

    public void initFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setJMenuBar(new CustomMenuBar());
    }

    public void showState(){
        JPanel jPanel = new JPanel(new BorderLayout(0, 0));
        jPanel.add(new PlayerSidePanel(GameState.getInstance().getP1(), GameState.getInstance().getSea1(), GameState.getInstance().getSea2(), Color.GREEN), BorderLayout.WEST);
        jPanel.add(new PlayerSidePanel(GameState.getInstance().getP2(), GameState.getInstance().getSea2(), GameState.getInstance().getSea1(), Color.RED), BorderLayout.EAST);
        setContentPane(jPanel);
        pack();

        Player victor = VictoryService.getVictor(GameState.getInstance());
        if (victor != null) {
            JOptionPane.showMessageDialog(this, victor.getName() + " won the game!");
        }
    }
}
