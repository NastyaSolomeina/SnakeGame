package visual;

import logic.Food;
import logic.Snake;

import snake.Point;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.util.ArrayDeque;

@SuppressWarnings("serial")
public class Board extends JPanel {

    private final int BOARDWIDTH = 1000;
    private final int BOARDHEIGHT = 675;
    private final int POINTSLINE = 50;
    private static final int PIXELSIZE = 25;

    private int score = 0;
    private boolean isLife = true;
    private int speed = 75;

    private Snake snake = new Snake();
    private Food food = new Food();

    public Snake getSnake(){
        return snake;
    }

    public Food getFood(){
        return food;
    }

    public int getScore(){
        return score;
    }

    public int getSpeed(){ return speed; }

    public Board() {

        addKeyListener(new Keys());
        setBackground(Color.BLACK);
        setFocusable(true);

        setPreferredSize(new Dimension(BOARDWIDTH, BOARDHEIGHT + POINTSLINE));

        initializeGame();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        draw(g);
    }

    public boolean isSnakeLife(){
        return isLife;
    }

    void draw(Graphics g) {
        if (isLife) {
            g.setColor(Color.WHITE);
            g.drawLine(0, BOARDHEIGHT, BOARDWIDTH, BOARDHEIGHT);

            printScore(g);
            //Image img = new ImageIcon("green.png").getImage();
            //ImageIcon f = new ImageIcon("snake_3_package/green.png");
            //foodImg = f.getImage();
            //g.drawImage(img, 0, 0, null);
            g.setColor(Color.green);
            g.fillRect(food.getFoodX(), food.getFoodY(), PIXELSIZE, PIXELSIZE);

            g.setColor(Color.RED);
            Point head = snake.getHead();
            g.fillOval(head.getX(), head.getY(), PIXELSIZE, PIXELSIZE);

            ArrayDeque<Point> tail= snake.getTail();
            g.setColor(Color.BLUE);
            for(int i = 0; i<snake.getJoints() - 1; i++){
                Point p = tail.pop();
                g.fillOval(p.getX(), p.getY(), PIXELSIZE, PIXELSIZE);
            }

        } else {
            endGame(g);
        }
    }

    void printScore(Graphics g) {

        String message = "Score: " + score;

        Font font = new Font("Times New Roman", Font.BOLD, 25);

        g.setColor(Color.WHITE);
        g.setFont(font);

        g.drawString(message, 30,BOARDHEIGHT + (POINTSLINE) / 2);
    }

    void initializeGame() {

        snake.setHead(new Point(BOARDWIDTH / 2, BOARDHEIGHT / 2));
        snake.setJoints(2,new Point(BOARDWIDTH / 2 - PIXELSIZE * 2,BOARDHEIGHT / 2));
        snake.setJoints(3,new Point(BOARDWIDTH / 2 - PIXELSIZE,BOARDHEIGHT / 2));


        snake.setMovingRight(true);
        food.createFood();
    }

    public void checkFoodCollisions() { //Змея рядом с едой

        Point head = snake.getHead();
        ArrayDeque<Point> tail= snake.getTail();

        if ((proximity(head.getX(), food.getFoodX(), 20))
                && (proximity(head.getY(), food.getFoodY(), 20))) {
            snake.setJoints(snake.getJoints() + 1, tail.pop());
            score += 5;
            food.createFood();
        }
    }

    public void checkCollisions() { // змея рядом с краями доски или ест себя

        Point head = snake.getHead();
        ArrayDeque<Point> tail= snake.getTail();

        for (int i = 0; i < snake.getJoints() - 1; i++) {

            Point p = tail.pop();
            if ((head.getX() == p.getX()) && (head.getY() == p.getY())) {
                isLife = false;
            }
        }

        // Края доски
        if (head.getY() > BOARDHEIGHT - PIXELSIZE) {
            isLife = false;
        }

        if (head.getY() < 0) {
            isLife = false;
        }

        if (head.getX() > BOARDWIDTH - PIXELSIZE) {
            isLife = false;
        }

        if (head.getX() < 0) {
            isLife = false;
        }
    }

    void endGame(Graphics g) {

        String message = "Game over";


        Font font = new Font("Times New Roman", Font.BOLD, 25);
        FontMetrics metrics = getFontMetrics(font);

        g.setColor(Color.red);
        g.setFont(font);

        g.drawString(message, (BOARDWIDTH - metrics.stringWidth(message)) / 2,
                BOARDHEIGHT / 2);

        String pointsCount = "Your result: " + score;

        g.drawString(pointsCount, (BOARDWIDTH - metrics.stringWidth(message)) / 2,
                BOARDHEIGHT / 2 + 50);
    }

    public void tick(){
        snake.move();
        checkFoodCollisions();
        checkCollisions();


        repaint();
    }


    private class Keys extends KeyAdapter {

        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!snake.isMovingRight())) {
                snake.setMovingLeft(true);
                snake.setMovingUp(false);
                snake.setMovingDown(false);
            }

            if ((key == KeyEvent.VK_RIGHT) && (!snake.isMovingLeft())) {
                snake.setMovingRight(true);
                snake.setMovingUp(false);
                snake.setMovingDown(false);
            }

            if ((key == KeyEvent.VK_UP) && (!snake.isMovingDown())) {
                snake.setMovingUp(true);
                snake.setMovingRight(false);
                snake.setMovingLeft(false);
            }

            if ((key == KeyEvent.VK_DOWN) && (!snake.isMovingUp())) {
                snake.setMovingDown(true);
                snake.setMovingRight(false);
                snake.setMovingLeft(false);
            }
        }
    }

    private boolean proximity(int a, int b, int closeness) {

        return Math.abs((long) a - b) <= closeness;
    }

    public static int getDotSize() {
        return PIXELSIZE;
    }
}