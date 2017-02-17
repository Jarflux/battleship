package game.battleship.ui;

import javax.swing.*;

/**
 * Developer: Ben Oeyen
 * Date: 17/02/2017
 */
public class CustomMenuBar extends JMenuBar {

    public CustomMenuBar() {
        super();
        JMenu menu = new JMenu("Battleship");
        this.add(menu);
        menu.add(new JMenuItem("New Game"));
        menu.add(new JMenuItem("Save Game"));
        menu.add(new JMenuItem("Load Game"));
        menu.addSeparator();
        menu.add(new JMenuItem("Highscores"));
        menu.addSeparator();
        menu.add(new JMenuItem("Exit Game"));
    }
}
