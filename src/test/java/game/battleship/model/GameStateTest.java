package game.battleship.model;

import game.battleship.repository.HighScoreRepository;
import game.battleship.service.HighScoreService;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Developer: Ben Oeyen
 * Date: 19/02/2017
 */

public class GameStateTest {

    @Test
    public void startNewGame() {
        GameState.getInstance().newGame(7, new Player("Ben"), new Player("Elien"));
        assertThat(GameState.getInstance().getPlayerToFire().getName()).isEqualTo("Ben");
    }

}
