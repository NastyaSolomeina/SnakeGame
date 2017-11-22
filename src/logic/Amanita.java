package logic;

import control.Configuration;
import control.Point;

import java.util.Random;

public class Amanita {
    private Point location;
    private Grid grid;
    private Configuration config;
    private Random random = new Random();

    private int  lifeTime;
    private int freq;

    public int getFreq(){ return freq; }
    public void setFreq(int val){ freq = val; }

    public int getLifeTime(){ return lifeTime; }
    public void setLifeTime(int val){ lifeTime = val; }
    public Point getLocation(){ return location; }
    public void setLocation(Point val){ location = val; }

    public Amanita(Grid gr, Configuration c){
        grid = gr;
        config = c;
        lifeTime = config.getLifeTimeAminata();
        freq = config.getFrequencyOfOccurrenceAminata();
    }

    public void updatePositionOfAmanita(){
        if (grid.isSnakeIsPoisoned()){
            return;
        }
        if (freq != 0){
            freq--;
            return;
        }
        if (location == null){
            create();
        }

        if (lifeTime == 0){
            location = null;
            grid.setAmanita(location);
            lifeTime = config.getLifeTimeAminata();
            freq = config.getFrequencyOfOccurrenceAminata();
            return;
        }

        if( !grid.itIscellWithAmanita(location.getX(), location.getY())){
            location = null;
        }

        lifeTime--;
    }

    public void create(){
        int newX = random.nextInt(grid.getWeidth());
        int newY = random.nextInt(grid.getHeight());

        if (!grid.cellIsEmpty(newX, newY)){
            create();
        }

        if (grid.cellIsEmpty(newX, newY)) {
            location = new Point(newX, newY);
            grid.setAmanita(location);
        }
    }
}
