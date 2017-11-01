package snake;

import visual.Board;
import javax.swing.JFrame;

public class Game extends JFrame {

    public Game(Board board) {
        add(board);
        setResizable(false);
        pack();

        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
