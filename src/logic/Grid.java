package logic;

import control.Configuration;
import control.Point;

import java.awt.*;
import java.util.ArrayList;

public class Grid {
    public enum obj{ HEAD1, HEAD2, TAIL1, TAIL2, FOOD, WALL, EMPTY, AMANITA }

    private obj[][] grid;

    private Point firstHead;
    private Point firstTail;
    private Point secondHead;
    private Point secondTail;
    private Point food;
    private Point amanita;
    private ArrayList<Wall> walls = new ArrayList<>();
    private boolean isFirstPoisoned = false;
    private boolean isSecondPoisoned = false;

    public Grid(Configuration config) {
        grid = new obj[config.getBoardHeight()][config.getBoardWidth()];
        for (int i = 0; i < config.getBoardHeight(); i++) {
            for (int j = 0; j < config.getBoardWidth(); j++) {
                grid[i][j] = obj.EMPTY;
            }
        }
    }

    public boolean getIsFirstPoisoned() {
        return isFirstPoisoned;
    }

    public void setIsFirstPoisoned(boolean b) {
        isFirstPoisoned = b;
    }

    public boolean getIsSecondPoisoned() {
        return isSecondPoisoned;
    }

    public void setIsSecondPoisoned(boolean b) {
        isSecondPoisoned = b;
    }

    public Point getFirstHead() {
        return firstHead;
    }

    public void setFirstHead(Point p) {
        firstHead = p;
    }

    public Point getFirstTail() {
        return firstTail;
    }

    public void setFirstTail(Point endTail) {
        this.firstTail = endTail;
    }

    public Point getSecondHead() {
        return secondHead;
    }

    public void setSecondHead(Point p) {
        secondHead = p;
    }

    public Point getSecondTail() {
        return secondTail;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }
    public Point getAmanita() {
        return amanita;
    }
    public Point getFood() {
        return food;
    }

    public void setSecondTail(Point endTail) {
        this.secondTail = endTail;
    }

    public obj[][] getGrid() {
        return grid;
    }

    public int getWidth() {
        return grid[0].length;
    }

    public int getHeight() {
        return grid.length;
    }

    public void setFirstSnake(Point newHead, Point newEndTail) {
        grid[firstHead.getY()][firstHead.getX()] = obj.TAIL1;
        firstHead = newHead;

        if (firstTail != null) {
            grid[firstTail.getY()][firstTail.getX()] = obj.EMPTY;
        }
        grid[firstHead.getY()][firstHead.getX()] = obj.HEAD1;
        firstTail = newEndTail;
    }

    public void initialFirstSnake(Snake snake) {
        firstHead = snake.getHead();
        firstTail = snake.getTail().peek();
        grid[firstHead.getY()][firstHead.getX()] = obj.HEAD1;
        for (Point item : snake.getTail()) {
            grid[item.getY()][item.getX()] = obj.TAIL1;
        }
    }

    public void setSecondSnake(Point newHead, Point newEndTail) {
        grid[secondHead.getY()][secondHead.getX()] = obj.TAIL2;
        secondHead = newHead;

        if (secondTail != null) {
            grid[secondTail.getY()][secondTail.getX()] = obj.EMPTY;
        }
        grid[secondHead.getY()][secondHead.getX()] = obj.HEAD2;
        secondTail = newEndTail;
    }

    public void initialSecondSnake(Snake snake) {
        secondHead = snake.getHead();
        secondTail = snake.getTail().peek();
        grid[secondHead.getY()][secondHead.getX()] = obj.HEAD2;
        for (Point item : snake.getTail()) {
            grid[item.getY()][item.getX()] = obj.TAIL2;
        }
    }

    public void setFood(Point f) {
        if (!(food == null)) {
            if (!food.equals(firstHead)) {
                grid[food.getY()][food.getX()] = obj.EMPTY;
            } else if (!food.equals(secondHead)) {
                grid[food.getY()][food.getX()] = obj.EMPTY;
            }
        }
        food = new Point(f);
        grid[food.getY()][food.getX()] = obj.FOOD;
    }


    public void addNewWall(Wall wall) {
        walls.add(new Wall(wall));
        redrawWall(wall, obj.WALL);
    }

    public void redrawWall(Wall wall, obj o) {
        for (int x = wall.getStart().getX(); x < wall.getEnd().getX() + 1; x++) {
            for (int y = wall.getStart().getY(); y < wall.getEnd().getY() + 1; y++) {
                grid[y][x] = o;
            }
        }
    }

    public boolean cellIsEmpty(int x, int y) {
        return (grid[y][x] == obj.EMPTY);
    }

    public boolean itIsCellWithFood(int x, int y) {
        return (grid[y][x] == obj.FOOD);
    }

    public boolean itIsCellWithAmanita(int x, int y) {
        return (grid[y][x] == obj.AMANITA);
    }

    public boolean itIsCellWithWall(int x, int y) {
        return (grid[y][x] == obj.WALL);
    }

    public boolean itIsCellWithSnake1(int x, int y) {
        return (grid[y][x] == obj.TAIL1 || grid[y][x] == obj.HEAD1);
    }

    public boolean itIscellWithSnake2(int x, int y) {
        return (grid[y][x] == obj.TAIL2|| grid[y][x] == obj.HEAD2);
    }

    public void wallMoved(Wall wall, int i) {
        redrawWall(walls.get(i), obj.EMPTY);
        walls.set(i, new Wall(wall));
        redrawWall(walls.get(i), obj.WALL);

    }

    public void removeWall(int i) {
        walls.remove(i);
    }


    public void setAmanita(Point loc) {
        if (loc != null) {
            grid[loc.getY()][loc.getX()] = obj.AMANITA;
            amanita = loc;
        } else{
            grid[amanita.getY()][amanita.getX()] = obj.EMPTY;
        }
    }

    public Color getColorObject(int x, int y) {
        switch (grid[y][x]) {
            case FOOD:
                return Color.GREEN;
            case AMANITA:
                return Color.ORANGE;
            case HEAD1:
                return Color.RED;
            case TAIL1:
                return Color.BLUE;
            case HEAD2:
                return Color.YELLOW;
            case TAIL2:
                return Color.CYAN;
            case WALL:
                return Color.WHITE;
        }
        return Color.BLACK;
    }
}
