package tests;

import control.Configuration;
import control.Point;
import logic.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static logic.SnakeNumber.First;
import static logic.SnakeNumber.Second;

public class InteractionTests {
    private Snake firstSnake, secondSnake;
    private Map map;
    private ArrayWalls walls;
    private Food food;
    private Grid grid;

    @BeforeMethod
    public void initialization() {
        Configuration config = new Configuration();
        grid = new Grid(config);
        map = new Map(config, grid);
        firstSnake = map.getSnake(First);
        secondSnake = map.getSnake(Second);
        walls = map.getWalls();
        food = map.getFood();
    }

    @BeforeMethod
    public void setSnakeConfiguration() {
        firstSnake.setHead(new Point(2, 5));
        firstSnake.setAlive(true);
        firstSnake.setPoisoned(false);
        secondSnake.setHead(new Point(10, 5));
        secondSnake.setAlive(true);
        secondSnake.setPoisoned(false);
        firstSnake.setJoints(2, new Point(26, 10));
        secondSnake.setJoints(2, new Point(21, 51));
        grid.setFirstHead(new Point(2, 5));
        grid.setSecondHead(new Point(10, 5));
    }

    @Test
    public void moveSnakeEatNotAllWall() {
        firstSnake.setPoisoned(true);
        food.setLocation(new Point(10, 15));
        grid.setFirstHead(new Point(2, 5));
        walls.add(new Wall(new Point(2, 5), new Point(2, 5), new Point(3, 2)));
        int oldScore = map.getScore(First);
        map.move();
        Assert.assertEquals(map.getScore(First), oldScore + 5);
    }


    @Test
    public void moveSnakeEatAllWall() {
        firstSnake.setPoisoned(true);
        food.setLocation(new Point(10, 15));
        walls.add(new Wall(new Point(2, 5), new Point(2, 5), new Point(3, 2)));
        map.move();
        Assert.assertTrue(map.havePlayerWon(First));
    }

    @Test
    public void moveSnakeEatFood() {
        food.setLocation(new Point(10, 15));
        food.setLocation(new Point(2, 5));
        int oldScore = map.getScore(First);
        map.move();
        Assert.assertEquals(map.getScore(First) - oldScore, 5);
    }

    @Test
    public void moveTickPluses() {
        food.setLocation(new Point(10, 15));
        map.setTick(10);
        map.move();
        Assert.assertEquals(map.getTick(), 11);
    }

    @Test
    public void collisionWithNoPointDifference() {
        walls.add(new Wall(new Point(2, 5), new Point(2, 5), new Point(3, 2)));
        walls.add(new Wall(new Point(10, 5), new Point(10, 5), new Point(3, 2)));
        map.move();
        Assert.assertEquals(map.getScore(First), map.getScore(Second));
        Assert.assertFalse(map.havePlayerWon(First));
        Assert.assertFalse(map.havePlayerWon(Second));
    }

    @Test
    public void collideWhenFirstIsWinning() {
        map.setScore(First, 20);
        map.setScore(Second, 25);
        walls.add(new Wall(new Point(2, 5), new Point(2, 5), new Point(3, 2)));
        walls.add(new Wall(new Point(10, 5), new Point(10, 5), new Point(3, 2)));
        map.move();
        Assert.assertNotEquals(map.getScore(First), map.getScore(Second));
        Assert.assertFalse(map.havePlayerWon(First));
        Assert.assertTrue(map.havePlayerWon(Second));
    }
}
