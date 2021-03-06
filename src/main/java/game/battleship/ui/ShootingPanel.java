package game.battleship.ui;

import game.battleship.model.GameState;
import game.battleship.model.Player;
import game.battleship.model.Position;
import game.battleship.model.Sea;
import game.battleship.service.ShootingService;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;

/**
 * Developer: Ben Oeyen
 * Date: 20/02/2017
 */
public class ShootingPanel extends JPanel {

    private static final String OCEAN = "/image/ocean.png";
    private static final String MISS = "/image/miss.png";
    private static final String HIT = "/image/hit.png";

    public ShootingPanel(Sea enemySea, Player player) {
        super();
        setBackground(new Color(84, 147, 175));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setLayout(new GridLayout(enemySea.getGridSize(), enemySea.getGridSize(), 5, 5));
        AddFiringTiles(enemySea, player);
    }

    private void AddFiringTiles(final Sea sea, final Player player) {
        for (int Y = 0; Y < sea.getGridSize(); Y++) {
            for (int X = 0; X < sea.getGridSize(); X++) {
                JPanel seaTile = new JPanel(new BorderLayout(0, 0));
                Sea.State seaState = sea.getState(X, Y);
                assignStylingToFiringTile(seaTile, seaState, player, new Position(X, Y));
                seaTile.setPreferredSize(new Dimension(50, 50));
                add(seaTile);
            }
        }
    }

    private MouseListener buildMouseListener(final Player player, final Position position) {
        return new MouseListener() {
            public void mouseClicked(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {
                boolean hit = ShootingService.shoot(GameState.getInstance(), player, position);
                if(hit){
                    playSound("hit-shorter");
                }else{
                    playSound("miss");
                }
                BattleshipFrame.getInstance().showState();
            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {
                ((JPanel) e.getComponent()).setBorder(BorderFactory.createEmptyBorder());
            }

            public void mouseEntered(MouseEvent e) {
                ((JPanel) e.getComponent()).setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.YELLOW));
                ((JPanel) e.getComponent()).setToolTipText("x: " + position.getX() + " y:" + position.getY());
            }
        };
    }

    private void assignStylingToFiringTile(JPanel seaTile, Sea.State seaState, Player player, Position position) {
        switch (seaState) {
            case HIT:
                seaTile.add(getTile(HIT), BorderLayout.CENTER);
                break;
            case MISS:
                seaTile.add(getTile(MISS), BorderLayout.CENTER);
                break;
            case SHIP:
                if (GameState.getInstance().getVictor() != null) {
                    seaTile.setBackground(Color.gray);
                }
            default:
                if (Sea.State.SHIP.equals(seaState) && GameState.getInstance().getVictor() != null) {
                    seaTile.setBackground(Color.gray);
                } else {
                    seaTile.add(getTile(OCEAN), BorderLayout.CENTER);
                    if (GameState.getInstance().getPlayerToFire().equals(player) && GameState.getInstance().getVictor() == null) {
                        seaTile.addMouseListener(buildMouseListener(player, position));
                    }
                }
        }
    }

    private JLabel getTile(String filename) {
        ImageIcon image = new ImageIcon(ShootingPanel.class.getResource(filename));
        return new JLabel("", image, JLabel.CENTER);
    }

    private void playSound(String filename) {
        try {
            URL soundName = ShootingPanel.class.getResource("/sounds/" + filename + ".wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundName);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }
}
