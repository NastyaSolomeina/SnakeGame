package logic;

import control.Configuration;
import java.awt.event.KeyEvent;

import control.Point;

public class Map {
    private Configuration config;
    private Grid grid;

    private Snake snake_1;
    private Snake snake_2;
    private Food food;
    private ArrayWalls walls;
    private Amanita aminata;

    private boolean player1Win = false;
    private boolean player2Win = false;
    private int score_1 = 0;
    private int score_2 = 0;
    int tick = 0;

    public boolean isPlayer1Win(){ return player1Win;}

    public boolean isPlayer2Win() {
        return player2Win;
    }

    public boolean snake_1IsPoisoned(){return snake_1.isPoisoned(); }
    public boolean snake_2IsPoisoned(){return snake_2.isPoisoned(); }
    public int getPoisonTime() {return snake_1.getPoisonTime(); }
    public ArrayWalls getWalls(){ return walls; }

    public Map(Configuration conf, Grid gr){
        config = conf;
        grid = gr;
        snake_1 = new Snake(grid, config);
        snake_2 = new Snake(grid, config);
        food = new Food(grid, config);
        walls = new ArrayWalls(grid, config);
        aminata = new Amanita(grid, config);
    }

    public boolean snake_1IsLife(){ return snake_1.getItIsLife(); }
    public boolean snake_2IsLife(){ return snake_2.getItIsLife(); }
    public int getScore_1() { return score_1; }
    public int getScore_2() { return score_2; }
    public Snake getSnake_1(){ return snake_1; }
    public Snake getSnake_2(){ return snake_2; }
    public Food getFood(){ return food; }
    public int getTick(){ return tick; }

    public void setTick(int tick) {
        this.tick = tick;
    }

    public void initialMap(){
        snake_1.setHead(new Point(18, 10));
        snake_1.setJoints(2, new Point(17, 10));
        snake_1.setJoints(3, new Point(16, 10));
        snake_1.setMovingRight(true);

        snake_2.setHead(new Point(18, 11));
        snake_2.setJoints(2, new Point(17, 11));
        snake_2.setJoints(3, new Point(16, 11));
        snake_2.setMovingRight(true);

        grid.initialSnake_1(snake_1);
        grid.initialSnake_2(snake_2);
        food.setLocation(new Point(25, 15));
        grid.setFood(food.getLocation());

        walls.add(new Wall(new Point(13, 5), new Point(18,5), new Point(1,0)));
        walls.add(new Wall(new Point(30, 15), new Point(30,22), new Point(0,-1)));
        walls.add(new Wall(new Point(30, 4), new Point(30,10), new Point(0,1)));
        walls.add(new Wall(new Point(36, 25), new Point(39, 25), new Point(-1,0)));
        walls.add(new Wall(new Point(8, 3), new Point(8,8), new Point(0,1)));

    }

    public void move(){
        aminata.updatePositionOfAmanita();

        Point p_1 = snake_1.move(); // возвращает удаленную из конца хвоста точку
        if(!snake_1.getItIsLife()){
            player2Win = true;
            return;
        }
        Point p_2 = snake_2.move(); // возвращает удаленную из конца хвоста точку
        if(!snake_2.getItIsLife()){
            player1Win = true;
            return;
        }
        if (checkSnake1AteWall()){
            score_1 += 5;
            if (walls.getCount() == 0){
                if (score_1 > score_2){
                    player1Win = true;
                }
                else{
                    player2Win = true;
                }
            }

        }
        if (checkSnake2AteWall()){
            score_2 += 5;
            if (walls.getCount() == 0){
                if (score_2 > score_1){
                    player2Win = true;
                }
                else{
                    player1Win = true;
                }
            }
        }
        if (checkSnake1AteFood()){
            score_1 += 5;
            food.createFood();
        }
        if (checkSnake2AteFood()){
            score_2 += 5;
            food.createFood();
        }
        grid.setSnake_1(snake_1.getHead(), p_1);
        grid.setSnake_2(snake_2.getHead(), p_2);
        if (config.foodIsMove(tick)) {
            food.move();
        }

        if (config.wallIsMove(tick)) {
            walls.move();
        }

        tick++;
    }

    public boolean checkSnake1AteFood(){
        return (snake_1.getHead().equals(food.getLocation()));
    }
    public boolean checkSnake2AteFood(){
        return (snake_2.getHead().equals(food.getLocation()));
    }
    public boolean checkSnake1AteWall(){
        if (walls.getCount() == 0){
            return false;
        }

        int k = walls.wallNumberSnakeEats(snake_1.getHead());
        return (k != -1);
    }
    public boolean checkSnake2AteWall(){
        if (walls.getCount() == 0){
            return false;
        }

        int k = walls.wallNumberSnakeEats(snake_2.getHead());
        return (k != -1);
    }
    public void turnSnake1(int key){
        if ((key == KeyEvent.VK_LEFT) && (!snake_1.isMovingRight())
                && !snake_1.getAlreadyTurned()) {
            snake_1.setMovingLeft(true);
            snake_1.setMovingUp(false);
            snake_1.setMovingDown(false);
            snake_1.setAlreadyTurned(true);
        }

        if ((key == KeyEvent.VK_RIGHT) && (!snake_1.isMovingLeft())
                && !snake_1.getAlreadyTurned()) {
            snake_1.setMovingRight(true);
            snake_1.setMovingUp(false);
            snake_1.setMovingDown(false);
            snake_1.setAlreadyTurned(true);
        }

        if ((key == KeyEvent.VK_UP) && (!snake_1.isMovingDown())
                && !snake_1.getAlreadyTurned()) {
            snake_1.setMovingUp(true);
            snake_1.setMovingRight(false);
            snake_1.setMovingLeft(false);
            snake_1.setAlreadyTurned(true);
        }

        if ((key == KeyEvent.VK_DOWN) && (!snake_1.isMovingUp())
                && !snake_1.getAlreadyTurned()) {
            snake_1.setMovingDown(true);
            snake_1.setMovingRight(false);
            snake_1.setMovingLeft(false);
            snake_1.setAlreadyTurned(true);
        }
    }
    public void turnSnake2(int key){
        if ((key == KeyEvent.VK_A) && (!snake_2.isMovingRight())
                && !snake_2.getAlreadyTurned()) {
            snake_2.setMovingLeft(true);
            snake_2.setMovingUp(false);
            snake_2.setMovingDown(false);
            snake_2.setAlreadyTurned(true);
        }

        if ((key == KeyEvent.VK_D) && (!snake_2.isMovingLeft())
                && !snake_2.getAlreadyTurned()) {
            snake_2.setMovingRight(true);
            snake_2.setMovingUp(false);
            snake_2.setMovingDown(false);
            snake_2.setAlreadyTurned(true);
        }

        if ((key == KeyEvent.VK_W) && (!snake_2.isMovingDown())
                && !snake_2.getAlreadyTurned()) {
            snake_2.setMovingUp(true);
            snake_2.setMovingRight(false);
            snake_2.setMovingLeft(false);
            snake_2.setAlreadyTurned(true);
        }

        if ((key == KeyEvent.VK_S) && (!snake_2.isMovingUp())
                && !snake_2.getAlreadyTurned()) {
            snake_2.setMovingDown(true);
            snake_2.setMovingRight(false);
            snake_2.setMovingLeft(false);
            snake_2.setAlreadyTurned(true);
        }
    }
}
