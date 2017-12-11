package visual;

import control.Configuration;
import logic.Grid;
import logic.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static logic.SnakeNumber.First;
import static logic.SnakeNumber.Second;

@SuppressWarnings("serial")
public class Board extends JPanel {

    private Map map;
    private Configuration config;
    private Grid grid;

    public int getSpeed() {
        return config.getSpeedTick();
    }

    public boolean snake1IsLife() {
        return map.isSnakeAlive(First);
    }

    public boolean snake2IsLife() {
        return map.isSnakeAlive(Second);
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
        if (map.isSnakeAlive(First) && map.isSnakeAlive(Second)) {
            draw(g);
            printScore(g);
            printParty(g);
        } else if (config.getCountOfPlayers() == 2) {
            config.setGameStoped(true);
        } else {
            if (config.getCurrentRound() != config.getCountOfRounds() + 1) {
                endGame(g);
            }
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

        String message1 = "Score: " + map.getScore(First);
        String message2 = "Score: " + map.getScore(Second);

        Font font = new Font("Times New Roman", Font.BOLD, 25);

        g.setColor(Color.WHITE);
        g.setFont(font);

        g.drawString(message1, 30, config.getHeightPS() + (config.getPLine()) / 2);
        g.drawString(message2, 150, config.getHeightPS() + (config.getPLine()) / 2);
    }

    private void printParty(Graphics g) {

        String message1 = "PoisonTime: ";
        String message2 = "PoisonTime: ";

        if (map.isSnakePoisoned(First)) {
            message1 += map.getPoisonTime(First);
        } else {
            message1 += "Off";
        }
        if (map.isSnakePoisoned(Second)) {
            message2 += map.getPoisonTime(Second);
        } else {
            message2 += "Off";
        }

        Font font = new Font("Times New Roman", Font.BOLD, 25);

        g.setColor(Color.WHITE);
        g.setFont(font);

        g.drawString(message1, 400, config.getHeightPS() + (config.getPLine()) / 2);
        g.drawString(message2, 600, config.getHeightPS() + (config.getPLine()) / 2);
    }


    private void endGame(Graphics g) {

        String message = "";
        g.setColor(Color.red);

        if (map.havePlayerWon(First)) {
            message = "Player 1 Win ";
            g.setColor(Color.green);
        }
        if (map.havePlayerWon(Second)) {
            message = "Player 2 Win ";
            g.setColor(Color.green);
        }
        if (map.havePlayerWon(First) && map.havePlayerWon(Second)) {
            message = "Dead heat";
            g.setColor(Color.pink);
        }

        Font font = new Font("Times New Roman", Font.BOLD, 25);
        FontMetrics metrics = getFontMetrics(font);

        g.setFont(font);

        g.drawString(message, (config.getWidthPS() - metrics.stringWidth(message)) / 2,
               config.getHeightPS() / 2);

        String points1Count = "Player 1 result: " + map.getScore(First);

        g.drawString(points1Count, (config.getWidthPS() - metrics.stringWidth(message)) / 2,
                config.getHeightPS() / 2 + 50);
        String points2Count = "Player 2 result: " + map.getScore(Second);

        g.drawString(points2Count, (config.getWidthPS() - metrics.stringWidth(message)) / 2,
                config.getHeightPS() / 2 + 100);
        String newGame = "Press Enter for new game";

        g.drawString(newGame, (config.getWidthPS() - metrics.stringWidth(message)) / 2,
                config.getHeightPS() / 2 + 150);

        config.setCurrentRound();
    }

    public void tick() {
        map.move();
        repaint();
    }

    private class Keys extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (config.getCountOfPlayers() == 2 && !map.isSnakeAlive(First) && !map.isSnakeAlive(Second)) {
                config.setGameStoped(true);
            } else if (key == KeyEvent.VK_ENTER && config.getCurrentRound() <= config.getCountOfRounds()
                    && (!map.isSnakeAlive(First) || !map.isSnakeAlive(Second))) {
                System.out.println(config.getCurrentRound());
                grid = new Grid(config);
                map = new Map(config, grid);
                map.initialMap();
            } else if (key == KeyEvent.VK_ENTER && (!map.isSnakeAlive(First) || !map.isSnakeAlive(Second))
                    && config.getCurrentRound() > config.getCountOfRounds()) {
                config.setGameStoped(true);
            }
            map.turnFirstSnake(key);
            map.turnSecondSnake(key);
        }
    }

}
