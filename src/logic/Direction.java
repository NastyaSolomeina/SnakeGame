package logic;

public enum Direction {
    Left, Up, Right, Down;

    public static Direction enumerate(int value) {
        switch (value) {
            case 0:
                return Left;
            case 1:
                return Up;
            case 2:
                return Right;
            case 3:
                return Down;
        }
        return null;
    }
}
