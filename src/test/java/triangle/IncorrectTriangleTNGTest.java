package triangle;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class IncorrectTriangleTNGTest {
    private Triangle triangle;

    @DataProvider(name = "incorrectTrianglesData")
    public Object[][] incorrectTrianglesDataProvider() {
        return new Object[][]{
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
                {new Double(4), new Double(8), new Double(20)},                           //a+b<c
                {new Double(4), new Double(10), new Double(5)},                           //a+c<b
                {new Double(15), new Double(8), new Double(5)},                           //b+c<a
                {1.0, 1.0, 2.0},                                                                             //a+b=c
                {0.0001, 0.0002, 0.0001},                                                                             //a+c=b
                {2000.0, 1000.0, 1000.0},                                                                             //b+c=a
                {Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE,},
                {Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE,},
                {new Double(Double.MAX_VALUE), new Double(Double.MAX_VALUE), new Double(5)},          //a+b=+infinity
                {new Double(Double.MIN_VALUE), new Double(Double.MIN_VALUE), new Double(5)},          //a+b=-infinity
                {new Double(Double.MAX_VALUE), new Double(8), new Double(Double.MAX_VALUE)},          //a+c=+infinity
                {new Double(Double.MIN_VALUE), new Double(8), new Double(Double.MIN_VALUE)},          //a+c=-infinity
                {new Double(4), new Double(Double.MAX_VALUE), new Double(Double.MAX_VALUE)},          //b+c=+infinity
                {new Double(4), new Double(Double.MIN_VALUE), new Double(Double.MIN_VALUE)},          //b+c=-infinity
                {new Double(-4), new Double(2), new Double(2)},                          //a+b+c=0
                {new Double(-4), new Double(-8), new Double(-5)},                        //(p*(p-a)*(p-b)*(p-c))<0, где p=(a+b+c)/2.
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

    @Test(dataProvider = "incorrectTrianglesData")
    public void checkTriangleTest(Double sideA, Double sideB, Double sideC) {
        this.triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertEquals(triangle.checkTriangle(), false);
    }

    @Test(expectedExceptions = NullPointerException.class, dataProvider = "nullSideProvider")
    public void checkTriangleNullSideTest(Double sideA, Double sideB, Double sideC) {
        this.triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertEquals(triangle.checkTriangle(), false);
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
    // We can't get message "c<=0" because we will always get messages "a+c<=b" or "a<=0" or "b<=0"
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

    @Test(dataProvider = "incorrectTrianglesData")
    public void detectIncorrectTriangleTest(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.detectTriangle(), 0);
    }

    @Test(expectedExceptions = Exception.class, dataProvider = "incorrectTrianglesData")
    public void getSquareIncorrectTriangleTest(Double a, Double b, Double c) {
        triangle = new Triangle(a, b, c);
        Assert.assertEquals(triangle.getSquare(), 0.0);
    }

    @Test(expectedExceptions = NullPointerException.class, dataProvider = "nullSideProvider")
    public void getSquareNullSideTest(Double sideA, Double sideB, Double sideC) {
        this.triangle = new Triangle(sideA, sideB, sideC);
        Assert.assertEquals(triangle.getSquare(), 0.0);
    }

    @AfterMethod
    public void afterMethod() {
        triangle = null;
    }
}
