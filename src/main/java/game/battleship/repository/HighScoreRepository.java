package game.battleship.repository;

import game.battleship.model.HighScore;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Developer: Ben Oeyen
 * Date: 16/02/2017
 */

public class HighScoreRepository {

    public static String highScoreFilename = "src/main/resources/image/highscores.data";

    public static List<HighScore> loadHighScores() {
        List<HighScore> highscores = new ArrayList<>();
        File f = new File(highScoreFilename);
        if (f.exists() && !f.isDirectory()) {
            try {
                FileInputStream fis = new FileInputStream(highScoreFilename);
                ObjectInputStream ois = new ObjectInputStream(fis);
                highscores = (List<HighScore>) ois.readObject();
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return highscores;
    }

    public static void saveHighScores(List<HighScore> highScores) {
        try {
            FileOutputStream fous = new FileOutputStream(highScoreFilename);
            ObjectOutputStream oos = new ObjectOutputStream(fous);
            oos.writeObject(highScores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteHighScores() {
        File f = new File(highScoreFilename);
        if (f.exists() && !f.isDirectory()) {
            f.delete();
        }
    }


}
