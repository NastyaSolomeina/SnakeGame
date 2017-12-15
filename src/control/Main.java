package control;

import visual.Board;
import visual.Menu;
import visual.Result;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener{

    private Board board;

    private static boolean choice = true;

    public Main(Board board) {
        this.board = board;
    }

    public Main() {
    }

    public void setChoice(boolean choice) {
        Main.choice = choice;
    }

    public static void main(String[] args) {
        Configuration config = new Configuration();
        Menu menu = new Menu(config);
        JFrame frameMenu = new Game(menu);
        while (choice) {
            frameMenu.setVisible(true);
        }
        frameMenu.setVisible(false);
        config.initializationScores();
        config.inicializationRounds();
        Board board = new Board(config);

        JFrame frame = new Game(board);
        frame.setVisible(true);

        Main m = new Main(board);
        m.startGame();
        int i = 0;
        while (!config.isGameStoped()) {
            System.out.print("");
        }
        frame.setVisible(false);
        Result result = new Result(config);
        JFrame frameResult = new Game(result);
        frameResult.setVisible(true);
    }

    public void startGame() {
        Timer timer = new Timer(board.getSpeed(), this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        if (board.snake1IsLife() && board.snake2IsLife()) {
            board.tick();
        }
    }
}