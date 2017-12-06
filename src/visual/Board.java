package visual;

import control.Configuration;
import logic.Grid;
import logic.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class Board extends JPanel {

    private Map map;
    private Configuration config;
    private Grid grid;

    public int getSpeed() {
        return config.getSpeedTick();
    }

    public boolean snakeIsLife() {
        return map.isFirstSnakeAlive();
    }

    public Board(Configuration config) {

        this.config = config;
        grid = new Grid(this.config);
        map = new Map(config, grid);

        addKeyListener(new Keys());
        setBackground(Color.BLACK);
        setFocusable(true);

        map.initialMap();

        setPreferredSize(new Dimension(config.getWidthPS(),
                config.getHeightPS() + config.getPLine()));

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (map.isFirstSnakeAlive() && map.isSecondSnakeAlive()) {
            draw(g);
            printScore(g);
            printParty(g);
        } else {
            endGame(g);
        }
    }


    private void draw(Graphics g) {

        g.setColor(Color.WHITE);
        g.drawLine(0, config.getHeightPS(), config.getWidthPS(), config.getHeightPS());

        Color col;

        for (int y = 0; y < config.getBoardHeight(); y++) {
            for (int x = 0; x < config.getBoardWidth(); x++) {
                col = grid.getColorObject(x, y);
                g.setColor(col);
                if (itIsOval(col)) {
                    g.fillOval(x * config.getPSize(), y * config.getPSize(),
                            config.getPSize(), config.getPSize());
                } else {
                    g.fillRect(x * config.getPSize(), y * config.getPSize(),
                            config.getPSize(), config.getPSize());
                }
            }
        }
    }

    private boolean itIsOval(Color col) {
        return (col == Color.RED || col == Color.BLUE || col == Color.YELLOW || col == Color.CYAN);
    }

    private void printScore(Graphics g) {

        String message = "Score: " + map.getFirstPlayerScore();

        Font font = new Font("Times New Roman", Font.BOLD, 25);

        g.setColor(Color.WHITE);
        g.setFont(font);

        g.drawString(message, 30,config.getHeightPS() + (config.getPLine()) / 2);
    }

    private void printParty(Graphics g) {

        String message = "PoisonTime: ";

        if (map.isFirstSnakePoisoned() && map.isSecondSnakePoisoned()) {
            message += map.getPoisonTime();
        } else {
            message += "Off";
        }

        Font font = new Font("Times New Roman", Font.BOLD, 25);

        g.setColor(Color.WHITE);
        g.setFont(font);

        g.drawString(message, 200,config.getHeightPS() + (config.getPLine()) / 2);
    }


    private void endGame(Graphics g) {

        String message = "Game over";
        g.setColor(Color.red);

        if (map.haveFirstPlayerWon()) {
            message = "Player 1 Win ";
            g.setColor(Color.green);
        }
        if (map.haveSecondPlayerWon()) {
            message = "Player 2 Win ";
            g.setColor(Color.green);
        }
        if (map.haveFirstPlayerWon() && map.haveSecondPlayerWon()) {
            message = "Dead heat";
            g.setColor(Color.pink);
        }

        Font font = new Font("Times New Roman", Font.BOLD, 25);
        FontMetrics metrics = getFontMetrics(font);

        g.setFont(font);

        g.drawString(message, (config.getWidthPS() - metrics.stringWidth(message)) / 2,
               config.getHeightPS() / 2);

        String points1Count = "Player 1 result: " + map.getFirstPlayerScore();

        g.drawString(points1Count, (config.getWidthPS() - metrics.stringWidth(message)) / 2,
                config.getHeightPS() / 2 + 50);
        String points2Count = "Player 2 result: " + map.getSecondPlayerScore();

        g.drawString(points2Count, (config.getWidthPS() - metrics.stringWidth(message)) / 2,
                config.getHeightPS() / 2 + 100);
        String newGame = "Press Enter for new game";

        g.drawString(newGame, (config.getWidthPS() - metrics.stringWidth(message)) / 2,
                config.getHeightPS() / 2 + 150);
    }

    public void tick() {
        map.move();
        repaint();
    }

    private class Keys extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_ENTER) {
                grid = new Grid(config);
                map = new Map(config, grid);

                map.initialMap();
            }
            map.turnFirstSnake(key);
            map.turnSecondSnake(key);
        }
    }

}
