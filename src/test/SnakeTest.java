package test;

import logic.Snake;
import snake.Point;
import org.junit.Assert;

public class SnakeTest {

    private
    Snake testSnake = new Snake();

    @org.junit.Test
    public void setJointsTest(){
        int oldJoins = testSnake.getJoints();
        testSnake.setJoints(1, new Point(50, 50) );
        Assert.assertEquals(oldJoins + 1, testSnake.getJoints());
    }
    @org.junit.Test
    public void moveLeftTest(){
        testSnake.setHead(new Point(500, 337));
        testSnake.setJoints(2,new Point(450,337));
        testSnake.setJoints(3,new Point(475, 337));
        testSnake.setMovingLeft(true);
        testSnake.move();
        Assert.assertEquals(475, testSnake.getHead().getX());
    }
    @org.junit.Test
    public void moveRightTest(){
        testSnake.setHead(new Point(500, 337));
        testSnake.setJoints(2,new Point(450,337));
        testSnake.setJoints(3,new Point(475, 337));
        testSnake.setMovingRight(true);
        testSnake.move();
        Assert.assertEquals(525, testSnake.getHead().getX());
    }@org.junit.Test
    public void moveUpTest(){
        testSnake.setHead(new Point(500, 337));
        testSnake.setJoints(2,new Point(450,337));
        testSnake.setJoints(3,new Point(475, 337));
        testSnake.setMovingUp(true);
        testSnake.move();
        Assert.assertEquals(312, testSnake.getHead().getY());
    }@org.junit.Test
    public void moveDownTest(){
        Snake testSnake = new Snake();
        testSnake.setHead(new Point(500, 337));
        testSnake.setJoints(2,new Point(450,337));
        testSnake.setJoints(3,new Point(475, 337));
        testSnake.setMovingDown(true);
        testSnake.move();
        Assert.assertEquals(362, testSnake.getHead().getY());
    }
}