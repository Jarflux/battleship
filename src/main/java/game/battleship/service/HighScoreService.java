package game.battleship.service;

import game.battleship.model.HighScore;
import game.battleship.model.Player;
import game.battleship.repository.HighScoreRepository;

import java.util.Collections;
import java.util.List;

/**
 * Developer: Ben Oeyen
 * Date: 19/02/2017
 */
public class HighScoreService {

    public List<HighScore> getAllHighscores(){
        return HighScoreRepository.loadHighScores();
    }

    public void addScoreToHighScore(Player player){
        List<HighScore> highScores = HighScoreRepository.loadHighScores();
        highScores.add(new HighScore(player.getName(), player.getScore()));
        Collections.sort(highScores);
        HighScoreRepository.saveHighScores(highScores);
    }

}
