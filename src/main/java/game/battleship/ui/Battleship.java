package game.battleship.ui;

import game.battleship.model.GameState;
import game.battleship.model.Player;
import game.battleship.model.Sea;

import javax.swing.*;
import java.awt.*;

/**
 * Developer: Ben Oeyen
 * Date: 16/02/2017
 */
public class Battleship {

    private static JFrame frame;

    public static void main(String[] args) {

        frame = new JFrame("Battleship");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        String nameP1 = getStringInput("Player 1 name"); //JOptionPane.showInputDialog("Player 1 name");
        String nameP2 = getStringInput("Player 2 name"); //
        int gridSize = 10; //getIntInput("Grid size"); //JOptionPane.showInputDialog("Grid Size");

        GameState gameState = new GameState(gridSize,nameP1,nameP2);

        showState(gameState);
    }

    private static String getStringInput(String messsage) {
        //TODO add error handling
        return JOptionPane.showInputDialog(messsage);
    }

    private static int getIntInput(String messsage) {
        //TODO add error handling
        return Integer.parseInt(JOptionPane.showInputDialog(messsage));
    }


    private static void showState(GameState gameState) {
        JPanel jPanel = new JPanel(new BorderLayout(0,0));
        jPanel.add(getPlayerSide(gameState.getP1(), gameState.getSea1(), Color.GREEN), BorderLayout.WEST);
        jPanel.add(getPlayerSide(gameState.getP2(), gameState.getSea2(), Color.RED), BorderLayout.EAST);
        frame.setContentPane(jPanel);
        frame.pack();
        frame.setVisible(true);
    }


    private static JPanel getPlayerSide(Player player, Sea sea, Color color){
        JPanel playerSide = new JPanel();

        playerSide.setLayout(new BorderLayout());
        playerSide.setBorder(BorderFactory.createMatteBorder(5,5,5,5, color));
        playerSide.add( getPlayerLabel(player), BorderLayout.NORTH);

        JPanel seaPanel = new JPanel();
        seaPanel.setBackground(new Color(84, 147, 175));
        seaPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        seaPanel.setLayout(new GridLayout(sea.getWidth(),sea.getHeight(), 5, 5));
        for(int i= 0 ; i< 10*10; i++){
            JPanel seaTile = new JPanel();
            seaTile.setBackground(new Color(0, 65, 94));
            seaTile.setPreferredSize(new Dimension(50,50));
            seaPanel.add(seaTile);
        }

        playerSide.add( seaPanel ,BorderLayout.SOUTH);

        return playerSide;
    }


    private static JTextArea getPlayerLabel(Player player) {
        JTextArea playerLabel = new JTextArea("Player: " + player.getName() + "\nHITS: " + player.getHits() + "\nSHOTS: " + player.getShots() + "\nACCURACY: " + player.getHitPercentage() + "%");
        playerLabel.setBackground(new Color(0, 65, 94));
        playerLabel.setForeground(new Color(255,255,255));
        playerLabel.setMargin(new Insets(5,5,5,5));
        return playerLabel;
    }

}
