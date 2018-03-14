package triangle;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class IncorrectTriangleTNGTest {
    private Triangle triangle;
    private final String MESSAGE = "[Triangle doesn't exist with side a = %f, side b = %f, side c = %f]";

    @DataProvider(name = "negativeTrianglesData")
    public Object[][] negativeTrianglesDataProvider() {
        return new Object[][]{
                // равносторонний
                {-1d, -1d, -1d},
                {-1d, 1d, 1d},
                {1d, -1d, 1d},
                {1d, 1d, -1d},
                {-1d, -1d, 1d},
                {1d, -1d, -1d},
                {-1d, 1d, -1d},
                {-Double.MIN_VALUE, -Double.MIN_VALUE, -Double.MIN_VALUE,},
                {-Double.MAX_VALUE, -Double.MAX_VALUE, -Double.MAX_VALUE,},
                // равнобедренный
                {-2d, -1d, -2d},
                {2d, 1d, -2d},
                {2d, -1d, 2d},
                {-2d, 1d, 2d},
                {-2d, -1d, 2d},
                {2d, -1d, -2d},
                {-2d, 1d, -2d},
                // прямоугольный
                {-3d, 4d, 5d},
                {3d, -4d, 5d},
                {3d, 4d, -5d},
                {-3d, -4d, 5d},
                {3d, -4d, -5d},
                {-3d, 4d, -5d},
                {-3d, -4d, -5d},
                // обычный
                {new Double(-4), new Double(-8), new Double(-5)},
                {new Double(4), new Double(8), new Double(-5)},
                {new Double(4), new Double(-8), new Double(5)},
                {new Double(-4), new Double(8), new Double(5)},
                {new Double(-4), new Double(-8), new Double(5)},
                {new Double(4), new Double(-8), new Double(-5)},
                {new Double(-4), new Double(8), new Double(-5)},
                //a+b+c=0
                {new Double(-4), new Double(2), new Double(2)},
                //(p*(p-a)*(p-b)*(p-c))<0, где p=(a+b+c)/2.
                {new Double(-4), new Double(-8), new Double(-5)},
        };
    }

    @DataProvider(name = "zeroTrianglesData")
    public Object[][] zeroTrianglesDataProvider() {
        return new Object[][]{
                {new Double(0), new Double(0), new Double(0)},
                {new Double(0), new Double(8), new Double(5)},
                {new Double(4), new Double(0), new Double(5)},
                {new Double(4), new Double(8), new Double(0)},
                {new Double(0), new Double(0), new Double(5)},
                {new Double(4), new Double(0), new Double(0)},
                {new Double(0), new Double(8), new Double(0)},
        };
    }

    @DataProvider(name = "infinityTrianglesData")
    public Object[][] infinityTrianglesDataProvider() {
        return new Object[][]{
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
                {Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY},
                {new Double(Double.MAX_VALUE), new Double(Double.MAX_VALUE), new Double(5)},          //a+b=+infinity
                {new Double(Double.MIN_VALUE), new Double(Double.MIN_VALUE), new Double(5)},          //a+b=-infinity
                {new Double(Double.MAX_VALUE), new Double(8), new Double(Double.MAX_VALUE)},          //a+c=+infinity
                {new Double(Double.MIN_VALUE), new Double(8), new Double(Double.MIN_VALUE)},          //a+c=-infinity
                {new Double(4), new Double(Double.MAX_VALUE), new Double(Double.MAX_VALUE)},          //b+c=+infinity
                {new Double(4), new Double(Double.MIN_VALUE), new Double(Double.MIN_VALUE)},          //b+c=-infinity
        };
    }

    @DataProvider(name = "incorrectTrianglesData")
    public Object[][] incorrectTrianglesDataProvider() {
        return new Object[][]{
                {4d, 8d, 20d},                           //a+b<c
                {4d, 10d, 5d},                           //a+c<b
                {15d, 8d, 5d},                           //b+c<a
                {1.0, 1.0, 2.0},                         //a+b=c
                {0.0001, 0.0002, 0.0001},                //a+c=b
                {2000.0, 1000.0, 1000.0},                //b+c=a
        };
    }

    @DataProvider(name = "nullSideProvider")
    public Object[][] nullSideProvider() {
        return new Object[][]{
                {null, new Double(8), new Double(5)},
                {new Double(4), null, new Double(5)},
                {new Double(4), new Double(8), null},
                {null, null, new Double(5)},
                {new Double(4), null, null},
                {null, null, null}
        };
    }

    @Test(dataProvider = "negativeTrianglesData")
    public void checkNegativeSideTriangleTest(Double sideA, Double sideB, Double sideC) {
        this.triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertEquals(triangle.checkTriangle(), false, String.format(MESSAGE, sideA, sideB, sideC));
    }

    @Test(dataProvider = "zeroTrianglesData")
    public void checkZeroSideTriangleTest(Double sideA, Double sideB, Double sideC) {
        this.triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertEquals(triangle.checkTriangle(), false, String.format(MESSAGE, sideA, sideB, sideC));
    }

    @Test(dataProvider = "infinityTrianglesData")
    public void checkInfinitySideTriangleTest(Double sideA, Double sideB, Double sideC) {
        this.triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertEquals(triangle.checkTriangle(), false, String.format(MESSAGE, sideA, sideB, sideC));
    }

    @Test(dataProvider = "incorrectTrianglesData")
    public void checkIncorrectTriangleTest(Double sideA, Double sideB, Double sideC) {
        this.triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertEquals(triangle.checkTriangle(), false, String.format(MESSAGE, sideA, sideB, sideC));
    }

    @Test(expectedExceptions = NullPointerException.class, dataProvider = "nullSideProvider")
    public void checkTriangleNullSideTest(Double sideA, Double sideB, Double sideC) {
        this.triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertEquals(triangle.checkTriangle(), false, String.format(MESSAGE, sideA, sideB, sideC));
    }

    @Test
    public void checkMessageSmokeTest() {
        this.triangle = new Triangle(1, 1, 1);
        triangle.checkTriangle();
        Assert.assertEquals(triangle.getMessage(), "");
    }

    @Test
    public void checkMessageSideATest() {
        this.triangle = new Triangle(-1, 1, 1);
        triangle.checkTriangle();
        Assert.assertEquals(triangle.getMessage(), "a<=0");
    }

    @Test
    public void checkMessageSideBTest() {
        this.triangle = new Triangle(1, 0, 1);
        triangle.checkTriangle();
        Assert.assertEquals(triangle.getMessage(), "b<=0");
    }

    @Test(enabled = false)
    // We can't get message "c<=0" because we always get message "a+c<=b" or "a<=0" or "b<=0"
    public void checkMessageSideCTest() {
        this.triangle = new Triangle(1, 2, 0);
        triangle.checkTriangle();
        Assert.assertEquals(triangle.getMessage(), "c<=0");
    }

    @Test
    public void checkMessageSumSideABTest() {
        this.triangle = new Triangle(2.5, 3.7, 10);
        triangle.checkTriangle();
        Assert.assertEquals(triangle.getMessage(), "a+b<=c");
    }

    @Test
    public void checkMessageSumSideBCTest() {
        this.triangle = new Triangle(10, 0.001, 0.8);
        triangle.checkTriangle();
        Assert.assertEquals(triangle.getMessage(), "b+c<=a");
    }

    @Test
    public void checkMessageSumSideACTest() {
        this.triangle = new Triangle(2.5, 10, 0.01);
        triangle.checkTriangle();
        Assert.assertEquals(triangle.getMessage(), "a+c<=b");
    }

    @Test(dataProvider = "negativeTrianglesData")
    public void detectNegativeSideTriangleTest(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.detectTriangle(), 0, String.format(MESSAGE, a, b, c));
    }

    @Test(dataProvider = "zeroTrianglesData")
    public void detectZeroTriangleTest(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.detectTriangle(), 0, String.format(MESSAGE, a, b, c));
    }

    @Test(dataProvider = "infinityTrianglesData")
    public void detectInfinityTriangleTest(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.detectTriangle(), 0, String.format(MESSAGE, a, b, c));
    }

    @Test(dataProvider = "incorrectTrianglesData")
    public void detectIncorrectTriangleTest(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.detectTriangle(), 0, String.format(MESSAGE, a, b, c));
    }

    @Test(expectedExceptions = Exception.class, dataProvider = "negativeTrianglesData")
    public void getSquareNegativeSideTriangleTest(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.getSquare(), 0.0, String.format(MESSAGE, a, b, c));
    }
    @Test(expectedExceptions = Exception.class, dataProvider = "zeroTrianglesData")
    public void getSquareZeroSideTriangleTest(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.getSquare(), 0.0, String.format(MESSAGE, a, b, c));
    }
    @Test(expectedExceptions = Exception.class, dataProvider = "infinityTrianglesData")
    public void getSquareInfinityTriangleTest(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.getSquare(), 0.0, String.format(MESSAGE, a, b, c));
    }

    @Test(expectedExceptions = Exception.class, dataProvider = "incorrectTrianglesData")
    public void getSquareIncorrectTriangleTest(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.getSquare(), 0.0, String.format(MESSAGE, a, b, c));
    }

    @Test(expectedExceptions = NullPointerException.class, dataProvider = "nullSideProvider")
    public void getSquareNullSideTest(Double sideA, Double sideB, Double sideC) {
        this.triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertEquals(triangle.getSquare(), 0.0, String.format(MESSAGE, sideA, sideB, sideC));
    }

    @AfterMethod
    public void afterMethod() {
        triangle = null;
    }
}
