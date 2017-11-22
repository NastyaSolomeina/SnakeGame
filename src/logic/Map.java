package logic;

import control.Configuration;
import java.awt.event.KeyEvent;

import control.Point;

public class Map {
    private Configuration config;
    private Grid grid;

    private Snake snake;
    private Food food;
    private ArrayWalls walls;
    private Amanita aminata;

    private boolean youWin = false;
    private int score = 0;
    int tick = 0;

    public boolean isYouWin(){ return youWin;}
    public boolean snakeIsPoisoned(){return snake.isPoisoned(); }
    public int getPoisonTime() {return snake.getPoisonTime(); }
    public ArrayWalls getWalls(){ return walls; }

    public Map(Configuration conf, Grid gr){
        config = conf;
        grid = gr;
        snake = new Snake(grid, config);
        food = new Food(grid, config);
        walls = new ArrayWalls(grid, config);
        aminata = new Amanita(grid, config);
    }

    public boolean snakeIsLife(){ return snake.getItIsLife(); }
    public int getScore() { return score; }
    public Snake getSnake(){ return snake; }
    public Food getFood(){ return food; }
    public int getTick(){ return tick; }

    public void setTick(int tick) {
        this.tick = tick;
    }

    public void initialMap(){
        snake.setHead(new Point(18, 10));
        snake.setJoints(2, new Point(17, 10));
        snake.setJoints(3, new Point(16, 10));
        snake.setMovingRight(true);

        grid.initialSnake(snake);

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

        Point p = snake.move(); // возвращает удаленную из конца хвоста точку
        if(!snake.getItIsLife()){ return; }

        if (checkSnakeAteWall()){
            score += 5;
            if (walls.getCount() == 0){
                youWin = true;
            }
        }

        if (checkSnakeAteFood()){
            score += 5;
            food.createFood();
        }
        grid.setSnake(snake.getHead(), p);

        if (config.foodIsMove(tick)) {
            food.move();
        }

        if (config.wallIsMove(tick)) {
            walls.move();
        }

        tick++;
    }

    public boolean checkSnakeAteFood(){
        return (snake.getHead().equals(food.getLocation()));
    }

    public boolean checkSnakeAteWall(){
        if (walls.getCount() == 0){
            return false;
        }

        int k = walls.wallNumberSnakeEats(snake.getHead());
        return (k != -1);
    }

    public void turnSnake(int key){
        if ((key == KeyEvent.VK_LEFT) && (!snake.isMovingRight())
                && !snake.getAlreadyTurned()) {
            snake.setMovingLeft(true);
            snake.setMovingUp(false);
            snake.setMovingDown(false);
            snake.setAlreadyTurned(true);
        }

        if ((key == KeyEvent.VK_RIGHT) && (!snake.isMovingLeft())
                && !snake.getAlreadyTurned()) {
            snake.setMovingRight(true);
            snake.setMovingUp(false);
            snake.setMovingDown(false);
            snake.setAlreadyTurned(true);
        }

        if ((key == KeyEvent.VK_UP) && (!snake.isMovingDown())
                && !snake.getAlreadyTurned()) {
            snake.setMovingUp(true);
            snake.setMovingRight(false);
            snake.setMovingLeft(false);
            snake.setAlreadyTurned(true);
        }

        if ((key == KeyEvent.VK_DOWN) && (!snake.isMovingUp())
                && !snake.getAlreadyTurned()) {
            snake.setMovingDown(true);
            snake.setMovingRight(false);
            snake.setMovingLeft(false);
            snake.setAlreadyTurned(true);
        }
    }

}
