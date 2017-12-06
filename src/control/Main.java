package control;

import visual.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener{

    private Board board;

    private Main(Board board) {
        this.board = board;
    }

    public static void main(String[] args) {
        Configuration config = new Configuration();
        Board board = new Board(config);

        JFrame frame = new Game(board);
        frame.setVisible(true);

        Main m = new Main(board);
        m.startGame();
    }

    private void startGame() {
        Timer timer = new Timer(board.getSpeed(), this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        if (board.snakeIsLife()) {
            board.tick();
        } /*else {
            timer.stop();
        }*/
    }
}