package tests;

import control.Configuration;
import control.Point;
import logic.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.ArrayList;

public class GridTest {
    Configuration config;
    Grid grid;
    Point head;
    Point endTail;
    Map map;
    ArrayList<Wall> walls;
    Point food;
    Point amanita;

    @BeforeMethod
    public void inicialisation(){
        config = new Configuration();
        grid = new Grid(config);
        map = new Map(config, grid);
        head = grid.getHead();
        endTail = grid.getEndTail();
        walls = grid.getWalls();
        food = grid.getFood();
        amanita = grid.getAmanita();
    }

    @DataProvider(name = "getColorObjectData")
    public Object[][] getColorObjectData() {
        return new Object[][]{
                {Grid.obj.FOOD, Color.GREEN},
                {Grid.obj.AMANITA, Color.ORANGE},
                {Grid.obj.HEAD, Color.RED},
                {Grid.obj.TAIL, Color.BLUE},
                {Grid.obj.WALL, Color.WHITE},
                {Grid.obj.EMPTY, Color.BLACK}
        };
    }

    @Test(dataProvider = "getColorObjectData")
    public void getColorObjectTest(Grid.obj obj, Color expColor){
        grid.getGrid()[5][2] = obj;
        Assert.assertEquals(grid.getColorObject(2, 5), expColor);
    }

    @Test
    public void setAmanitaLocNullTest(){
        grid.setAmanita(new Point(2, 5));
        grid.setAmanita(null);
        Assert.assertEquals(grid.getGrid()[5][2], Grid.obj.EMPTY);
    }
    @Test
    public void setAmanitaLocNotNullTest(){
        grid.setAmanita(new Point(2, 5));
        Assert.assertEquals(grid.getGrid()[5][2], Grid.obj.AMANITA);
    }
    /*@Test
    public void wallMoved(){
        Wall wall = new Wall(new Point(2,5), new Point(5,5), new Point(1, 0));
        walls.add(wall);
        grid.wallMoved(walls.get(0), 0);
        Assert.assertEquals(grid.getGrid()[5][2], Grid.obj.EMPTY);
    }

    @Test
    public void addNewWallTest(){
        grid.addNewWall(new Wall(new Point(2,5), new Point(5,5), new Point(1, 0)));
        Assert.assertEquals(grid.getGrid()[2][5], Grid.obj.WALL);
    }*/

    @Test
    public void setFoodFoodWasNullTest(){
        grid.setFood(new Point(2, 5));
        Assert.assertEquals(grid.getGrid()[5][2], Grid.obj.FOOD);
    }
    @Test
    public void setFoodFoodIsNullTest(){
        grid.setFood(new Point(2, 5));

        grid.setHead(new Point(10, 15));
        grid.setFood(new Point(3, 7));
        Assert.assertEquals(grid.getGrid()[5][2], Grid.obj.EMPTY);
    }
    @Test
    public void initialSnakeTest(){
        Snake snake = new Snake(grid, config);
        snake.setHead(new Point(10, 10));
        snake.setJoints(2, new Point(26, 10));
        snake.setJoints(3, new Point(36, 10));
        grid.initialSnake(snake);
        Assert.assertEquals(grid.getGrid()[10][10], Grid.obj.HEAD);
    }

    @Test
    public void setSnakeTest(){
        grid.setHead(new Point(2, 5));
        grid.setEndTail(new Point(3, 5));
        grid.setSnake(new Point(1, 5), new Point(2, 5));
        Assert.assertEquals(grid.getGrid()[5][2], Grid.obj.TAIL);
    }
}
