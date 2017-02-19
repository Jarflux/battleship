package game.battleship.ui;

import game.battleship.model.*;
import game.battleship.service.FiringService;
import game.battleship.service.VictoryService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static game.battleship.model.Sea.State.EMPTY;
import static game.battleship.model.Sea.State.SHIP;

/**
 * Developer: Ben Oeyen
 * Date: 16/02/2017
 */
public class Battleship {

    private static JFrame frame;
    private static GameState gameState;

    private static final boolean DEBUG = true;
    private static final String OCEAN = "src/main/resources/image/ocean.png";
    private static final String MISS = "src/main/resources/image/miss.png";
    private static final String HIT = "src/main/resources/image/hit.png";
    private static final String HITCLEAN = "src/main/resources/image/hitclean.png";

    public static void main(String[] args) {
        frame = new JFrame("Battleship");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setJMenuBar(new CustomMenuBar());

        String nameP1 = "Ben"; //getStringInput("Player 1 name"); //JOptionPane.showInputDialog("Player 1 name");
        String nameP2 = "Elien"; //getStringInput("Player 2 name"); //
        int gridSize = 6; //getIntInput("Grid size"); //JOptionPane.showInputDialog("Grid Size");

        gameState = new GameState(gridSize, nameP1, nameP2);
        gameState.getSea1().addSchip(0, 4, new Ship(4, Ship.Orientation.HORIZONTAL));
        gameState.getSea2().addSchip(1, 1, new Ship(4, Ship.Orientation.VERTICAL));

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
        JPanel jPanel = new JPanel(new BorderLayout(0, 0));
        jPanel.add(getPlayerSide(gameState.getP1(), gameState.getSea1(), gameState.getSea2(), Color.GREEN), BorderLayout.WEST);
        jPanel.add(getPlayerSide(gameState.getP2(), gameState.getSea2(), gameState.getSea1(), Color.RED), BorderLayout.EAST);
        frame.setContentPane(jPanel);
        frame.pack();

        Player victor = VictoryService.getVictor(gameState);
        if (victor != null) {
            JOptionPane.showMessageDialog(frame, victor.getName() + " won the game!");
        }
    }


    private static JPanel getPlayerSide(Player player, Sea ownSea, Sea enemySea, Color color) {
        JPanel playerSide = new JPanel();

        playerSide.setLayout(new BorderLayout());
        playerSide.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, color));
        playerSide.add(getPlayerLabel(player), BorderLayout.NORTH);

        JPanel seaPanel = new JPanel(new BorderLayout(0, 0));
        if (DEBUG) {
            JPanel shipPanel = new JPanel();
            shipPanel.setBackground(new Color(84, 147, 175));
            shipPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            shipPanel.setLayout(new GridLayout(ownSea.getWidth(), ownSea.getHeight(), 5, 5));
            AddShipTiles(ownSea, player, shipPanel);
            seaPanel.add(shipPanel, BorderLayout.NORTH);
        }

        JPanel firingPanel = new JPanel();
        firingPanel.setBackground(new Color(84, 147, 175));
        firingPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        firingPanel.setLayout(new GridLayout(enemySea.getWidth(), enemySea.getHeight(), 5, 5));
        AddFiringTiles(enemySea, player, firingPanel);
        seaPanel.add(firingPanel, BorderLayout.SOUTH);

        playerSide.add(seaPanel, BorderLayout.SOUTH);
        return playerSide;
    }

    private static void AddFiringTiles(final Sea sea, final Player player, JPanel seaPanel) {
        for (int j = 0; j < sea.getHeight(); j++) {
            for (int i = 0; i < sea.getWidth(); i++) {
                JPanel seaTile = new JPanel(new BorderLayout(0, 0));
                Sea.State seaState = sea.getState(i, j);
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
                FiringService.shoot(gameState, player, i, j);
                showState();
            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {
                ((JPanel) e.getComponent()).setBorder(BorderFactory.createEmptyBorder());
            }

            public void mouseEntered(MouseEvent e) {
                ((JPanel) e.getComponent()).setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.YELLOW));
                ((JPanel) e.getComponent()).setToolTipText("x: " + i + " y:" + j);
            }
        };
    }

    private static void assignStylingToFiringTile(JPanel seaTile, Sea.State seaState, Player player, int coordinateX, int coordinateY) {
        switch (seaState) {
            case HIT:
                seaTile.add(getTile(HIT), BorderLayout.CENTER);
                break;
            case MISS:
                seaTile.add(getTile(MISS), BorderLayout.CENTER);
                break;
            default:
                seaTile.add(getTile(OCEAN), BorderLayout.CENTER);
                if (gameState.getPlayerToFire().equals(player) && VictoryService.getVictor(gameState) == null) {
                    seaTile.addMouseListener(buildMouseListener(player, coordinateX, coordinateY));
                }
        }
    }

    private static JLabel getTile(String filename) {
        ImageIcon image = new ImageIcon(filename);
        return new JLabel("", image, JLabel.CENTER);
    }


    private static JTextArea getPlayerLabel(Player player) {
        String turnIndicator = "";
        if (gameState.getPlayerToFire().equals(player)) {
            turnIndicator = " <-- ";
        }
        JTextArea playerLabel = new JTextArea("PLAYER: " + player.getName() + turnIndicator + "\nHITS: " + player.getHits() + "\nSHOTS: " + player.getShots() + "\nACCURACY: " + player.getHitPercentage() + "%");
        playerLabel.setBackground(new Color(0, 65, 94));
        playerLabel.setForeground(new Color(255, 255, 255));
        playerLabel.setMargin(new Insets(5, 5, 5, 5));
        return playerLabel;
    }

    // Code below can be removed when the debug view is no longer required

    private static void AddShipTiles(final Sea sea, final Player player, JPanel seaPanel) {
        for (int j = 0; j < sea.getHeight(); j++) {
            for (int i = 0; i < sea.getWidth(); i++) {
                JPanel shipTile = new JPanel(new BorderLayout(0, 0));
                Sea.State seaState = sea.getState(i, j);
                assignStylingToShipTile(shipTile, seaState);
                shipTile.setPreferredSize(new Dimension(50, 50));
                seaPanel.add(shipTile);
            }
        }
    }

    private static void assignStylingToShipTile(JPanel seaTile, Sea.State seaState) {
        switch (seaState) {
            case HIT:
                JLabel hitLabel = getTile(HITCLEAN);
                hitLabel.setOpaque(true);
                hitLabel.setBackground(Color.gray);
                seaTile.add(hitLabel, BorderLayout.CENTER);
                break;
            case MISS:
                seaTile.add(getTile(MISS), BorderLayout.CENTER);
                break;
            case SHIP:
                seaTile.setBackground(Color.gray);
                break;
            case EMPTY:
                seaTile.add(getTile(OCEAN), BorderLayout.CENTER);
        }
    }


}
