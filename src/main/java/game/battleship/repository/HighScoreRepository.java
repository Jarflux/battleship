package game.battleship.repository;

import game.battleship.model.HighScore;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Developer: Ben Oeyen
 * Date: 16/02/2017
 */
//test
public class HighScoreRepository {

    private static final String HIGHSCORE_FILE = "src/main/resources/image/highscores.dat";

    public static List<HighScore> loadHighScores() {
        List<HighScore> highscores = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(HIGHSCORE_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            highscores = (List<HighScore>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return highscores;
    }

    public static void saveHighScores(List<HighScore> highScores) {
        try {
            FileOutputStream fous = new FileOutputStream(HIGHSCORE_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fous);
            oos.writeObject(highScores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
