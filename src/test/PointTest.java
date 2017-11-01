package test;

import snake.Point;
import org.junit.Assert;

public class PointTest {
    @org.junit.Test
    public void toStringTest(){
        Point testPoint = new Point(200, 562);
        Assert.assertEquals("Point [x=200, y=562]", testPoint.toString());
    }
}
