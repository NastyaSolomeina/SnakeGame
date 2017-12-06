package tests;

import control.Configuration;
import control.Point;
import logic.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.event.KeyEvent;

public class MapTests {
    private Snake snake;
    private Map map;
    private ArrayWalls walls;
    private Food food;

    @BeforeTest
    public void initialization() {
        Configuration config = new Configuration();
        Grid grid = new Grid(config);
        map = new Map(config, grid);
        snake = map.getFirstSnake();
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
        Assert.assertTrue(map.getFirstSnake().getAlreadyTurned());
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
}