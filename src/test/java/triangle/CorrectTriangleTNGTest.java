package triangle;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CorrectTriangleTNGTest {
    Triangle triangle;

    @DataProvider(name = "ordinaryTriangleData")
    public Object[][] ordinaryTriangleDataProvider() {
        return new Object[][]{
                {8.0, 5.0, 4.0},
                {0.004, 0.005, 0.008},
                {5000.0, 8000.0, 4000.0}
        };
    }

    @DataProvider(name = "equilateralTriangleData")
    public Object[][] equilateralTriangleDataProvider() {
        return new Object[][]{
                {1.0, 1.0, 1.0},
                {0.002, 0.002, 0.002},
                {Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE}
        };
    }

    @DataProvider(name = "isoscelesTriangleData")
    public Object[][] isoscelesTriangleDataProvider() {
        return new Object[][]{
                {2.0, 1.0, 2.0},
                {0.002, 0.002, 0.001},
                {5000.0, 5000.0, 7660.44}
        };
    }

    @DataProvider(name = "rectangularTriangleData")
    public Object[][] rectangularTriangleDataProvider() {
        return new Object[][]{
                {3.0, 4.0, 5.0},
                {0.004, 0.003, 0.005},
                {5000.0, 3000.0, 4000.0},
                {4000.1, getHypotenuse(3000.1, 4000.1), 3000.1}
        };
    }

    @DataProvider(name = "rectangularIsoscelesTriangleData")
    //прямоугольные равнобедренные треугольники
    public Object[][] rectangularIsoscelesTriangleDataProvider() {
        return new Object[][]{
                {7.0, 7.0, getHypotenuse(7.0)},
                {5.0, 5.0, getHypotenuse(5.0)},
                {0.007, 0.007, getHypotenuse(0.007)},
                {0.005, 0.005, getHypotenuse(0.005)},
                {7000.0, 7000.0, getHypotenuse(7000.0)},
                {5000.1, 5000.1, getHypotenuse(5000.1)}
        };
    }

    private Double getHypotenuse(double a, double b) {
        return Math.sqrt(a * a + b * b);
    }

    private Double getHypotenuse(double a) {
        return Math.sqrt(2 * a * a);
    }

    @Test(dataProvider = "ordinaryTriangleData")
    public void checkMessageOrdinaryTriangleTest(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.getMessage(), "");
    }

    @Test(dataProvider = "equilateralTriangleData")
    public void checkMessageEquilateralTriangleTest(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.getMessage(), "");
    }

    @Test(dataProvider = "isoscelesTriangleData")
    public void checkMessageIsoscelesTriangleTest(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.getMessage(), "");
    }

    @Test(dataProvider = "rectangularTriangleData")
    public void checkMessageRectangularTriangleTest(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.getMessage(), "");
    }

    @Test(dataProvider = "ordinaryTriangleData")
    public void detectOrdinaryTriangleTest(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.detectTriangle(), 4);
    }

    @Test(dataProvider = "equilateralTriangleData")
    public void detectEquilateralTriangleTest(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.detectTriangle(), 3);
    }

    @Test(dataProvider = "isoscelesTriangleData")
    public void detectIsoscelesTriangleTest(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.detectTriangle(), 2);
    }

    @Test(dataProvider = "rectangularTriangleData")
    public void detectRectangularTriangleTest(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.detectTriangle(), 8);
    }

    @Test(dataProvider = "rectangularIsoscelesTriangleData")
    public void detectRectangularIsoscelesTriangleTest(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.detectTriangle(), 10);
    }

    @Test(dataProvider = "ordinaryTriangleData")
    public void getSquareOrdinaryTriangleTest(Double a, Double b, Double c){
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(new BigDecimal(triangle.getSquare()).setScale(2, RoundingMode.UP).doubleValue(), 8.19);
    }

    @AfterMethod
    public void afterMethod() {
        triangle = null;
    }
}
