package triangle;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class CorrectTriangleTNGTest {
    private Triangle triangle;
    private final String MESSAGE = "[This is triangle with side a = %f, side b = %f, side c = %f]";

    @DataProvider(name = "ordinaryTriangleData")
    public Object[][] ordinaryTriangleDataProvider() {
        return new Object[][]{
                {8.0, 5.0, 4.0},
                {0.004, 0.005, 0.008},
                {5000.0, 8000.0, 4000.0},
                {5000.1, 8000.1, 4000.1},
        };
    }

    @DataProvider(name = "equilateralTriangleData")
    public Object[][] equilateralTriangleDataProvider() {
        return new Object[][]{
                {1.0, 1.0, 1.0},
                {0.002, 0.002, 0.002},
                {4000d, 4000d, 4000d},
                {8000.1, 8000.1, 8000.1},
                {Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE},
                {Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE,}
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
                {5000.1, 5000.1, getHypotenuse(5000.1)},
        };
    }

    private Double getHypotenuse(double a, double b) {
        return Math.sqrt(a * a + b * b);
    }

    private Double getHypotenuse(double a) {
        return Math.sqrt(2 * a * a);
    }

    // проверим метод getMessage()

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

    // проверим метод detectTriangle()

    @Test(dataProvider = "ordinaryTriangleData")
    public void detectOrdinaryTriangleTest(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.detectTriangle(), 4, String.format(MESSAGE, a, b, c));
    }

    @Test(dataProvider = "equilateralTriangleData")
    public void detectEquilateralTriangleTest(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.detectTriangle(), 3, String.format(MESSAGE, a, b, c));
    }

    @Test(dataProvider = "isoscelesTriangleData")
    public void detectIsoscelesTriangleTest(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.detectTriangle(), 2, String.format(MESSAGE, a, b, c));
    }

    @Test(dataProvider = "rectangularTriangleData")
    public void detectRectangularTriangleTest(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.detectTriangle(), 8, String.format(MESSAGE, a, b, c));
    }

    @Test(dataProvider = "rectangularIsoscelesTriangleData")
    public void detectRectangularIsoscelesTriangleTest(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.detectTriangle(), 10, String.format(MESSAGE, a, b, c));
    }

    // проверим метод getSquare()

    @Test(dataProvider = "ordinaryTriangleData")
    public void getSquareOrdinaryTriangleTest(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.getSquare(), calculateSquareOfTriangle(a, b, c));
    }

    @Test
    public void getSquareEquilateralTriangleSimpleTest() {
        triangle = new Triangle(1.0, 1.0, 1.0);
        Assert.assertEquals(triangle.getSquare(), calculateSquareOfTriangle(1.0, 1.0, 1.0));
    }

    @Test
    public void getSquareEquilateralTriangleLittleSidesTest() {
        triangle = new Triangle(0.0001, 0.0001, 0.0001);
        Assert.assertEquals(triangle.getSquare(), calculateSquareOfTriangle(0.0001, 0.0001, 0.0001));
    }

    @Test(expectedExceptions = Exception.class)
    public void getSquareEquilateralTriangleMaxValueTest() {
        triangle = new Triangle(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
        Assert.assertEquals(triangle.getSquare(), calculateSquareOfTriangle(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE));
    }

    @Test(dataProvider = "isoscelesTriangleData")
    public void getSquareIsoscelesTriangleTest(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.getSquare(), calculateSquareOfTriangle(a, b, c));
    }

    @Test(dataProvider = "rectangularTriangleData")
    public void getSquareRectangularTriangleTest(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.getSquare(), calculateSquareOfTriangle(a, b, c));
    }

    @Test(dataProvider = "rectangularIsoscelesTriangleData")
    public void getSquareRectangularIsoscelesTriangleTest(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.getSquare(), calculateSquareOfTriangle(a, b, c));
    }

    @AfterMethod
    public void afterMethod() {
        triangle = null;
    }

    private Double calculateSquareOfTriangle(Double a, Double b, Double c) {
        BigDecimal sideA = new BigDecimal(a);
        BigDecimal sideB = new BigDecimal(b);
        BigDecimal sideC = new BigDecimal(c);
        BigDecimal perimeter = sideA.add(sideB).add(sideC).divide(new BigDecimal(2));
        BigDecimal square = perimeter.multiply((
                perimeter.subtract(sideA)).
                multiply((perimeter.subtract(sideB))).
                multiply(perimeter.subtract(sideC)
                ));
        Double result = Math.sqrt(Double.valueOf(square.toString()));
        return result;
    }
}
