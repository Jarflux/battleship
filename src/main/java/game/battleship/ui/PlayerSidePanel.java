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
public class PlayerSidePanel extends JPanel {

    public PlayerSidePanel(Player player, Sea ownSea, Sea enemySea, Color color) {
        super();

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, color));
        add(new PlayerLabel(player), BorderLayout.NORTH);

        JPanel seaPanel = new JPanel(new BorderLayout(0, 0));
        if (BattleshipFrame.DEBUG) {
            seaPanel.add(new SeaPanel(ownSea, player), BorderLayout.NORTH);
        }

        seaPanel.add(new FiringPanel(enemySea, player), BorderLayout.SOUTH);
        add(seaPanel);
    }
}


