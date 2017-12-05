package control;

@SuppressWarnings("FieldCanBeLocal")
public class Configuration {
    private int boardWidth = 40;
    private int boardHeight = 27;
    private int pointsLine = 50;
    private int pixelSize = 25;


    private int speedFood = 2;
    private int speedWall = 3;
    private int speedTick = 500;
    private int lifeTimeAmanita = 50;
    private int frequencyOfOccurrenceAmanita = 100;
    private int poisonTime = 100;

    public boolean foodIsMove(int tick) {
        return (tick % speedFood == 0);
    }

    public boolean wallIsMove(int tick) {
        return (tick % speedWall == 0);
    }


    public int getLifeTimeAmanita() {
        return lifeTimeAmanita;
    }

    public int getFrequencyOfOccurrenceAmanita() {
        return frequencyOfOccurrenceAmanita;
    }

    public int getPoisonTime() {
        return poisonTime;
    }

    public int getBoardHeight() { return boardHeight; }

    public int getBoardWidth() { return boardWidth; }

    public int getSpeedTick() { return speedTick; }

    public int getPSize() { return pixelSize; }

    public int getPLine() { return pointsLine; }

    public int getHeightPS() {return boardHeight * pixelSize; }

    public int getWidthPS() {return boardWidth * pixelSize; }
}
