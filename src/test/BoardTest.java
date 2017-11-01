package test;

import org.junit.Before;
import org.junit.BeforeClass;
import visual.Board;
import logic.Snake;
import snake.Point;
import logic.Food;

//import org.junit.Assert;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import org.testng.Assert;
import org.testng.annotations.Test;

public class BoardTest {

    private Board board = new Board();
    private Snake testSnake = board.getSnake();
    private Food food = board.getFood();

    private Map<Boolean, Point> testPoint = new HashMap<Boolean, Point>();

    public void setUp(){
        testPoint.put(false, new Point(500, 10000));
        testPoint.put(false, new Point(10000, 500));
        testPoint.put(false, new Point(500, -10));
        testPoint.put(false, new Point(-10, 500));
        testPoint.put(true, new Point(500, 500));
    }

    @org.junit.Test
    public void checkCollisionsTest(){
        for (Iterator iterator = testPoint.keySet().iterator(); iterator.hasNext();) {
            Boolean expected = (boolean) iterator.next();
            Point thisPoint = testPoint.get(expected);
            testSnake.setHead(thisPoint);
            board.checkCollisions();
            Assert.assertEquals((boolean)expected, board.isSnakeLife());
        }

    }
    @org.junit.Test
    public void checkCollisionsDontDieTest(){
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
        food.createFood();
        int foodX = food.getFoodX();
        int foodY = food.getFoodY();
        int oldPoints = board.getScore();
        testSnake.setHead(new Point(foodX - 25, foodY));
        board.checkFoodCollisions();
        int newPoints = board.getScore();
        Assert.assertEquals(0, newPoints - oldPoints);
    }
}
