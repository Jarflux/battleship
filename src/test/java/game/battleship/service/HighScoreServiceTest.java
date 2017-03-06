package game.battleship.service;

import game.battleship.model.Player;
import game.battleship.repository.HighScoreRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Developer: Ben Oeyen
 * Date: 19/02/2017
 */

public class HighScoreServiceTest {

    @BeforeClass
    public static void setHighScoreTestFileName(){
        HighScoreRepository.highScoreFilename = "src/resources/image/testhighscores.data";
    }

    @Before
    @After
    // This method wil run before and after each test to provide a clean start situation
    public void cleanHighScoreFile(){
        HighScoreRepository.deleteHighScores();
    }

    @Test
    public void testGetAllHighScores() {
        int size = HighScoreService.getAllHighscores().size();
        Player p = new Player("Ben");
        HighScoreService.addScoreToHighScore(p);
        assertThat(HighScoreService.getAllHighscores().size()).isEqualTo(size + 1);
    }

    @Test
    public void testOnlyTop10HighScoresIsSaved() {
        Player p = new Player("Ben");
        for(int i = 0; i < 20 ; i++){
            HighScoreService.addScoreToHighScore(p);
        }
        assertThat(HighScoreService.getAllHighscores().size()).isEqualTo(10);
    }

}
