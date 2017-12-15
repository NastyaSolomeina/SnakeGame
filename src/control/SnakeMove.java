package control;

import logic.Direction;
import logic.Snake;
import logic.SnakeNumber;

public class SnakeMove {
    private SnakeNumber snakeNumber;
    private Direction direction;

    public SnakeMove(SnakeNumber number, Direction dir) {
        snakeNumber = number;
        direction = dir;
    }

    public SnakeNumber getSnakeNumber() {
        return snakeNumber;
    }

    public void changeDirection(Snake snake) {
        if (direction == Direction.Left && snake.getDirection() != Direction.Right) {
            snake.setDirection(true, false, false, false);
        }
        if (direction == Direction.Right && snake.getDirection() != Direction.Left) {
            snake.setDirection(false, true, false, false);
        }
        if (direction == Direction.Up && snake.getDirection() != Direction.Down) {
            snake.setDirection(false, false, true, false);
        }
        if (direction == Direction.Down && snake.getDirection() != Direction.Up) {
            snake.setDirection(false, false, false, true);
        }
    }
}
