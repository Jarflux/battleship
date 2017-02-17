package game.battleship.ui;

import game.battleship.model.*;
import game.battleship.service.FiringService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Developer: Ben Oeyen
 * Date: 16/02/2017
 */
public class Battleship {

    private static JFrame frame;
    private static GameState gameState;

    private static final boolean DEBUG = true;

    public static void main(String[] args) {
        frame = new JFrame("Battleship");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setJMenuBar(new CustomMenuBar());

        String nameP1 = "Ben"; //getStringInput("Player 1 name"); //JOptionPane.showInputDialog("Player 1 name");
        String nameP2 = "Elien"; //getStringInput("Player 2 name"); //
        int gridSize = 6; //getIntInput("Grid size"); //JOptionPane.showInputDialog("Grid Size");

        gameState = new GameState(gridSize,nameP1,nameP2);
        gameState.getSea1().addSchip(1,1, new Ship(3));
        gameState.getSea2().addSchip(1,1, new Ship(5));

        showState();
    }

    private static String getStringInput(String messsage) {
        //TODO add error handling
        return JOptionPane.showInputDialog(messsage);
    }

    private static int getIntInput(String messsage) {
        //TODO add error handling
        return Integer.parseInt(JOptionPane.showInputDialog(messsage));
    }


    private static void showState() {
        JPanel jPanel = new JPanel(new BorderLayout(0,0));
        jPanel.add(getPlayerSide(gameState.getP1(), gameState.getSea1(),  gameState.getSea2(), Color.GREEN), BorderLayout.WEST);
        jPanel.add(getPlayerSide(gameState.getP2(), gameState.getSea2(), gameState.getSea1(), Color.RED), BorderLayout.EAST);
        frame.setContentPane(jPanel);
        frame.pack();
    }


    private static JPanel getPlayerSide(Player player, Sea ownSea, Sea enemySea, Color color){
        JPanel playerSide = new JPanel();

        playerSide.setLayout(new BorderLayout());
        playerSide.setBorder(BorderFactory.createMatteBorder(5,5,5,5, color));
        playerSide.add( getPlayerLabel(player), BorderLayout.NORTH);

        JPanel seaPanel = new JPanel(new BorderLayout(0,0));
        if(DEBUG){
            JPanel shipPanel = new JPanel();
            shipPanel.setBackground(new Color(84, 147, 175));
            shipPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
            shipPanel.setLayout(new GridLayout(ownSea.getWidth(),ownSea.getHeight(), 5, 5));
            AddShipTiles(ownSea, player, shipPanel);
            seaPanel.add(shipPanel ,BorderLayout.NORTH);
        }

        JPanel firingPanel = new JPanel();
        firingPanel.setBackground(new Color(84, 147, 175));
        firingPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        firingPanel.setLayout(new GridLayout(enemySea.getWidth(),enemySea.getHeight(), 5, 5));
        AddFiringTiles(enemySea, player, firingPanel);
        seaPanel.add(firingPanel ,BorderLayout.SOUTH);

        playerSide.add(seaPanel, BorderLayout.SOUTH);
        return playerSide;
    }

    private static void AddShipTiles(final Sea sea, final Player player, JPanel seaPanel) {
        for(int i= 0 ; i< sea.getWidth(); i++){
            for(int j= 0 ; j< sea.getHeight(); j++){
                JPanel shipTiles = new JPanel();
                shipTiles.setBackground(new Color(0, 65, 94));
                SeaState seaState = sea.getState(i,j);
                assignStylingToShipTile(shipTiles, seaState, player, i, j);
                shipTiles.setPreferredSize(new Dimension(50, 50));
                seaPanel.add(shipTiles);
            }
        }
    }

    private static void assignStylingToShipTile(JPanel seaTile, SeaState seaState, Player player, int coordinateX, int coordinateY) {
        switch (seaState){
            case HIT:
                seaTile.setBackground(Color.red);
                break;
            case MISS:
                seaTile.setBackground(Color.white);
                break;
            case SHIP:
                seaTile.setBackground(Color.gray);
                break;
            case EMPTY:
                seaTile.setBackground(new Color(0, 65, 94));
        }
    }


    private static void AddFiringTiles(final Sea sea, final Player player, JPanel seaPanel) {
        for(int i= 0 ; i< sea.getWidth(); i++){
            for(int j= 0 ; j< sea.getHeight(); j++){
                JPanel seaTile = new JPanel();
                seaTile.setBackground(new Color(0, 65, 94));
                SeaState seaState = sea.getState(i,j);
                assignStylingToFiringTile(seaTile, seaState, player, i, j);
                seaTile.setPreferredSize(new Dimension(50, 50));
                seaPanel.add(seaTile);
            }
        }
    }

    private static MouseListener buildMouseListener(final Player player, final int i, final int j) {
        return new MouseListener() {
            public void mouseClicked(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {
                FiringService.shoot(gameState,player,i,j);
                showState();
            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {
                ((JPanel) e.getComponent()).setBorder(BorderFactory.createEmptyBorder());
            }

            public void mouseEntered(MouseEvent e) {
                ((JPanel) e.getComponent()).setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.YELLOW) );
            }

        };
    }

    private static void assignStylingToFiringTile(JPanel seaTile, SeaState seaState, Player player, int coordinateX, int coordinateY) {
        switch (seaState){
            case HIT:
                seaTile.setBackground(Color.red);
                break;
            case MISS:
                seaTile.setBackground(Color.white);
                break;
            default:
                seaTile.setBackground(new Color(0, 65, 94));
                seaTile.addMouseListener(buildMouseListener(player, coordinateX, coordinateY));
//                        ImageIcon image = new ImageIcon("image/pic1.jpg");
//                        JLabel label = new JLabel("", image, JLabel.CENTER);
//                        JPanel panel = new JPanel(new BorderLayout());
//                        panel.add( label, BorderLayout.CENTER );
        }
    }


    private static JTextArea getPlayerLabel(Player player) {
        JTextArea playerLabel = new JTextArea("PLAYER: " + player.getName() + "\nHITS: " + player.getHits() + "\nSHOTS: " + player.getShots() + "\nACCURACY: " + player.getHitPercentage() + "%");
        playerLabel.setBackground(new Color(0, 65, 94));
        playerLabel.setForeground(new Color(255,255,255));
        playerLabel.setMargin(new Insets(5,5,5,5));
        return playerLabel;
    }

}
