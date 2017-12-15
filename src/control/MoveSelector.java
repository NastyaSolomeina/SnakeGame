package control;

import logic.Direction;
import logic.SnakeNumber;

import java.awt.event.KeyEvent;

public class MoveSelector {
    public SnakeMove select(int command) {

        if (command == KeyEvent.VK_A) {
            return new SnakeMove(SnakeNumber.Second, Direction.Left);
        }

        if (command == KeyEvent.VK_D) {
            return new SnakeMove(SnakeNumber.Second, Direction.Right);
        }

        if (command == KeyEvent.VK_W) {
            return new SnakeMove(SnakeNumber.Second, Direction.Up);
        }

        if (command == KeyEvent.VK_S) {
            return new SnakeMove(SnakeNumber.Second, Direction.Down);
        }
        if (command == KeyEvent.VK_LEFT) {
            return new SnakeMove(SnakeNumber.First, Direction.Left);
        }

        if (command == KeyEvent.VK_RIGHT) {
            return new SnakeMove(SnakeNumber.First, Direction.Right);
        }

        if (command == KeyEvent.VK_UP) {
            return new SnakeMove(SnakeNumber.First, Direction.Up);
        }

        if (command == KeyEvent.VK_DOWN) {
            return new SnakeMove(SnakeNumber.First, Direction.Down);
        }
        return null;
    }
}
