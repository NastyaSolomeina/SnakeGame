package logic;

import control.Configuration;
import control.Point;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Snake {

    private Point head = new Point();
    private ArrayDeque<Point> tail = new ArrayDeque<>();

    private boolean isPoisoned = false;
    private int poisonTime = 0;

    private Direction direction = null;
    private int[] controls;
    @Deprecated
    private boolean movingLeft = false;
    @Deprecated
    private boolean movingRight = false;
    @Deprecated
    private boolean movingUp = false;
    @Deprecated
    private boolean movingDown = false;

    private boolean alreadyTurned = false;
    private boolean isAlive = true;

    private int len = 0;
    private Grid grid;
    private Configuration config;

    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }

    public void setPoisoned(boolean poisoned) {
        isPoisoned = poisoned;
    }

    @Deprecated
    public Snake(Grid grid, Configuration configuration) {
        this.grid = grid;
        config = configuration;
    }

    public Snake(Grid grid, Configuration configuration,
                 int left, int up, int right, int down) {
        this.grid = grid;
        config = configuration;
        controls = new int[]{left, up, right, down};
        Arrays.sort(controls);
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

    @SuppressWarnings("unchecked")
    public ArrayDeque<Point> getTail() {
        return new ArrayDeque(tail);
    }
    public boolean getAlreadyTurned() { return alreadyTurned; }
    public void setAlreadyTurned(boolean t) { alreadyTurned = t; }

    public Point getHead() {
        return new Point(head);
    }
    public void setHead(Point point) { head = new Point(point); }

    public Direction getDirection() {
        return direction;
    }

    public void turn(int key) {
        int keyPosition = Arrays.binarySearch(controls, key);
        int currentDirection = direction.ordinal();
        if (Math.abs(keyPosition - currentDirection) != 2 && !alreadyTurned)
            direction = Direction.enumerate(key);
    }

    public void setDirection(boolean left, boolean right, boolean up, boolean down) {
        movingLeft = left;
        movingRight = right;
        movingUp = up;
        movingDown = down;
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

    public boolean getIsAlive() {
        return isAlive;
    }

    public void setJoints(int length, Point point) {
        len = length;
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
            isAlive = false;
        }

        if (head.getY() < 0) {
            isAlive = false;
        }

        if (head.getX() > grid.getWidth() - 1) {
            isAlive = false;
        }

        if (head.getX() < 0) {
            isAlive = false;
        }
        return isAlive;
    }

    public void collideWithObjectInNextCell(Point p) {
        if (grid.itIsCellWithFood(head.getX(), head.getY())) {
            if (isPoisoned) {
                isAlive = false;
                return;
            }
            setJoints(getLen()+1, p);
        }
        if (grid.itIsCellWithWall(head.getX(), head.getY())) {
            if (!isPoisoned) {
                isAlive = false;
                return;
            }
            setJoints(getLen()+1, p);
            return;
        }
        if (grid.itIsCellWithSnake1(head.getX(), head.getY())) {
            if ((head.equals(p)) && (p != tail.peek())) {
                return;
            }
            isAlive = false;
        }
        if (grid.itIscellWithSnake2(head.getX(), head.getY())) {
            if ((head.equals(p)) && (p != tail.peek())) {
                return;
            }
            isAlive = false;
        }
        if (grid.itIsCellWithAmanita(head.getX(), head.getY())) {
            isPoisoned = true;
            poisonTime = config.getPoisonTime();
            grid.setAmanita(null);
            grid.setIsFirstPoisoned(true);
        }
    }
}
