package tests;

import control.Configuration;
import control.Point;
import logic.Amanita;
import logic.Grid;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AmanitaTests {

    private Configuration config = new Configuration();
    private Grid gridEx = new Grid(config);
    private Grid.obj[][] grid = gridEx.getGrid();
    private Amanita amanita = new Amanita(gridEx, config);

    @Test
    public void updatePositionOfAmanitaPoisonedTrue() {
        gridEx.setIsFirstPoisoned(true);
        amanita.setLifeTime(10);
        amanita.updatePositionOfAmanita();
        Assert.assertEquals(amanita.getLifeTime(), 10);
    }

    @Test
    public void updatePositionOfAmanita() {
        gridEx.setIsFirstPoisoned(false);
        amanita.setFreq(10);
        amanita.updatePositionOfAmanita();
        Assert.assertEquals(amanita.getFreq(), 9);
    }

    @Test
    public void create() {
        amanita.setLocation(new Point(2, 5));
        Point oldAmanita = amanita.getLocation();
        amanita.create();
        Assert.assertNotEquals(oldAmanita, amanita.getLocation());
    }
}
