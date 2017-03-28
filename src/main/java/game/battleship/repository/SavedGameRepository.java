package game.battleship.repository;

import game.battleship.model.GameState;

import java.io.*;

/**
 * Developer: Ben Oeyen
 * Date: 16/02/2017
 */

public class SavedGameRepository {

    public static String SAVED_GAMES_DIRECTORY = "src/main/resources/savedgames";

    public static GameState loadGame(String savedGameFilename) {
        GameState gamestate = null;
        File f = new File(SAVED_GAMES_DIRECTORY + "/" + savedGameFilename + ".save");
        if (f.exists() && !f.isDirectory()) {
            try {
                FileInputStream fis = new FileInputStream(SAVED_GAMES_DIRECTORY + "/" + savedGameFilename + ".save");
                ObjectInputStream ois = new ObjectInputStream(fis);
                gamestate = (GameState) ois.readObject();
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return gamestate;
    }

    public static void saveGame(GameState gameState, String savedGameFilename) {
        makeSureDirectoryExists();
        try {
            FileOutputStream fous = new FileOutputStream(SAVED_GAMES_DIRECTORY + "/" + savedGameFilename + ".save");
            ObjectOutputStream oos = new ObjectOutputStream(fous);
            oos.writeObject(gameState);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void makeSureDirectoryExists() {
        File savedGameDir = new File(SAVED_GAMES_DIRECTORY);
        if (!savedGameDir.exists()) {
            savedGameDir.mkdir();
        }
    }


}
