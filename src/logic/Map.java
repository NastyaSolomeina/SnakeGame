package logic;

import control.Configuration;
import control.Point;

import java.awt.event.KeyEvent;

public class Map {
    private Configuration config;
    private Grid grid;

    private Snake firstSnake;
    private Snake secondSnake;
    private Food food;
    private ArrayWalls walls;
    private Amanita amanita;

    private boolean player1Win = false;
    private boolean player2Win = false;
    private int score_1 = 0;
    private int score_2 = 0;
    private int tick = 0;

    public Map(Configuration conf, Grid gr) {
        config = conf;
        grid = gr;
        firstSnake = new Snake(grid, config);
        secondSnake = new Snake(grid, config);
        food = new Food(grid, config);
        walls = new ArrayWalls(grid, config);
        amanita = new Amanita(grid, config);
    }

    public boolean isFirstPlayerWon() {
        return player1Win;
    }

    public boolean isSecondPlayerWon() {
        return player2Win;
    }

    public boolean isFirstSnakePoisoned() {
        return firstSnake.isPoisoned();
    }

    public boolean isSecondSnakePoisoned() {
        return secondSnake.isPoisoned();
    }

    public int getPoisonTime() {
        return firstSnake.getPoisonTime();
    }

    public ArrayWalls getWalls() {
        return walls;
    }

    public boolean isFirstSnakeAlive() {
        return firstSnake.getItIsLife();
    }

    public boolean isSecondSnakeAlive() {
        return secondSnake.getItIsLife();
    }

    public int getFirstPlayerScore() {
        return score_1;
    }

    public int getSecondPlayerScore() {
        return score_2;
    }

    public Snake getFirstSnake() {
        return firstSnake;
    }

    public Snake getSecondSnake() {
        return secondSnake;
    }

    public Food getFood() {
        return food;
    }

    public int getTick() {
        return tick;
    }

    public void setTick(int tick) {
        this.tick = tick;
    }

    public void initialMap() {
        firstSnake.setHead(new Point(18, 10));
        firstSnake.setJoints(2, new Point(17, 10));
        firstSnake.setJoints(3, new Point(16, 10));
        firstSnake.setMovingRight(true);

        secondSnake.setHead(new Point(18, 11));
        secondSnake.setJoints(2, new Point(17, 11));
        secondSnake.setJoints(3, new Point(16, 11));
        secondSnake.setMovingRight(true);

        grid.initialFirstSnake(firstSnake);
        grid.initialSecondSnake(secondSnake);
        food.setLocation(new Point(25, 15));
        grid.setFood(food.getLocation());

        walls.add(new Wall(new Point(13, 5), new Point(18,5), new Point(1,0)));
        walls.add(new Wall(new Point(30, 15), new Point(30,22), new Point(0,-1)));
        walls.add(new Wall(new Point(30, 4), new Point(30,10), new Point(0,1)));
        walls.add(new Wall(new Point(36, 25), new Point(39, 25), new Point(-1,0)));
        walls.add(new Wall(new Point(8, 3), new Point(8,8), new Point(0,1)));

    }

    public void move() {
        amanita.updatePositionOfAmanita();

        Point p_1 = firstSnake.move(); // возвращает удаленную из конца хвоста точку
        if (!firstSnake.getItIsLife()) {
            player2Win = true;
            return;
        }
        Point p_2 = secondSnake.move(); // возвращает удаленную из конца хвоста точку
        if (!secondSnake.getItIsLife()) {
            player1Win = true;
            return;
        }
        if (checkFirstSnakeAteWall()) {
            score_1 += 5;
            if (walls.getCount() == 0) {
                if (score_1 > score_2) {
                    player1Win = true;
                }
                else{
                    player2Win = true;
                }
            }

        }
        if (checkSecondSnakeAteWall()) {
            score_2 += 5;
            if (walls.getCount() == 0) {
                if (score_2 > score_1) {
                    player2Win = true;
                }
                else{
                    player1Win = true;
                }
            }
        }
        if (checkFirstSnakeAteFood()) {
            score_1 += 5;
            food.createFood();
        }
        if (checkSecondSnakeAteFood()) {
            score_2 += 5;
            food.createFood();
        }
        grid.setFirstSnake(firstSnake.getHead(), p_1);
        grid.setSecondSnake(secondSnake.getHead(), p_2);
        if (config.foodIsMove(tick)) {
            food.move();
        }

        if (config.wallIsMove(tick)) {
            walls.move();
        }

        tick++;
    }

    public boolean checkFirstSnakeAteFood() {
        return (firstSnake.getHead().equals(food.getLocation()));
    }

    public boolean checkSecondSnakeAteFood() {
        return (secondSnake.getHead().equals(food.getLocation()));
    }

    public boolean checkFirstSnakeAteWall() {
        if (walls.getCount() == 0) {
            return false;
        }

        int k = walls.wallNumberSnakeEats(firstSnake.getHead());
        return (k != -1);
    }

    public boolean checkSecondSnakeAteWall() {
        if (walls.getCount() == 0) {
            return false;
        }

        int k = walls.wallNumberSnakeEats(secondSnake.getHead());
        return (k != -1);
    }

    public void turnFirstSnake(int key) {
        if ((key == KeyEvent.VK_LEFT) && (!firstSnake.isMovingRight())
                && !firstSnake.getAlreadyTurned()) {
            firstSnake.setMovingLeft(true);
            firstSnake.setMovingUp(false);
            firstSnake.setMovingDown(false);
            firstSnake.setAlreadyTurned(true);
        }

        if ((key == KeyEvent.VK_RIGHT) && (!firstSnake.isMovingLeft())
                && !firstSnake.getAlreadyTurned()) {
            firstSnake.setMovingRight(true);
            firstSnake.setMovingUp(false);
            firstSnake.setMovingDown(false);
            firstSnake.setAlreadyTurned(true);
        }

        if ((key == KeyEvent.VK_UP) && (!firstSnake.isMovingDown())
                && !firstSnake.getAlreadyTurned()) {
            firstSnake.setMovingUp(true);
            firstSnake.setMovingRight(false);
            firstSnake.setMovingLeft(false);
            firstSnake.setAlreadyTurned(true);
        }

        if ((key == KeyEvent.VK_DOWN) && (!firstSnake.isMovingUp())
                && !firstSnake.getAlreadyTurned()) {
            firstSnake.setMovingDown(true);
            firstSnake.setMovingRight(false);
            firstSnake.setMovingLeft(false);
            firstSnake.setAlreadyTurned(true);
        }
    }

    public void turnSecondSnake(int key) {
        if ((key == KeyEvent.VK_A) && (!secondSnake.isMovingRight())
                && !secondSnake.getAlreadyTurned()) {
            secondSnake.setMovingLeft(true);
            secondSnake.setMovingUp(false);
            secondSnake.setMovingDown(false);
            secondSnake.setAlreadyTurned(true);
        }

        if ((key == KeyEvent.VK_D) && (!secondSnake.isMovingLeft())
                && !secondSnake.getAlreadyTurned()) {
            secondSnake.setMovingRight(true);
            secondSnake.setMovingUp(false);
            secondSnake.setMovingDown(false);
            secondSnake.setAlreadyTurned(true);
        }

        if ((key == KeyEvent.VK_W) && (!secondSnake.isMovingDown())
                && !secondSnake.getAlreadyTurned()) {
            secondSnake.setMovingUp(true);
            secondSnake.setMovingRight(false);
            secondSnake.setMovingLeft(false);
            secondSnake.setAlreadyTurned(true);
        }

        if ((key == KeyEvent.VK_S) && (!secondSnake.isMovingUp())
                && !secondSnake.getAlreadyTurned()) {
            secondSnake.setMovingDown(true);
            secondSnake.setMovingRight(false);
            secondSnake.setMovingLeft(false);
            secondSnake.setAlreadyTurned(true);
        }
    }
}
