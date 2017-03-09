package game.battleship.ui;

import game.battleship.model.GameState;
import game.battleship.model.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Developer: Gilles Plaquet
 * Date: 20/02/2017
 */
public class PlayerLabel extends JTextArea{

    public PlayerLabel(Player player) {
        super();
        String turnIndicator = "";
        if (GameState.getInstance().getPlayerToFire().equals(player)) {
            turnIndicator = " <-- ";
        }
        setText("PLAYER: " + player.getName() + turnIndicator + "\nHITS: " + player.getHits() + "\nSHOTS: " + player.getShots() + "\nACCURACY: " + player.getHitPercentage() + "%");
        setBackground(new Color(0, 65, 94));
        setForeground(new Color(255, 255, 255));
        setMargin(new Insets(5, 5, 5, 5));
    }
}
