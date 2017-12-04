package logic;

import control.Configuration;
import control.Point;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Grid {
    public enum obj{ HEAD, TAIL, FOOD, WALL, EMPTY, AMANITA }

    private Configuration config;
    private obj[][] grid;

    private Point head;
    private Point endTail;
    private Point food;
    private Point amanita;
    private ArrayList<Wall> walls = new ArrayList<Wall>();
    private boolean snakeIsPoisoned = false;

    public boolean isSnakeIsPoisoned(){ return snakeIsPoisoned; }
    public void changeIsPoisoned(boolean b) { snakeIsPoisoned = b; }

    public Point getHead() {
        return head;
    }

    public Point getEndTail() {
        return endTail;
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

    public void setEndTail(Point endTail) {
        this.endTail = endTail;
    }

    public int getWeidth(){ return grid[0].length; }
    public int getHeight(){ return grid.length; }

    public obj[][] getGrid(){ return grid; }

    public void setHead(Point p){ head = p; }

    public Grid(Configuration config){
        this.config = config;
        grid = new obj[config.getBoardHeight()][config.getBoardWidth()];

        for (int i = 0; i < config.getBoardHeight(); i++){
            for (int j = 0; j < config.getBoardWidth(); j++){
                grid[i][j] = obj.EMPTY;
            }
        }
    }

    public void setSnake(Point newHead, Point newEndTail){
        grid[head.getY()][head.getX()] = obj.TAIL;
        head = newHead;

        if (endTail != null) {
            grid[endTail.getY()][endTail.getX()] = obj.EMPTY;
        }
        grid[head.getY()][head.getX()] = obj.HEAD;
        endTail = newEndTail;
    }

    public void initialSnake(Snake snake){
        head = snake.getHead();
        endTail = snake.getTail().peek();
        grid[head.getY()][head.getX()] = obj.HEAD;
        for (Point item : snake.getTail()) {
            grid[item.getY()][item.getX()] = obj.TAIL;
        }
    }

    public void setFood(Point f){
        if (!(food == null)){
            if (!food.equals(head)){
                grid[food.getY()][food.getX()] = obj.EMPTY;
            }
        }
        food = new Point(f);
        grid[food.getY()][food.getX()] = obj.FOOD;
    }


    public void addNewWall(Wall wall){
        walls.add(new Wall(wall));
        redrawWall(wall, obj.WALL);
    }

    public void redrawWall(Wall wall, obj o){
        for (int x = wall.getStart().getX(); x < wall.getEnd().getX() + 1; x++) {
            for (int y = wall.getStart().getY(); y < wall.getEnd().getY() + 1; y++) {
                grid[y][x] = o;
            }
        }
    }

    public boolean cellIsEmpty(int x, int y){
        return (grid[y][x] == obj.EMPTY);
    }

    public boolean itIscellWithFood(int x, int y){
        return (grid[y][x] == obj.FOOD);
    }
    public boolean itIscellWithAmanita(int x, int y){
        return (grid[y][x] == obj.AMANITA);
    }
    public boolean itIscellWithWall(int x, int y){
        return (grid[y][x] == obj.WALL);
    }
    public boolean itIscellWithSnake(int x, int y){
        return (grid[y][x] == obj.TAIL || grid[y][x] == obj.HEAD);
    }

    public void wallMoved(Wall wall, int i){
        redrawWall(walls.get(i), obj.EMPTY);
        walls.set(i, new Wall(wall));
        redrawWall(walls.get(i), obj.WALL);

    }

    public void removeWall(int i){
        walls.remove(i);
    }


    public void setAmanita(Point loc){
        if (loc != null){
            grid[loc.getY()][loc.getX()] = obj.AMANITA;
            amanita = loc;
        } else{
            grid[amanita.getY()][amanita.getX()] = obj.EMPTY;
        }
    }

    public Color getColorObject(int x, int y){
        switch (grid[y][x]){
            case FOOD:
                return Color.GREEN;
            case AMANITA:
                return Color.ORANGE;
            case HEAD:
                return Color.RED;
            case TAIL:
                return Color.BLUE;
            case WALL:
                return Color.WHITE;
        }
        return Color.BLACK;
    }
}
