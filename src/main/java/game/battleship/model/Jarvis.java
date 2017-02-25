package game.battleship.model;

/**
 * Developer: Ben Oeyen
 * Date: 25/02/2017
 */
public class Jarvis extends Player{


    public enum IntelligenceLevel{
        STUPID,
        REGULAR,
        SMART
    }

    private IntelligenceLevel intelligenceLevel;

    public Jarvis(IntelligenceLevel intelligenceLevel) {
        super("Jarvis");
        this.intelligenceLevel = intelligenceLevel;
    }

    public IntelligenceLevel getIntelligenceLevel() {
        return intelligenceLevel;
    }
}
