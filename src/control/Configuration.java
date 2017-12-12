package control;


import java.util.ArrayList;

public class Configuration {
    private int boardWidth = 40;
    private int boardHeight = 27;
    private int pointsLine = 50;
    private int pixelSize = 25;


    private int speedFood = 2;
    private int speedWall = 3;
    private int speedTick = 280;
    private int lifeTimeAmanita = 50;
    private int frequencyOfOccurrenceAmanita = 100;
    private int poisonTime = 100;
    private int countOfPlayers;
    private int countOfRounds;
    private int currentRound = 1;
    private ArrayList<Integer> scores = new ArrayList<>();
    private ArrayList<String> rounds = new ArrayList<>();
    private boolean GameStoped = false;

    public boolean isGameStoped() {
        return GameStoped;
    }

    public void setGameStoped(boolean gameStoped) {
        GameStoped = gameStoped;
    }

    public void setCountOfRounds() {
        this.countOfRounds = (countOfPlayers * (countOfPlayers - 1)) / 2;
    }

    public void inicializationRounds() {
        for (int i = 0; i < countOfPlayers; i++) {
            for (int j = i + 1; j < countOfPlayers; j++) {
                rounds.add("" + (i + 1) + ' ' + (j + 1));
            }
        }
    }

    public String getPlayers() {
        return rounds.get(currentRound - 1);
    }

    public int getScores(int i) {
        return scores.get(i);
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound() {
        this.currentRound++;
    }

    public int getCountOfRounds() {
        return countOfRounds;
    }

    public void initializationScores() {
        for (int i = 0; i < countOfPlayers; i++) {
            scores.add(0);
        }
    }

    public void incrementScore(int index, int score) {
        int old = getScores(index);
        this.scores.set(index, score + old);
    }

    public int getCountOfPlayers() {
        return countOfPlayers;
    }

    public void setCountOfPlayers(int countOfPlayers) {
        this.countOfPlayers = countOfPlayers;
    }

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
