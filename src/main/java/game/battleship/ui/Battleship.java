package game.battleship.ui;

import game.battleship.model.GameState;
import game.battleship.model.Player;
import game.battleship.model.Sea;
import game.battleship.model.SeaState;

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
        AddSeaTiles(sea, seaPanel);
        playerSide.add( seaPanel ,BorderLayout.SOUTH);

        return playerSide;
    }

    private static void AddSeaTiles(Sea sea, JPanel seaPanel) {
        for(int i= 0 ; i< sea.getWidth(); i++){
            for(int j= 0 ; j< sea.getHeight(); j++){
                JPanel seaTile = new JPanel();
                seaTile.setBackground(new Color(0, 65, 94));
                SeaState seaState = sea.getState(i,j);
                assignStylingToSeaTile(seaTile, seaState);
                seaTile.setPreferredSize(new Dimension(50, 50));
                seaPanel.add(seaTile);
            }
        }
    }

    private static void assignStylingToSeaTile(JPanel seaTile, SeaState seaState) {
        switch (seaState){
            case EMPTY:
                seaTile.setBackground(new Color(0, 65, 94));
//                        ImageIcon image = new ImageIcon("image/pic1.jpg");
//                        JLabel label = new JLabel("", image, JLabel.CENTER);
//                        JPanel panel = new JPanel(new BorderLayout());
//                        panel.add( label, BorderLayout.CENTER );
                break;
            case HIT:
                seaTile.setBackground(Color.red);
                break;
            case MISS:
                seaTile.setBackground(Color.white);
                break;
            case SHIP:
                seaTile.setBackground(Color.gray);
                break;
        }
    }


    private static JTextArea getPlayerLabel(Player player) {
        JTextArea playerLabel = new JTextArea("Player: " + player.getName() + "\nHITS: " + player.getHits() + "\nSHOTS: " + player.getShots() + "\nACCURACY: " + player.getHitPercentage() + "%");
        playerLabel.setBackground(new Color(0, 65, 94));
        playerLabel.setForeground(new Color(255,255,255));
        playerLabel.setMargin(new Insets(5,5,5,5));
        return playerLabel;
    }

}
