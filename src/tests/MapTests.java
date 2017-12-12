package tests;

import control.Configuration;
import control.Point;
import logic.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.event.KeyEvent;
import java.util.ArrayDeque;
import java.util.ArrayList;

import static logic.SnakeNumber.First;
import static logic.SnakeNumber.Second;

public class MapTests {
    private Snake snake;
    private Map map;
    private ArrayWalls walls;
    private Food food;

    @BeforeTest
    public void initialization() {
        Configuration config = new Configuration();
        config.setCountOfPlayers(2);
        config.inicializationRounds();
        config.initializationScores();
        Grid grid = new Grid(config);
        map = new Map(config, grid);
        snake = map.getSnake(First);
        walls = map.getWalls();
        food = map.getFood();
    }

    @DataProvider(name = "turnSnakeData")
    public Object[][] turnSnakeData() {
        return new Object[][]{
                {KeyEvent.VK_LEFT, false, false, true, false, false},
                {KeyEvent.VK_RIGHT, false, false, true, false, false},
                {KeyEvent.VK_DOWN, false, true, false, false, false},
                {KeyEvent.VK_UP, false, true, false, false, false}
        };
    }

    @Test(dataProvider = "turnSnakeData")
    public void turnSnakeTest(int key, boolean left, boolean right, boolean down, boolean up, boolean turn) {

        snake.setMovingLeft(left);
        snake.setMovingRight(right);
        snake.setMovingUp(up);
        snake.setMovingDown(down);
        snake.setAlreadyTurned(turn);

        map.turnFirstSnake(key);
        Assert.assertTrue(map.getSnake(First).getAlreadyTurned());
    }

    @Test
    public void checkSnakeAteWallCountZero() {
        Assert.assertFalse(map.checkFirstSnakeAteWall());
    }

    @Test
    public void checkSnakeAteWallCount() {
        snake.setHead(new Point(2, 5));
        walls.add(new Wall(new Point(2,5), new Point(2,5), new Point(3, 2)));
        Assert.assertTrue(map.checkFirstSnakeAteWall());
    }

    @Test
    public void checkSnakeAteFood() {
        snake.setHead(new Point(2, 5));
        food.setLocation(new Point(2, 5));
        Assert.assertTrue(map.checkFirstSnakeAteFood());
    }

    @Test
    public void mapInitialization() {
        map.initialMap();
        Assert.assertEquals(map.getSnake(First).getHead(), new Point(18, 10));
        ArrayDeque<Point> firstTail = map.getSnake(First).getTail();
        Assert.assertEquals(firstTail.pop(), new Point(16, 10));
        Assert.assertEquals(firstTail.pop(), new Point(17, 10));
        Assert.assertTrue(firstTail.isEmpty());
        Assert.assertTrue(map.getSnake(First).isMovingRight());
        Assert.assertEquals(map.getSnake(Second).getHead(), new Point(18, 11));
        ArrayDeque<Point> secondTail = map.getSnake(Second).getTail();
        Assert.assertEquals(secondTail.pop(), new Point(16, 11));
        Assert.assertEquals(secondTail.pop(), new Point(17, 11));
        Assert.assertTrue(secondTail.isEmpty());
        Assert.assertTrue(map.getSnake(Second).isMovingRight());
        Assert.assertEquals(map.getFood().getLocation(), new Point(25, 15));
        ArrayList<Wall> walls = map.getWalls().getWalls();
        Assert.assertEquals(walls.size(), 5);
    }
}