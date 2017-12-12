package tests;

import control.Configuration;
import control.Point;
import logic.Amanita;
import logic.Grid;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AmanitaTests {

    private Configuration config;
    private Grid gridEx;
    private Grid.obj[][] grid;
    private Amanita amanita;

    @BeforeMethod
    public void initialization() {
        config = new Configuration();
        gridEx = new Grid(config);
        grid = gridEx.getGrid();
        amanita = new Amanita(gridEx, config);
    }

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

    @Test
    public void updateWithNullLocation() {
        amanita.setFreq(0);
        amanita.setLocation(null);
        amanita.updatePositionOfAmanita();
        Point location = amanita.getLocation();
        Assert.assertNotNull(location);
        Assert.assertEquals(grid[location.getY()][location.getX()], Grid.obj.AMANITA);
    }

    @Test
    public void updateWithNoLifetime() {
        amanita.setFreq(0);
        amanita.setLifeTime(0);
        amanita.updatePositionOfAmanita();
        Assert.assertNull(amanita.getLocation());
        Assert.assertEquals(amanita.getLifeTime(), config.getLifeTimeAmanita());
        Assert.assertEquals(amanita.getFreq(), config.getFrequencyOfOccurrenceAmanita());
    }

    @Test
    public void decreaseLifetime() {
        amanita.setFreq(0);
        amanita.setLifeTime(123);
        amanita.updatePositionOfAmanita();
        Assert.assertEquals(amanita.getLifeTime(), 123 - 1);
    }
}
