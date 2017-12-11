package control;

import visual.Board;
import visual.Menu;
import visual.Result;

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

    Game(Menu menu) {
        add(menu);
        setResizable(false);
        pack();

        setTitle("Menu");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    Game(Result result) {
        add(result);
        setResizable(false);
        pack();

        setTitle("Menu");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
