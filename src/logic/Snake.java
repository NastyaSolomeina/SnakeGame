package logic;

import snake.Point;
import visual.Board;
import java.util.ArrayDeque;

public class Snake {

    private Point head = new Point();
    private ArrayDeque<Point> tail = new ArrayDeque<Point>();

    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean movingUp = false;
    private boolean movingDown = false;

    private int joints = 0;

    public ArrayDeque<Point> getTail(){
        return new ArrayDeque(tail);
    }
    public Point getHead(){
        return new Point(head);
    }

    public void setHead(Point point) {

        head = new Point(point);
    }

    public boolean isMovingLeft()
    {
        return movingLeft;
    }

    public void setMovingLeft(boolean left) {
        movingLeft = left;
    }

    public boolean isMovingRight()
    {
        return movingRight;
    }

    public void setMovingRight(boolean right) {

        movingRight = right;
    }

    public boolean isMovingUp() {
        return movingUp;
    }

    public void setMovingUp(boolean up) {
        movingUp = up;
    }

    public boolean isMovingDown() {
        return movingDown;
    }

    public void setMovingDown(boolean down) {
        movingDown = down;
    }

    public int getJoints() {
        return joints;
    }

    public void setJoints(int j, Point point) {
        joints = j;
        tail.push(point);
    }

    public void move() {
        tail.pop();
        tail.add(new Point(head));

        if (movingLeft) {
            head.setX(head.getX() - Board.getDotSize());
        }

        if (movingRight) {
            head.setX(head.getX() + Board.getDotSize());
        }

        if (movingDown) {
            head.setY(head.getY() + Board.getDotSize());
        }

        if (movingUp) {
            head.setY(head.getY() - Board.getDotSize());
        }
    }
}
