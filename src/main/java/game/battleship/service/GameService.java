package game.battleship.service;

import game.battleship.model.GameState;
import game.battleship.model.Jarvis;
import game.battleship.model.Player;
import game.battleship.repository.SavedGameRepository;

/**
 * Developer: Gilles Plaquet
 * Date: 21/02/2017
 */
public class GameService {

    public static void newGamePlayerVsPlayer(int gridsize, String playerName1, String playerName2){
        GameState.getInstance().newGame(gridsize, new Player(playerName1), new Player(playerName2));
        ShipService.randomizeShips();
    }

    public static void newGamePlayerVsJarvis(int gridsize, String playerName1, Jarvis.IntelligenceLevel intelligenceLevel) {
        GameState.getInstance().newGame(gridsize, new Player(playerName1), new Jarvis(intelligenceLevel));
        ShipService.randomizeShips();
    }

    public static void loadGame(String savedGameFileName){
        GameState savedGameState = SavedGameRepository.loadGame(savedGameFileName);
        GameState.getInstance().loadGame(savedGameState);
    }

    public static void saveGame(String savedGameFileName){
        SavedGameRepository.saveGame(GameState.getInstance(), savedGameFileName);
    }

}
