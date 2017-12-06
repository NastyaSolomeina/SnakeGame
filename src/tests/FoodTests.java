package tests;

import control.Configuration;
import control.Point;
import logic.Food;
import logic.Grid;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FoodTests {

    private Configuration config;
    private Grid grid;
    private Food food;

    @BeforeMethod
    private void initialization() {
        config = new Configuration();
        grid = new Grid(config);
        food = new Food(grid, config);
    }


    @Test
    public void foodRunRun() {
        food.setLocation(new Point(2,5));
        food.foodRun();
        Assert.assertNotEquals(food.getLocation(), new Point(2,5));
    }

    @Test
    public void foodRunDontRun() {
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
    public void moveSleep() {
        food.setSleep(10);
        food.move();
        Assert.assertEquals(food.getSleep(), 9);
    }

    @Test
    public void moveStoppedRun() {
        food.setSleep(0);
        food.setRun(0);
        food.move();
        Assert.assertNotEquals(food.getSleep(), 0);
    }

    @Test
    public void moveRun() {

        food.setLocation(new Point(2, 5));
        food.setSleep(0);
        food.setRun(10);
        food.move();
        Assert.assertEquals(food.getRun(), 9);
    }

    @Test
    public void create() {
        food.createFood();
        Assert.assertEquals(grid.getGrid()[food.getLocation().getY()][food.getLocation().getX()], Grid.obj.FOOD);
    }
}
