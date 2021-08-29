import hooks.UnitTestsHooks;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test(groups = "unit tests")
public class PointTests extends UnitTestsHooks {

    @DataProvider
    public Object[][] positiveTests() {
        Point p1 = new Point(0, 0);
        return new Object[][]{
                new Object[]{"One coordinate is NaN", new Point(Double.NaN, 1), new Point(2, Double.NaN), Double.NaN},
                new Object[]{"Correct data", new Point(1, 1), new Point(4, 5), 5.0},
                new Object[]{"One coordinate is null", new Point(1, 1), null, Double.NaN},
                new Object[]{"The same coordinates", new Point(1, 1), new Point(1, 1), 0},
                new Object[]{"The same object", p1, p1, 0},
        };
    }

    @Test
    public void testNonInt() {
        double delta = 0.1;
        double expected = 6.4;
        Point p1 = new Point(1, 4);
        Point p2 = new Point(5, 9);
        Assert.assertTrue(Math.abs(p1.distance(p2) - expected) < delta);
    }

    @Test(dataProvider = "positiveTests")
    public void dataDriven(String _desc, Point p1, Point p2, double expected) {
        Assert.assertEquals(p1.distance(p2), expected);
    }
}
