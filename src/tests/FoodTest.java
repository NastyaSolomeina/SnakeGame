package tests;

import control.Configuration;
import control.Point;
import logic.Food;
import logic.Grid;
import logic.Snake;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FoodTest {

    Configuration config;
    Grid grid;
    Snake snake;
    Grid.obj[][] gridTest;
    Food food;

    @BeforeMethod
    private void initialization() {
        config = new Configuration();
        grid = new Grid(config);
        snake = new Snake(grid, config);
        gridTest = grid.getGrid();
        food = new Food(grid, config);
    }


    @Test
    public void foodRunRunTest() {
        food.setLocation(new Point(2,5));
        food.foodRun();
        Assert.assertNotEquals(food.getLocation(), new Point(2,5));
    }

    @Test
    public void foodRunDontRunTest() {
        food.setLocation(new Point(2, 5));
        for (int i = 1; i < 4; i++) {
            for (int j = 4; j < 7; j++) {
                grid.getGrid()[j][i] = Grid.obj.WALL;
            }
        }
        food.foodRun();
        Assert.assertEquals(food.getLocation(), new Point(2,5));
    }

    @Test
    public void moveSleepTest() {
        food.setSleep(10);
        food.move();
        Assert.assertEquals(food.getSleep(), 9);
    }

    @Test
    public void moveStoppedRunTest() {
        food.setSleep(0);
        food.setRun(0);
        food.move();
        Assert.assertNotEquals(food.getSleep(), 0);
    }

    @Test
    public void moveRunTest() {

        food.setLocation(new Point(2, 5));
        food.setSleep(0);
        food.setRun(10);
        food.move();
        Assert.assertEquals(food.getRun(), 9);
    }

    @Test
    public void createTest() {
        food.createFood();
        Assert.assertEquals(grid.getGrid()[food.getLocation().getY()][food.getLocation().getX()], Grid.obj.FOOD);
    }
}
