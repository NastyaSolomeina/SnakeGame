package logic;

import control.Configuration;
import control.Point;

import java.util.ArrayDeque;

public class Snake {

    private Point head = new Point();
    private ArrayDeque<Point> tail = new ArrayDeque<Point>();

    private boolean isPoisoned = false;
    private int poisonTime = 0;

    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean movingUp = false;
    private boolean movingDown = false;

    private boolean alreadyTurned = false;
    private boolean itIsLife = true;

    private int len = 0;
    private Grid grid;
    private Configuration config;

    public void setItIsLife(boolean itIsLife) {
        this.itIsLife = itIsLife;
    }

    public void setPoisoned(boolean poisoned) {
        isPoisoned = poisoned;
    }

    public Snake(Grid grid, Configuration c) {
        this.grid = grid;
        config = c;
    }

    public void setPoisonTime(int val) {
        poisonTime = val;
    }

    public int getPoisonTime() {
        return poisonTime;
    }

    public boolean isPoisoned() {
        return isPoisoned;
    }

    public ArrayDeque<Point> getTail() {
        return new ArrayDeque(tail);
    }
    public boolean getAlreadyTurned() { return alreadyTurned; }
    public void setAlreadyTurned(boolean t) { alreadyTurned = t; }

    public Point getHead() {
        return new Point(head);
    }
    public void setHead(Point point) { head = new Point(point); }

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
    public void setMovingRight(boolean right) { movingRight = right; }

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

    public int getLen() {
        return len;
    }

    public boolean getItIsLife() {
        return itIsLife;
    }

    public void setJoints(int l, Point point) {
        len = l;
        tail.push(point);
    }

    public Point move() {
        Point p = tail.pop();
        tail.add(new Point(head));

        if (movingLeft) {
            head.setX(head.getX() - 1);
        }

        if (movingRight) {
            head.setX(head.getX() + 1);
        }

        if (movingDown) {
            head.setY(head.getY() + 1);
        }

        if (movingUp) {
            head.setY(head.getY() - 1);
        }
        alreadyTurned = false;

        if (!checkOutOfMap()) {
            return p;
        }

        collideWithObjectInNextCell(p);

        if (poisonTime == 0) {
            isPoisoned = false;
            grid.setIsFirstPoisoned(false);
        }else{
            poisonTime--;
        }

        return tail.peek();
    }

    public boolean checkOutOfMap() {
        if (head.getY() > grid.getHeight() - 1) {
            itIsLife = false;
        }

        if (head.getY() < 0) {
            itIsLife = false;
        }

        if (head.getX() > grid.getWidth() - 1) {
            itIsLife = false;
        }

        if (head.getX() < 0) {
            itIsLife = false;
        }
        return itIsLife;
    }

    public void collideWithObjectInNextCell(Point p) {
        if (grid.cellIsEmpty(head.getX(), head.getY())) {
            //
        }
        if (grid.itIsCellWithFood(head.getX(), head.getY())) {
            if (isPoisoned) {
                itIsLife = false;
                return;
            }
            setJoints(getLen()+1, p);
        }
        if (grid.itIsCellWithWall(head.getX(), head.getY())) {
            if (!isPoisoned) {
                itIsLife = false;
                return;
            }
            setJoints(getLen()+1, p);
            return;
        }
        if (grid.itIsCellWithSnake1(head.getX(), head.getY())) {
            if ((head.equals(p)) && (p != tail.peek())) {
                return;
            }
            itIsLife = false;
        }
        if (grid.itIscellWithSnake2(head.getX(), head.getY())) {
            if ((head.equals(p)) && (p != tail.peek())) {
                return;
            }
            itIsLife = false;
        }
        if (grid.itIsCellWithAmanita(head.getX(), head.getY())) {
            isPoisoned = true;
            poisonTime = config.getPoisonTime();
            grid.setAmanita(null);
            grid.setIsFirstPoisoned(true);
        }
    }
}
