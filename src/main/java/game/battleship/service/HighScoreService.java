package game.battleship.service;

import game.battleship.model.HighScore;
import game.battleship.model.Jarvis;
import game.battleship.model.Player;
import game.battleship.repository.HighScoreRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Developer: Ben Oeyen
 * Date: 19/02/2017
 */
public class HighScoreService {

    public static List<HighScore> getAllHighscores() {
        return HighScoreRepository.loadHighScores();
    }

    public static void addScoreToHighScore(Player player) {
        if (!(player instanceof Jarvis)) {
            List<HighScore> highScores = HighScoreRepository.loadHighScores();
            highScores.add(new HighScore(player.getName(), player.getScore()));
            Collections.sort(highScores);
            int min = Math.min(highScores.size(), 10); // We use Math.min to determine the lowest number between current size and 10 to prevent IndexOutOfBoundsException
            highScores = new ArrayList<>(highScores.subList(0, min)); // sublist returns a 'RandomAccessSubList' which is not serializable so we create a new ArrayList
            HighScoreRepository.saveHighScores(highScores);
        }
    }

    public static void removeAllHighScores() {
        HighScoreRepository.deleteHighScores();
    }

}
