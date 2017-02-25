package game.battleship.ui;

import game.battleship.model.GameState;
import game.battleship.model.Player;
import game.battleship.model.Sea;
import game.battleship.service.ShootingService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Developer: Ben Oeyen
 * Date: 20/02/2017
 */
public class FiringPanel extends JPanel {

    private static final String OCEAN = "src/main/resources/image/ocean.png";
    private static final String MISS = "src/main/resources/image/miss.png";
    private static final String HIT = "src/main/resources/image/hit.png";

    public FiringPanel(Sea enemySea, Player player) {
        super();
        setBackground(new Color(84, 147, 175));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setLayout(new GridLayout(enemySea.getWidth(), enemySea.getHeight(), 5, 5));
        AddFiringTiles(enemySea, player);
    }

    private void AddFiringTiles(final Sea sea, final Player player) {
        for (int j = 0; j < sea.getHeight(); j++) {
            for (int i = 0; i < sea.getWidth(); i++) {
                JPanel seaTile = new JPanel(new BorderLayout(0, 0));
                Sea.State seaState = sea.getState(i, j);
                assignStylingToFiringTile(seaTile, seaState, player, i, j);
                seaTile.setPreferredSize(new Dimension(50, 50));
                add(seaTile);
            }
        }
    }

    private MouseListener buildMouseListener(final Player player, final int i, final int j) {
        return new MouseListener() {
            public void mouseClicked(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {
                ShootingService.shoot(GameState.getInstance(), player, i, j);
                BattleshipFrame.getInstance().showState();
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

    private void assignStylingToFiringTile(JPanel seaTile, Sea.State seaState, Player player, int coordinateX, int coordinateY) {
        switch (seaState) {
            case HIT:
                seaTile.add(getTile(HIT), BorderLayout.CENTER);
                break;
            case MISS:
                seaTile.add(getTile(MISS), BorderLayout.CENTER);
                break;
            default:
                seaTile.add(getTile(OCEAN), BorderLayout.CENTER);
                if (GameState.getInstance().getPlayerToFire().equals(player) && GameState.getInstance().getVictor() == null) {
                    seaTile.addMouseListener(buildMouseListener(player, coordinateX, coordinateY));
                }
        }
    }

    private JLabel getTile(String filename) {
        ImageIcon image = new ImageIcon(filename);
        return new JLabel("", image, JLabel.CENTER);
    }
}
