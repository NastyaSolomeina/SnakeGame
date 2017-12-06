package control;

import visual.Board;

import javax.swing.*;

class Game extends JFrame {

    Game(Board board) {
        add(board);
        setResizable(false);
        pack();

        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
