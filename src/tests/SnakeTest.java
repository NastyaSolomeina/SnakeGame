package tests;

import control.Configuration;
import control.Point;
import logic.Amanita;
import logic.Grid;
import logic.Snake;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class SnakeTest {

    private Snake snake;
    private Grid.obj[][] gridTest;
    private Grid grid;

    @BeforeMethod
    private void initialization() {
        Configuration config = new Configuration();
        grid = new Grid(config);
        snake = new Snake(grid, config);
        gridTest = grid.getGrid();
        Amanita amanita = new Amanita(grid, config);
    }

    @Test
    public void collidePoisonedWithFoodInNextCell() {
        snake.setAlive(true);
        snake.setPoisoned(true);
        snake.setHead(new Point(2,5));
        gridTest[5][2] = Grid.obj.FOOD;
        snake.collideWithObjectInNextCell(new Point(2, 5));
        Assert.assertFalse(snake.getIsAlive());
    }

    @Test
    public void collideNotPoisonedWithFoodInNextCell() {
        snake.setPoisoned(false);
        snake.setHead(new Point(2,5));
        gridTest[5][2] = Grid.obj.FOOD;
        snake.collideWithObjectInNextCell(new Point(2, 5));
        Assert.assertEquals(snake.getLen(), 1);
    }

    @Test
    public void collidePoisonedWithWallInNextCell() {
        snake.setAlive(true);
        snake.setPoisoned(false);
        snake.setHead(new Point(2,5));
        gridTest[5][2] = Grid.obj.WALL;
        snake.collideWithObjectInNextCell(new Point(2, 5));
        Assert.assertFalse(snake.getIsAlive());
    }

    @Test
    public void collideNotPoisonedWithWallInNextCell() {
        snake.setPoisoned(false);
        snake.setHead(new Point(2,5));
        gridTest[5][2] = Grid.obj.WALL;
        snake.collideWithObjectInNextCell(new Point(2, 5));
        Assert.assertEquals(snake.getLen(), 0);
    }

    @Test
    public void collideNotDeadWithSnakeInNextCell() {
        snake.setAlive(true);
        snake.setHead(new Point(2,5));
        gridTest[5][2] = Grid.obj.TAIL1;
        snake.setJoints(2, new Point(2, 5));
        snake.collideWithObjectInNextCell(new Point(2, 5));
        Assert.assertTrue(snake.getIsAlive());
    }

    @Test
    public void collideDeadWithSnakeInNextCell() {
        snake.setAlive(true);
        snake.setHead(new Point(2,5));
        gridTest[5][2] = Grid.obj.TAIL1;
        snake.setJoints(2, new Point(4, 5));
        snake.collideWithObjectInNextCell(new Point(3, 5));
        Assert.assertFalse(snake.getIsAlive());
    }

    @Test
    public void collideWithObjectInNextCellAmanita() {
        snake.setPoisoned(false);
        snake.setHead(new Point(2,5));
        grid.setAmanita(new Point(5, 9));
        gridTest[5][2] = Grid.obj.AMANITA;
        snake.collideWithObjectInNextCell(new Point(2, 5));
        Assert.assertTrue(snake.isPoisoned());
    }

    @DataProvider(name = "checkOutOfMapData")
    public Object[][] checkOutOfMapData() {
        return new Object[][]{
                {new Point(5, 1000), false},
                {new Point(1000, 5), false},
                {new Point(5, -1000), false},
                {new Point(-1000, 5), false},
                {new Point(5, 5), true}
        };
    }

    @Test(dataProvider = "checkOutOfMapData")
    public void checkOutOfMapTest(Point head, boolean expect) {
        snake.setAlive(true);
        snake.setHead(head);
        boolean actual = snake.checkOutOfMap();
        Assert.assertEquals(actual, expect);
    }

    @DataProvider(name = "moveMoveData")
    public Object[][] moveMoveData() {
        return new Object[][]{
                {true, false, false, false, new Point(17,10)},
                {false, true, false, false, new Point(19,10)},
                {false, false, true, false, new Point(18,11)},
                {false, false, false, true, new Point(18,9)}
        };
    }

    @Test(dataProvider = "moveMoveData")
    public void moveMoveTest(boolean left, boolean right, boolean down, boolean up, Point expected) {
        snake.setHead(new Point(18, 10));
        snake.setJoints(2, new Point(26, 10));
        snake.setJoints(3, new Point(36, 10));
        snake.setMovingLeft(left);
        snake.setMovingRight(right);
        snake.setMovingUp(up);
        snake.setMovingDown(down);
        snake.move();
        Assert.assertEquals(snake.getHead(), expected);
    }

    @Test
    public void moveOutOfMapTest() {
        snake.setHead(new Point(1000, 10));
        snake.setJoints(2, new Point(26, 10));
        snake.setJoints(3, new Point(36, 10));
        snake.setMovingLeft(true);
        snake.setMovingRight(false);
        snake.setMovingUp(false);
        snake.setMovingDown(false);
        Point actual = snake.move();
        Assert.assertEquals(actual, new Point(36, 10));
    }

    @Test
    public void moveIfSnakePoisoned() {
        snake.setHead(new Point(10, 10));
        snake.setJoints(2, new Point(26, 10));
        snake.setJoints(3, new Point(36, 10));

        snake.setMovingLeft(true);
        snake.setMovingRight(false);
        snake.setMovingUp(false);
        snake.setMovingDown(false);

        snake.setAlive(true);
        snake.setPoisonTime(0);
        snake.setPoisoned(true);
        snake.move();
        Assert.assertEquals(snake.isPoisoned(), false);
    }
}

