package tests;

import control.Configuration;
import control.Point;
import logic.Grid;
import logic.Snake;
import logic.Amanita;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class SnakeTest {

    Configuration config;
    Grid grid;
    Snake snake;
    Grid.obj[][] gridTest;
    Amanita amanita;

    @BeforeClass
    private void inicilisation(){
        config = new Configuration();
        grid = new Grid(config);
        snake = new Snake(grid, config);
        gridTest = grid.getGrid();
        amanita = new Amanita(grid, config);
    }

    @Test
    public void collideWithObjectInNextCellFoodAndIsPoisonedTest(){
        snake.setItIsLife(true);
        snake.setPoisoned(true);
        snake.setHead(new Point(2,5));
        gridTest[5][2] = Grid.obj.FOOD;
        snake.collideWithObjectInNextCell(new Point(2, 5));
        Assert.assertFalse(snake.getItIsLife());
    }

    @Test
    public void collideWithObjectInNextCellFoodAndDontPoisonedTest(){
        snake.setPoisoned(false);
        snake.setHead(new Point(2,5));
        gridTest[5][2] = Grid.obj.FOOD;
        snake.collideWithObjectInNextCell(new Point(2, 5));
        Assert.assertEquals(snake.getLen(), 1);
    }

    @Test
    public void collideWithObjectInNextCellWallAndIsPoisonedTest(){
        snake.setItIsLife(true);
        snake.setPoisoned(false);
        snake.setHead(new Point(2,5));
        gridTest[5][2] = Grid.obj.WALL;
        snake.collideWithObjectInNextCell(new Point(2, 5));
        Assert.assertFalse(snake.getItIsLife());
    }

    @Test
    public void collideWithObjectInNextCellWallAndDontPoisonedTest(){
        snake.setPoisoned(false);
        snake.setHead(new Point(2,5));
        gridTest[5][2] = Grid.obj.WALL;
        snake.collideWithObjectInNextCell(new Point(2, 5));
        Assert.assertEquals(snake.getLen(), 2);
    }
    /*@Test
    public void collideWithObjectInNextCellSnakeAndDontDieTest(){
        snake.setItIsLife(true);
        snake.setHead(new Point(2,5));
        gridTest[5][2] = Grid.obj.TAIL;
        snake.setJoints(2, new Point(2, 5));
        snake.collideWithObjectInNextCell(new Point(2, 5));
        Assert.assertTrue(snake.getItIsLife());
    }

    @Test
    public void collideWithObjectInNextCellSnakeAndDieTest(){
        snake.setItIsLife(true);
        snake.setHead(new Point(2,5));
        gridTest[5][2] = Grid.obj.TAIL;
        snake.setJoints(2, new Point(4, 5));
        snake.collideWithObjectInNextCell(new Point(3, 5));
        Assert.assertFalse(snake.getItIsLife());
    }

    @Test
    public void collideWithObjectInNextCellAmanita(){
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
    public void checkOutOfMapTest(Point head, boolean expect){
        snake.setItIsLife(true);
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
    public void moveMoveTest(boolean left, boolean right, boolean down, boolean up, Point expected){
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
    public void moveOutOfMapTest(){
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
    public void moveIfSnakePoisoned(){
        snake.setHead(new Point(10, 10));
        snake.setJoints(2, new Point(26, 10));
        snake.setJoints(3, new Point(36, 10));

        snake.setMovingLeft(true);
        snake.setMovingRight(false);
        snake.setMovingUp(false);
        snake.setMovingDown(false);

        snake.setItIsLife(true);
        snake.setPoisonTime(0);
        snake.setPoisoned(true);
        snake.move();
        Assert.assertEquals(snake.isPoisoned(), false);
    }*/
}

