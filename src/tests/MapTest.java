package tests;

import control.Configuration;
import control.Point;
import logic.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.event.KeyEvent;

public class MapTest {

    Configuration config;
    Grid grid;
    Snake snake;
    Map map;
    ArrayWalls walls;
    Food food;

    @BeforeMethod
    public void inicialisation(){
        config = new Configuration();
        grid = new Grid(config);
        map = new Map(config, grid);
        snake = map.getSnake_1();
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

   /* @Test(dataProvider = "turnSnakeData")
    public void turnSnakeTest(int key, boolean left, boolean right, boolean down, boolean up, boolean turn) {

        snake.setMovingLeft(left);
        snake.setMovingRight(right);
        snake.setMovingUp(up);
        snake.setMovingDown(down);
        snake.setAlreadyTurned(turn);

        map.turnSnake(key);
        Assert.assertTrue(map.getSnake_1().getAlreadyTurned());
    }

    @Test
    public void checkSnakeAteWallCountZeroTest(){
        Assert.assertFalse(map.checkSnakeAteWall());
    }
    @Test
    public void checkSnakeAteWallCountTest(){
        snake.setHead(new Point(2, 5));
        walls.add(new Wall(new Point(2,5), new Point(2,5), new Point(3, 2)));
        Assert.assertTrue(map.checkSnakeAteWall());
    }

    @Test
    public void checkSnakeAteFoodTest(){
        snake.setHead(new Point(2, 5));
        food.setLocation(new Point(2, 5));
        Assert.assertTrue(map.checkSnakeAteFood());
    }*/

    /*@Test
    public void moveSnakeEatNotAllWallTest(){
        snake.setHead(new Point(2, 5));
        snake.setItIsLife(true);
        snake.setPoisoned(true);
        food.setLocation(new Point (10, 15));
        snake.setJoints(2, new Point(26, 10));
        grid.setHead(new Point(2, 5));
        walls.add(new Wall(new Point(2,5), new Point(2,5), new Point(3, 2)));
        int oldScore = map.getScore();
        map.move();
        Assert.assertEquals(map.getScore(), oldScore + 5);
    }
    @Test
    public void moveSnakeEatAllWallTest(){
        snake.setHead(new Point(2, 5));
        snake.setItIsLife(true);
        snake.setPoisoned(true);
        food.setLocation(new Point (10, 15));
        snake.setJoints(2, new Point(26, 10));
        grid.setHead(new Point(2, 5));
        walls.add(new Wall(new Point(2,5), new Point(2,5), new Point(3, 2)));
        map.move();
        Assert.assertTrue(map.isYouWin());
    }

    @Test
    public void moveSnakeEatFoodTest(){
        snake.setHead(new Point(2, 5));
        snake.setItIsLife(true);
        snake.setPoisoned(true);
        food.setLocation(new Point (10, 15));
        snake.setJoints(2, new Point(26, 10));
        grid.setHead(new Point(2, 5));
        food.setLocation(new Point(2, 5));
        int oldScore = map.getScore();
        map.move();
        Assert.assertEquals(map.getScore(), oldScore + 5);
    }

    @Test
    public void moveTickPlusesTest(){
        snake.setHead(new Point(2, 5));
        snake.setItIsLife(true);
        snake.setPoisoned(true);
        food.setLocation(new Point (10, 15));
        snake.setJoints(2, new Point(26, 10));
        grid.setHead(new Point(2, 5));
        map.setTick(10);
        map.move();
        Assert.assertEquals(map.getTick(), 11);
    }*/
}
