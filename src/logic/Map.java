package logic;

import control.Configuration;
import control.MoveSelector;
import control.Point;
import control.SnakeMove;

import static logic.SnakeNumber.First;

public class Map {
    private Configuration config;
    private Grid grid;

    private Snake firstSnake, secondSnake;
    private Food food;
    private ArrayWalls walls;
    private Amanita amanita;
    private int countOfPlayers;
    private int countOfRounds;
    private int currentRound;
    private int firstPlayer;
    private int secondPlayer;
    private MoveSelector selector;

    private boolean firstPlayerWon = false, secondPlayerWon = false;
    private int firstPlayerScore = 0, secondPlayerScore = 0;
    private int tick = 0;

    public Map(Configuration conf, Grid gr) {
        config = conf;
        grid = gr;
        countOfPlayers = config.getCountOfPlayers();
        firstSnake = new Snake(grid, config);
        secondSnake = new Snake(grid, config);
        food = new Food(grid, config);
        walls = new ArrayWalls(grid, config);
        amanita = new Amanita(grid, config);
        countOfRounds = config.getCountOfRounds();
        currentRound = config.getCurrentRound();
        firstPlayer = Integer.parseInt(config.getPlayers().substring(0, 1));
        secondPlayer = Integer.parseInt(config.getPlayers().substring(2));
        selector = new MoveSelector();
    }

    public Configuration getConfig() {
        return config;
    }

    public boolean havePlayerWon(SnakeNumber number) {
        return (number == First) ? firstPlayerWon : secondPlayerWon;
    }

    public boolean isSnakePoisoned(SnakeNumber number) {
        return ((number == First) ? firstSnake : secondSnake).isPoisoned();
    }

    public int getPoisonTime(SnakeNumber number) {
        return ((number == First) ? firstSnake : secondSnake).getPoisonTime();
    }

    public ArrayWalls getWalls() {
        return walls;
    }

    public boolean isSnakeAlive(SnakeNumber number) {
        return ((number == First) ? firstSnake : secondSnake).getIsAlive();
    }

    public int getScore(SnakeNumber number) {
        return number == First ? firstPlayerScore : secondPlayerScore;
    }

    public void setScore(SnakeNumber number, int score) {
        if (number == First)
            firstPlayerScore = score;
        else
            secondPlayerScore = score;
    }

    public Snake getSnake(SnakeNumber number) {
        return (number == First) ? firstSnake : secondSnake;
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

        Point p_1 = firstSnake.move();
        Point p_2 = secondSnake.move();

        if (!firstSnake.getIsAlive() && !secondSnake.getIsAlive()) {
            if (firstPlayerScore > secondPlayerScore) {
                firstPlayerWon = true;
                config.incrementScore(firstPlayer - 1, 3);
            } else if (secondPlayerScore > firstPlayerScore) {
                secondPlayerWon = true;
                config.incrementScore(secondPlayer - 1, 3);
            } else {
                firstPlayerWon = secondPlayerWon = true;
                config.incrementScore(firstPlayer - 1, 1);
                config.incrementScore(secondPlayer - 1, 1);
            }
            return;
        }

        if (!firstSnake.getIsAlive()) {
            secondPlayerWon = true;
            config.incrementScore(secondPlayer - 1, 3);
            return;
        }
        if (!secondSnake.getIsAlive()) {
            firstPlayerWon = true;
            config.incrementScore(firstPlayer - 1, 3);
            return;
        }

        if (checkFirstSnakeAteFood()) {
            firstPlayerScore += 5;
            food.createFood();
        }

        if (checkFirstSnakeAteWall()) {
            firstPlayerScore += 5;
            if (walls.getCount() == 0) {
                if (firstPlayerScore > secondPlayerScore) {
                    firstPlayerWon = true;
                } else{
                    secondPlayerWon = true;
                }
            }
        }

        if (checkSecondSnakeAteFood()) {
            secondPlayerScore += 5;
            food.createFood();
        }

        if (checkSecondSnakeAteWall()) {
            secondPlayerScore += 5;
            if (walls.getCount() == 0) {
                if (secondPlayerScore > firstPlayerScore) {
                    secondPlayerWon = true;
                }
                else{
                    firstPlayerWon = true;
                }
            }
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

    @SuppressWarnings("WeakerAccess")
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

    @SuppressWarnings("WeakerAccess")
    public boolean checkSecondSnakeAteWall() {
        if (walls.getCount() == 0) {
            return false;
        }

        int k = walls.wallNumberSnakeEats(secondSnake.getHead());
        return (k != -1);
    }

    public void turnSnake(int key) {
        SnakeMove snakeMove = selector.select(key);
        if (snakeMove != null) {
            Snake snake = getSnake(snakeMove.getSnakeNumber());
            snakeMove.changeDirection(snake);
        }
    }
}

