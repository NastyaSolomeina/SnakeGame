package logic;


import control.Point;

public class Wall {
    private Point start;
    private Point end;
    private Point dir;

    public Wall(Point start, Point end, Point move) {
        this.start = start;
        this.end = end;
        dir = move;
    }

    public Point getStart() { return new Point(start.getX(),start.getY()); }
    public Point getEnd() { return new Point(end.getX(),end.getY()); }
    public Point getDir() { return new Point(dir.getX(),dir.getY()); }

    public void setStart(Point st){ start = new Point(st);}
    public void setEnd(Point end){ this.end = new Point(end);}

    public Wall(Wall wall){
        start = wall.getStart();
        end = wall.getEnd();
        dir = wall.getDir();
    }

    public void changeDir(){
        dir.setX(-dir.getX());
        dir.setY(-dir.getY());
    }
}
