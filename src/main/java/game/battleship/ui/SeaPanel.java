package game.battleship.ui;

import game.battleship.model.Player;
import game.battleship.model.Sea;

import javax.swing.*;
import java.awt.*;

/**
 * Developer: Ben Oeyen
 * Date: 20/02/2017
 */
public class SeaPanel extends JPanel{

    private static final String OCEAN = "src/main/resources/image/ocean.png";
    private static final String MISS = "src/main/resources/image/miss.png";
    private static final String HIT = "src/main/resources/image/hit.png";
    private static final String HITCLEAN = "src/main/resources/image/hitclean.png";

    public SeaPanel(Sea sea, Player player){
        super();
        setBackground(new Color(84, 147, 175));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setLayout(new GridLayout(sea.getGridSize(), sea.getGridSize(), 5, 5));
        AddShipTiles(sea, player);
    }

    private void AddShipTiles(final Sea sea, final Player player) {
        for (int j = 0; j < sea.getGridSize(); j++) {
            for (int i = 0; i < sea.getGridSize(); i++) {
                JPanel shipTile = new JPanel(new BorderLayout(0, 0));
                Sea.State seaState = sea.getState(i, j);
                assignStylingToShipTile(shipTile, seaState);
                shipTile.setPreferredSize(new Dimension(50, 50));
                add(shipTile);
            }
        }
    }

    private void assignStylingToShipTile(JPanel seaTile, Sea.State seaState) {
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

    private JLabel getTile(String filename) {
        ImageIcon image = new ImageIcon(filename);
        return new JLabel("", image, JLabel.CENTER);
    }
}
