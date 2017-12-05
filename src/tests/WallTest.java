package tests;

import control.Point;
import logic.Wall;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WallTest {
    @Test
    public void changeDirTest() {
        Wall wall = new Wall(new Point(2,5), new Point(2,6), new Point(3, 2));
        wall.changeDir();
        Assert.assertTrue((wall.getDir().getX() == -3) &&(wall.getDir().getY() == -2));
    }

}
