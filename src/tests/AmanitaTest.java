package tests;
import control.Point;
import logic.Grid;
import control.Configuration;
import org.testng.Assert;
import logic.Wall;
import logic.Amanita;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AmanitaTest {

    private Configuration config = new Configuration();
    private Grid gridEx = new Grid(config);
    private Grid.obj[][] grid = gridEx.getGrid();
    private Amanita amanita = new Amanita(gridEx, config);

    /*@Test
    public void updatePositionOfAmanitaPoisonedTrueTest(){
        gridEx.changeIsPoisoned(true);
        amanita.setLifeTime(10);
        amanita.updatePositionOfAmanita();
        Assert.assertEquals(amanita.getLifeTime(), 10);
    }

    @Test
    public void updatePositionOfAmanitaTest(){
        gridEx.changeIsPoisoned(false);
        amanita.setFreq(10);
        amanita.updatePositionOfAmanita();
        Assert.assertEquals(amanita.getFreq(), 9);
    }
*/
    @Test
    public void createTest(){
        amanita.setLocation(new Point(2, 5));
        Point oldAmanita = amanita.getLocation();
        amanita.create();
        Assert.assertNotEquals(oldAmanita, amanita.getLocation());
    }
}
