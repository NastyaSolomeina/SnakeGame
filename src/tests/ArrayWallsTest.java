package tests;

import control.Configuration;
import control.Point;
import logic.ArrayWalls;
import logic.Grid;
import logic.Wall;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ArrayWallsTest {
    private Configuration config;
    private Grid grid;
    private ArrayWalls walls;

    @BeforeMethod
    public void initialization() {
        config = new Configuration();
        grid = new Grid(config);
        walls = new ArrayWalls(grid, config);
    }

    @Test
    public void snakeEatVerticalWallTest() {
        Wall wall = new Wall(new Point(2,5), new Point(2,9), new Point(3, 2));
        Assert.assertTrue(walls.snakeEatVerticalWall(new Point(2, 5), wall));
    }

    @Test
    public void snakeEatHorizontalWallTest() {
        Wall wall = new Wall(new Point(2,5), new Point(9,5), new Point(3, 2));
        Assert.assertTrue(walls.snakeEatHorizontalWall(new Point(2, 5), wall));
    }

    @Test
    public void snakeFinishedWallTest() {
        Wall wall = new Wall(new Point(2,5), new Point(2,5), new Point(3, 2));
        Assert.assertTrue(walls.snakeFinishedWall(new Point(2, 5), wall));
    }

    /*@Test                                  //Здесь недоприватные методы
    public void getNextStepEndTest() {
        Wall wall = new Wall(new Point(2,5), new Point(2,9), new Point(0, 1));
        Assert.assertEquals(walls.getNextStepEnd(wall), new Point(2, 10));
    }

    @Test
    public void getNextStepStartTest() {
        Wall wall = new Wall(new Point(2,5), new Point(2,9), new Point(0, 1));
        Assert.assertEquals(walls.getNextStepStart(wall), new Point(2, 6));
    }*/

    @Test
    public void findAndDeployMeetingWallEndTest() {
        walls.add(new Wall(new Point(2,5), new Point(2,9), new Point(0, 2)));
        walls.findAndDeployMeetingWallEnd(new Point(2, 5));
        Assert.assertEquals(walls.getWalls().get(0).getDir().getY(), -2);
    }
    @Test
    public void findAndDeployMeetingWallStartTest() {
        walls.add(new Wall(new Point(2,5), new Point(2,9), new Point(0, 2)));
        walls.findAndDeployMeetingWallStart(new Point(2, 9));
        Assert.assertEquals(walls.getWalls().get(0).getDir().getY(), -2);
    }
    @Test
    public void moveRightDownTest() {
        walls.add(new Wall(new Point(2,5), new Point(2,9), new Point(0, 1)));
        walls.move();
    }
}
