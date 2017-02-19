package game.battleship.model;

/**
 * Developer: Ben Oeyen
 * Date: 19/02/2017
 */
public class HighScore {
    private String playerName;
    private double score;

    public HighScore(String playerName, double score) {
        this.playerName = playerName;
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
