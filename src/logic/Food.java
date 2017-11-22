package logic;

import control.Configuration;
import control.Point;

import java.util.Random;

public class Food {
    private Point location;

    private Grid grid;
    private Configuration config;
    private Random random = new Random();

    public Food(Grid gr, Configuration c){
        grid = gr;
        config = c;
    }
    private int sleep;
    private int run;
    public void setSleep(int val){ sleep = val; }
    public int getRun(){ return run; }
    public int getSleep() {
        return sleep;
    }

    public void setRun(int run) {
        this.run = run;
    }

    public Point getLocation() { return new Point(location); }
    public void setLocation(Point location) {
        this.location = new Point(location);
    }

    public void createFood() {

        int newX = random.nextInt(grid.getWeidth());
        int newY = random.nextInt(grid.getHeight());

        if (!grid.cellIsEmpty(newX, newY)){
            createFood();
        }

        if (grid.cellIsEmpty(newX, newY)) {
            location = new Point(newX, newY);
            grid.setFood(location);
        }

        updateTheTime();
    }

    void updateTheTime(){ // устанавливаем новое время для сна и бега
        sleep = random.nextInt(30);
        run = random.nextInt(30);
    }

    public void move() {
        if (sleep != 0) { // еще спит
            sleep--;
            return;
        }
        if (run == 0) { // закончил бегать
            updateTheTime();
            return;
        }

        foodRun(); // двигаем еду

        run--;
        grid.setFood(location);
    }


    public void foodRun(){
        int dir = random.nextInt(4);

        switch (dir){
            case 0: //right
                if (location.getX() + 1 < config.getBoardWidth()){
                    if (grid.cellIsEmpty(location.getX() + 1, location.getY())){
                        location.setX(location.getX() + 1);
                    }
                }
                break;
            case 1: //left
                if (location.getX() - 1 >= 0){
                    if (grid.cellIsEmpty(location.getX() - 1, location.getY())){
                        location.setX(location.getX() - 1);
                    }
                }
                break;
            case 2: //down
                if (location.getY() + 1 < config.getBoardHeight()){
                    if (grid.cellIsEmpty(location.getX(), location.getY() + 1)){
                        location.setY(location.getY() + 1);
                    }
                }
                break;
            case 3: //up
                if (location.getY() - 1 >= 0){
                    if (grid.cellIsEmpty(location.getX(), location.getY() - 1)){
                        location.setY(location.getY() - 1);
                    }
                }
                break;
        }
    }
}
