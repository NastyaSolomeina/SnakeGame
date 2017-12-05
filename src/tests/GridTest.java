package tests;

import control.Configuration;
import control.Point;
import logic.Grid;
import logic.Map;
import logic.Snake;
import logic.Wall;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.ArrayList;

public class GridTest {
    private Configuration config;
    private Grid grid;
    private Point head;
    private Point endTail;
    private Map map;
    private ArrayList<Wall> walls;
    private Point food;
    private Point amanita;

    @BeforeMethod
    public void initialization() {
        config = new Configuration();
        grid = new Grid(config);
        map = new Map(config, grid);
        walls = grid.getWalls();
        food = grid.getFood();
        amanita = grid.getAmanita();
    }

    @DataProvider(name = "getColorObjectData")
    public Object[][] getColorObjectData() {
        return new Object[][]{
                {Grid.obj.FOOD, Color.GREEN},
                {Grid.obj.AMANITA, Color.ORANGE},
                {Grid.obj.WALL, Color.WHITE},
                {Grid.obj.EMPTY, Color.BLACK}
        };
    }

    @Test(dataProvider = "getColorObjectData")
    public void getColorObjectTest(Grid.obj obj, Color expColor) {
        grid.getGrid()[5][2] = obj;
        Assert.assertEquals(grid.getColorObject(2, 5), expColor);
    }

    @Test
    public void setAmanitaLocNullTest() {
        grid.setAmanita(new Point(2, 5));
        grid.setAmanita(null);
        Assert.assertEquals(grid.getGrid()[5][2], Grid.obj.EMPTY);
    }
    @Test
    public void setAmanitaLocNotNullTest() {
        grid.setAmanita(new Point(2, 5));
        Assert.assertEquals(grid.getGrid()[5][2], Grid.obj.AMANITA);
    }

    @Test
    public void wallMoved() {
        Wall wall = new Wall(new Point(2,5), new Point(5,5), new Point(1, 0));
        walls.add(wall);
        grid.wallMoved(walls.get(0), 0);
        for (int x = 2; x < 6; x++)
            Assert.assertEquals(grid.getGrid()[5][x], Grid.obj.WALL);
        Assert.assertEquals(grid.getGrid()[5][6], Grid.obj.EMPTY);
        Assert.assertEquals(grid.getGrid()[5][1], Grid.obj.EMPTY);
    }

    @Test
    public void addNewWallTest() {
        grid.addNewWall(new Wall(new Point(2,5), new Point(5,5), new Point(1, 0)));
        for (int x = 2; x < 6; x++)
            Assert.assertEquals(grid.getGrid()[5][x], Grid.obj.WALL);
    }

    @Test
    public void setFoodFoodWasNullTest() {
        grid.setFood(new Point(2, 5));
        Assert.assertEquals(grid.getGrid()[5][2], Grid.obj.FOOD);
    }

    @Test
    public void setFoodTest() {
        grid.setFood(new Point(2, 5));
        Assert.assertEquals(grid.getGrid()[5][2], Grid.obj.FOOD);
        grid.setFirstHead(new Point(10, 15));
        grid.setFood(new Point(3, 7));
        Assert.assertEquals(grid.getGrid()[5][2], Grid.obj.EMPTY);
        Assert.assertEquals(grid.getGrid()[7][3], Grid.obj.FOOD);
    }

    @Test
    public void initialSnakeTest() {
        Snake snake = new Snake(grid, config);
        snake.setHead(new Point(10, 10));
        snake.setJoints(2, new Point(26, 10));
        snake.setJoints(3, new Point(36, 10));
        grid.initialFirstSnake(snake);
        Assert.assertEquals(grid.getGrid()[10][10], Grid.obj.HEAD1);
    }
    @Test
    public void setSnakeTest() {
        grid.setFirstHead(new Point(2, 5));
        grid.setFirstTail(new Point(3, 5));
        grid.setFirstSnake(new Point(1, 5), new Point(2, 5));
        Assert.assertEquals(grid.getGrid()[5][2], Grid.obj.TAIL1);
    }
}
