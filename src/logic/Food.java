package logic;

import visual.Board;
import snake.Point;

import java.util.ArrayDeque;

public class Food {

    private Snake snake = new Snake();
    private int foodX;
    private int foodY;

    private final int RANDOMPOSITIONX = 40;
    private final int RANDOMPOSITIONY = 27;

    public void createFood() {

        int location = (int) (Math.random() * RANDOMPOSITIONX);
        foodX = ((location * Board.getDotSize()));

        location = (int) (Math.random() * RANDOMPOSITIONY);
        foodY = ((location * Board.getDotSize()));



        Point head = snake.getHead();

        if ((foodX == head.getX()) && (foodY == head.getY())) {
            createFood();
        }

        ArrayDeque<Point> tail= snake.getTail();

        while (tail.peek() != null){
            Point p = tail.pop();
            if ((foodX == p.getX()) && (foodY == p.getY())){
                createFood();
            }
        }
    }

    public int getFoodX() {

        return foodX;
    }

    public int getFoodY() {
        return foodY;
    }
}