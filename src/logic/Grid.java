package logic;

import control.Configuration;
import control.Point;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Grid {
    public enum obj{ HEAD1, HEAD2, TAIL1, TAIL2, FOOD, WALL, EMPTY, AMANITA }

    private Configuration config;
    private obj[][] grid;

    private Point head_1;
    private Point endTail_1;
    private Point head_2;
    private Point endTail_2;
    private Point food;
    private Point amanita;
    private ArrayList<Wall> walls = new ArrayList<Wall>();
    private boolean snakeIsPoisoned_1 = false;
    private boolean snakeIsPoisoned_2 = false;

    public boolean isSnakeIsPoisoned_1(){ return snakeIsPoisoned_1; }
    public void changeIsPoisoned_1(boolean b) { snakeIsPoisoned_1 = b; }

    public boolean isSnakeIsPoisoned_2(){ return snakeIsPoisoned_2; }
    public void changeIsPoisoned_2(boolean b) { snakeIsPoisoned_2 = b; }

    public Point getHead_1() {
        return head_1;
    }

    public Point getEndTail_1() {
        return endTail_1;
    }

    public Point getHead_2() {
        return head_2;
    }

    public Point getEndTail_2() {
        return endTail_2;
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

    public void setEndTail_1(Point endTail) {
        this.endTail_1 = endTail;
    }

    public void setEndTail_2(Point endTail) {
        this.endTail_2 = endTail;
    }

    public int getWeidth(){ return grid[0].length; }
    public int getHeight(){ return grid.length; }

    public obj[][] getGrid(){ return grid; }

    public void setHead_1(Point p){ head_1 = p; }

    public void setHead_2(Point p){ head_2 = p; }
    public Grid(Configuration config){
        this.config = config;
        grid = new obj[config.getBoardHeight()][config.getBoardWidth()];

        for (int i = 0; i < config.getBoardHeight(); i++){
            for (int j = 0; j < config.getBoardWidth(); j++){
                grid[i][j] = obj.EMPTY;
            }
        }
    }

    public void setSnake_1(Point newHead, Point newEndTail){
        grid[head_1.getY()][head_1.getX()] = obj.TAIL1;
        head_1 = newHead;

        if (endTail_1 != null) {
            grid[endTail_1.getY()][endTail_1.getX()] = obj.EMPTY;
        }
        grid[head_1.getY()][head_1.getX()] = obj.HEAD1;
        endTail_1 = newEndTail;
    }

    public void initialSnake_1(Snake snake){
        head_1 = snake.getHead();
        endTail_1 = snake.getTail().peek();
        grid[head_1.getY()][head_1.getX()] = obj.HEAD1;
        for (Point item : snake.getTail()) {
            grid[item.getY()][item.getX()] = obj.TAIL1;
        }
    }
    public void setSnake_2(Point newHead, Point newEndTail){
        grid[head_2.getY()][head_2.getX()] = obj.TAIL2;
        head_2 = newHead;

        if (endTail_2 != null) {
            grid[endTail_2.getY()][endTail_2.getX()] = obj.EMPTY;
        }
        grid[head_2.getY()][head_2.getX()] = obj.HEAD2;
        endTail_2 = newEndTail;
    }

    public void initialSnake_2(Snake snake){
        head_2 = snake.getHead();
        endTail_2 = snake.getTail().peek();
        grid[head_2.getY()][head_2.getX()] = obj.HEAD2;
        for (Point item : snake.getTail()) {
            grid[item.getY()][item.getX()] = obj.TAIL2;
        }
    }
    public void setFood(Point f){
        if (!(food == null)){
            if (!food.equals(head_1)){
                grid[food.getY()][food.getX()] = obj.EMPTY;
            }
            else if (!food.equals(head_2)){
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
    public boolean itIscellWithSnake1(int x, int y){
        return (grid[y][x] == obj.TAIL1 || grid[y][x] == obj.HEAD1);
    }

    public boolean itIscellWithSnake2(int x, int y){
        return (grid[y][x] == obj.TAIL2|| grid[y][x] == obj.HEAD2);
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
