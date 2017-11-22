package logic;

import control.Configuration;
import control.Point;

import java.util.ArrayList;

public class ArrayWalls {

    private ArrayList<Wall> walls = new ArrayList<Wall>();
    private Grid grid;
    private Configuration config;

    private boolean goUpOrLeft = false;
    private boolean goDownOrRight = false;

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public Grid getGrid() {
        return grid;
    }

    public int getCount(){ return walls.size(); }

    public ArrayWalls(Grid gr, Configuration c){
        grid = gr;
        config = c;
    }

    public void add(Wall wall){
        walls.add(wall);
        grid.addNewWall(wall);
    }

    public void move(){
        for (int i = 0; i < walls.size(); i++){
            goDownOrRight = false;
            goUpOrLeft = false;
            Point dir = walls.get(i).getDir();
            if (dir.equals(new Point(1,0)) ||
                    dir.equals(new Point(0,1))){
                moveToRightOrDown(walls.get(i), i);
                continue;
            }
            if (dir.equals(new Point(-1,0)) ||
                    dir.equals(new Point(0,-1))){
                moveToLeftOrUp(walls.get(i), i);
                continue;
            }
        }
    }

    public void moveToRightOrDown(Wall wall, int i){
        goDownOrRight = true;
        Point next = getNextStepEnd(wall);
        if ((next.getX() >= config.getBoardWidth()) ||
                next.getY() >= config.getBoardHeight()){
            if (goUpOrLeft){ return;  }
            wall.changeDir();
            moveToLeftOrUp(wall, i);
            return;
        }
        if(!grid.cellIsEmpty(next.getX(), next.getY())){
            if (grid.itIscellWithWall(next.getX(), next.getY())){
                findAndDeployMeetingWallEnd(next);
            }
            if (goUpOrLeft){ return;  }
            wall.changeDir();
            moveToLeftOrUp(wall, i);
            return;
        }
        wall.setEnd(next);
        wall.setStart(getNextStepStart(wall));
        grid.wallMoved(wall, i);
    }

    public void moveToLeftOrUp(Wall wall, int i){
        goUpOrLeft = true;
        Point next = getNextStepStart(wall);
        if ((next.getX() < 0) || (next.getY() < 0)){
            if(goDownOrRight){ return; }
            wall.changeDir();
            moveToRightOrDown(wall, i);
            return;
        }
        if (!grid.cellIsEmpty(next.getX(), next.getY())){
            if (grid.itIscellWithWall(next.getX(), next.getY())){
                findAndDeployMeetingWallStart(next);
            }
            if(goDownOrRight){ return; }
            wall.changeDir();
            moveToRightOrDown(wall, i);
            return;

        }
        wall.setEnd(getNextStepEnd(wall));
        wall.setStart(next);
        grid.wallMoved(wall, i);
    }

    public void findAndDeployMeetingWallStart(Point collision){
        for(Wall w: walls){
            if( w.getEnd().equals(collision)){
                w.changeDir();
            }
        }
    }

    public void findAndDeployMeetingWallEnd(Point collision){
        for(Wall w: walls){
            if( w.getStart().equals(collision)){
                w.changeDir();
            }
        }
    }

    Point getNextStepStart(Wall wall){
        return new Point(wall.getStart().getX() + wall.getDir().getX(),
                wall.getStart().getY() + wall.getDir().getY());
    }

    Point getNextStepEnd(Wall wall){
        return new Point(wall.getEnd().getX() + wall.getDir().getX(),
                wall.getEnd().getY() + wall.getDir().getY());
    }

    public int wallNumberSnakeEats(Point head){
        Wall w;
        for (int i = 0; i < walls.size(); i++){
            w = walls.get(i);
            if (snakeFinishedWall(head, w)){
                remove(i);
                return i;
            }
            if (snakeEatHorizontalWall(head, w)){
                if (head.getX() - w.getStart().getX() > 0){ // Не вся левая часть съедена
                    add( new Wall(w.getStart(), new Point(head.getX() - 1, head.getY()), w.getDir()));
                }
                if (w.getEnd().getX() - head.getX() > 0){
                    add(new Wall(new Point(head.getX()+1, head.getY()),w.getEnd(), w.getDir()));
                }
                remove(i);
                return i;
            }
            if (snakeEatVerticalWall(head, w)){
                if (head.getY() - w.getStart().getY() > 0){
                    add( new Wall(w.getStart(), new Point(head.getX(), head.getY() - 1), w.getDir()));
                }
                if (w.getEnd().getY() - head.getY() > 0){
                    add(new Wall(new Point(head.getX(), head.getY() + 1),w.getEnd(), w.getDir()));
                }
                remove(i);
                return i;
            }
        }
        return -1;
    }

    public void remove(int i){
        walls.remove(i);
        grid.removeWall(i);
    }

    public boolean snakeFinishedWall(Point h, Wall w){
        return (h.getX() == w.getStart().getX() && h.getX() == w.getEnd().getX()
                && h.getY() == w.getEnd().getY() && h.getY() == w.getStart().getY());
    }

    public boolean snakeEatHorizontalWall(Point h, Wall w){
        return (h.getY() == w.getStart().getY()  && h.getY() == w.getEnd().getY()
                && w.getStart().getX()<=h.getX() && h.getX()<= w.getEnd().getX());
    }

    public boolean snakeEatVerticalWall(Point h, Wall w){
        return (h.getX() == w.getStart().getX() && h.getX() == w.getEnd().getX()
                && w.getStart().getY()<=h.getY() && h.getY() <= w.getEnd().getY());
    }
}
