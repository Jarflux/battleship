package game.battleship.model;

import static java.lang.Integer.compare;

/**
 * Developer: Ben Oeyen
 * Date: 19/02/2017
 */
public class HighScore implements Comparable<HighScore> {
    private String playerName;
    private int score;

    public HighScore(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(HighScore o) {
        return compare(this.getScore(), o.getScore());
    }
}
