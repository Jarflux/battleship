package game.battleship.ui;

import game.battleship.model.GameState;
import game.battleship.model.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Developer: Ben Oeyen
 * Date: 20/02/2017
 */
public class PlayerLabel extends JLabel{

    public PlayerLabel(GameState gameState, Player player ) {
        super();
        String turnIndicator = "";
        if (gameState.getPlayerToFire().equals(player)) {
            turnIndicator = " <-- ";
        }
        JTextArea playerLabel = new JTextArea("PLAYER: " + player.getName() + turnIndicator + "\nHITS: " + player.getHits() + "\nSHOTS: " + player.getShots() + "\nACCURACY: " + player.getHitPercentage() + "%");
        playerLabel.setBackground(new Color(0, 65, 94));
        playerLabel.setForeground(new Color(255, 255, 255));
        playerLabel.setMargin(new Insets(5, 5, 5, 5));
    }
}
