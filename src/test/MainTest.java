package test;

/*import visual.Board;
import logic.Snake;
import snake.Point;
import logic.Food;
import org.junit.Assert;

public class MainTest {
    @org.junit.Test
    public void checkCollisionsBigY(){
        Board board = new Board();
        Snake testSnake = board.getSnake();
        testSnake.setHead(new Point(500, 10000));
        board.checkCollisions();
        boolean isLife = board.isSnakeLife();
        Assert.assertFalse(isLife);
    }
    @org.junit.Test
    public void checkCollisionsBigX(){
        Board board = new Board();
        Snake testSnake = board.getSnake();
        testSnake.setHead(new Point(10000, 500));
        board.checkCollisions();
        boolean isLife = board.isSnakeLife();
        Assert.assertFalse(isLife);
    }

    @org.junit.Test
    public void checkCollisionsSmallY(){
        Board board = new Board();
        Snake testSnake = board.getSnake();
        testSnake.setHead(new Point(500, -10));
        board.checkCollisions();
        boolean isLife = board.isSnakeLife();
        Assert.assertFalse(isLife);
    }

    @org.junit.Test
    public void checkCollisionsSmallX(){
        Board board = new Board();
        Snake testSnake = board.getSnake();
        testSnake.setHead(new Point(-10, 500));
        board.checkCollisions();
        boolean isLife = board.isSnakeLife();
        Assert.assertFalse(isLife);
    }

    @org.junit.Test
    public void checkCollisionsNormalPoint(){
        Board board = new Board();
        Snake testSnake = board.getSnake();
        testSnake.setHead(new Point(500, 500));
        board.checkCollisions();
        boolean isLife = board.isSnakeLife();
        Assert.assertTrue(isLife);
    }

    @org.junit.Test
    public void checkCollisionsDontDieTest(){
        Board board = new Board();
        Snake testSnake = board.getSnake();
        testSnake.setJoints(4, new Point(500, 500));
        testSnake.setJoints(5, new Point(550, 500));
        testSnake.setJoints(6, new Point(600, 500));
        testSnake.setHead(new Point(650, 500));
        board.checkCollisions();
        boolean isLife = board.isSnakeLife();
        Assert.assertTrue(isLife);
    }
    @org.junit.Test
    public void checkCollisionsDieTest(){
        Board board = new Board();
        Snake testSnake = board.getSnake();
        testSnake.setJoints(4, new Point(500, 500));
        testSnake.setJoints(5, new Point(550, 500));
        testSnake.setJoints(6, new Point(600, 500));
        testSnake.setJoints(7, new Point(650, 500));
        testSnake.setJoints(8, new Point(700, 500));
        testSnake.setJoints(9, new Point(750, 500));
        testSnake.setHead(new Point(500, 500));
        board.checkCollisions();
        boolean isLife = board.isSnakeLife();
        Assert.assertFalse(isLife);
    }
    @org.junit.Test
    public void checkFoodCollisionsPointsPlusFiveTest(){
        Board board = new Board();
        Snake testSnake = board.getSnake();
        Food food = board.getFood();
        food.createFood();
        int foodX = food.getFoodX();
        int foodY = food.getFoodY();
        int oldPoints = board.getScore();
        testSnake.setHead(new Point(foodX - 15, foodY));
        board.checkFoodCollisions();
        int newPoints = board.getScore();
        Assert.assertEquals(5, newPoints - oldPoints);
    }

    @org.junit.Test
    public void checkFoodCollisionsPointsDontChangeTest(){
        Board board = new Board();
        Snake testSnake = board.getSnake();
        Food food = board.getFood();
        food.createFood();
        int foodX = food.getFoodX();
        int foodY = food.getFoodY();
        int oldPoints = board.getScore();
        testSnake.setHead(new Point(foodX - 25, foodY));
        board.checkFoodCollisions();
        int newPoints = board.getScore();
        Assert.assertEquals(0, newPoints - oldPoints);
    } @org.junit.Test
public void setJointsTest(){
    Snake testSnake = new Snake();
    int oldJoins = testSnake.getJoints();
    testSnake.setJoints(1, new Point(50, 50) );
    Assert.assertEquals(oldJoins + 1, testSnake.getJoints());
}
    @org.junit.Test
    public void moveLeftTest(){
        Snake testSnake = new Snake();
        testSnake.setHead(new Point(500, 337));
        testSnake.setJoints(2,new Point(450,337));
        testSnake.setJoints(3,new Point(475, 337));
        testSnake.setMovingLeft(true);
        testSnake.move();
        Assert.assertEquals(475, testSnake.getHead().x);
    }
    @org.junit.Test
    public void moveRightTest(){
        Snake testSnake = new Snake();
        testSnake.setHead(new Point(500, 337));
        testSnake.setJoints(2,new Point(450,337));
        testSnake.setJoints(3,new Point(475, 337));
        testSnake.setMovingRight(true);
        testSnake.move();
        Assert.assertEquals(525, testSnake.getHead().x);
    }@org.junit.Test
public void moveUpTest(){
    Snake testSnake = new Snake();
    testSnake.setHead(new Point(500, 337));
    testSnake.setJoints(2,new Point(450,337));
    testSnake.setJoints(3,new Point(475, 337));
    testSnake.setMovingUp(true);
    testSnake.move();
    Assert.assertEquals(312, testSnake.getHead().y);
}@org.junit.Test
public void moveDownTest(){
    Snake testSnake = new Snake();
    testSnake.setHead(new Point(500, 337));
    testSnake.setJoints(2,new Point(450,337));
    testSnake.setJoints(3,new Point(475, 337));
    testSnake.setMovingDown(true);
    testSnake.move();
    Assert.assertEquals(362, testSnake.getHead().y);
} @org.junit.Test
public void toStringTest(){
    Point point = new Point(500, 964);
    String pointString = point.toString();
    Assert.assertEquals("Point [x=500, y=964]", pointString);
}
}*/

