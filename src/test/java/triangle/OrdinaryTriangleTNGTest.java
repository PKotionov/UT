package triangle;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OrdinaryTriangleTNGTest {
    Triangle ordinaryTriangle;

    @DataProvider(name = "ordinaryTrianglesData")
    public Object[][] ordinaryTrianglesDataProvider() {
        return new Object[][]{
                {new Double(4), new Double(8), new Double(5)},
                {new Double(-4), new Double(-8), new Double(-5)},
                {new Double(4), new Double(8), new Double(-5)},
                {new Double(4), new Double(-8), new Double(5)},
                {new Double(-4), new Double(8), new Double(5)},
                {new Double(-4), new Double(-8), new Double(5)},
                {new Double(4), new Double(-8), new Double(-5)},
                {new Double(-4), new Double(8), new Double(-5)},
                {new Double(0), new Double(0), new Double(0)},
                {new Double(0), new Double(8), new Double(5)},
                {new Double(4), new Double(0), new Double(5)},
                {new Double(4), new Double(8), new Double(0)},
                {new Double(0), new Double(0), new Double(5)},
                {new Double(4), new Double(0), new Double(0)},
                {new Double(0), new Double(8), new Double(0)},
                {null, new Double(8), new Double(5)},
                {new Double(4), null, new Double(5)},
                {new Double(4), new Double(8), null},
                {null, null, new Double(5)},
                {new Double(4), null, null},
                {null, null, null},
                {new Double(Double.POSITIVE_INFINITY), new Double(8), new Double(5)},
                {new Double(4), new Double(Double.POSITIVE_INFINITY), new Double(5)},
                {new Double(4), new Double(8), new Double(Double.POSITIVE_INFINITY)},
                {new Double(Double.POSITIVE_INFINITY), new Double(Double.POSITIVE_INFINITY), new Double(5)},
                {new Double(4), new Double(Double.POSITIVE_INFINITY), new Double(Double.POSITIVE_INFINITY)},
                {new Double(Double.POSITIVE_INFINITY), new Double(8), new Double(Double.POSITIVE_INFINITY)},
                {new Double(Double.POSITIVE_INFINITY), new Double(Double.POSITIVE_INFINITY), new Double(Double.POSITIVE_INFINITY)},
                {new Double(Double.NEGATIVE_INFINITY), new Double(8), new Double(5)},
                {new Double(4), new Double(Double.NEGATIVE_INFINITY), new Double(5)},
                {new Double(4), new Double(8), new Double(Double.NEGATIVE_INFINITY)},
                {new Double(Double.NEGATIVE_INFINITY), new Double(Double.NEGATIVE_INFINITY), new Double(5)},
                {new Double(Double.NEGATIVE_INFINITY), new Double(8), new Double(Double.NEGATIVE_INFINITY)},
                {new Double(4), new Double(Double.NEGATIVE_INFINITY), new Double(Double.NEGATIVE_INFINITY)},
                {new Double(Double.NEGATIVE_INFINITY), new Double(Double.NEGATIVE_INFINITY), new Double(Double.NEGATIVE_INFINITY)},
        };
    }

    @Test(dataProvider = "ordinaryTrianglesData")
    public void test(double a, double b, double c) {
        Assert.assertEquals(new Triangle(a, b, c).checkTriangle(), false);
    }


    @DataProvider(name = "subtractionProvider")
    public Object[][] subtractionProvider() {
        return new Object[][]
                {
                        {new Double(5), new Double(2), new Double(3)},
                        {new Double(-1), new Double(2), new Double(-3)},
                        {new Double(-10), new Double(-2), new Double(-8)}
                };
    }


    @Test(dataProvider = "subtractionProvider")
    public void tstSubtraction(Double minuend, Double subtrahend, Double residual) {
        Assert.assertEquals(minuend + subtrahend + residual, 0);
    }


}
