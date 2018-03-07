package triangle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DetectTriangleTest {

    private Triangle rectangularTriangle, ordinaryTriangle, equilateralTriangle, isoscelesTriangle;
    private double a = 3.0, b = 4.0, c = 5.0;  // rectangular triangle
    private double a1 = 3.0; // equilateral triangle
    private double a2 = 4.0, b2 = 8.0, c2 = 5.0; // ordinary triangle
    private double a3 = 4.0, b3 = 4.0, c3 = 5.0; // isosceles triangle


    @Before
    public void setUp() throws Exception {
        rectangularTriangle = new Triangle(a, b, c);
        equilateralTriangle = new Triangle(a1, a1, a1);
        ordinaryTriangle = new Triangle(a2, b2, c2);
        isoscelesTriangle = new Triangle(a3, b3, c3);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void detectRectangularTriangle() throws Exception {
        assertEquals(rectangularTriangle.TR_RECTANGULAR, rectangularTriangle.detectTriangle());
    }

    @Test
    public void detectOrdinaryTriangle() throws Exception {
        assertEquals(ordinaryTriangle.TR_ORDYNARY, ordinaryTriangle.detectTriangle());
    }

    @Test
    public void detectIsoscelesTriangle() throws Exception {
        assertEquals(isoscelesTriangle.TR_ISOSCELES, isoscelesTriangle.detectTriangle());
    }

    @Test
    public void detectEquatorialTriangle() throws Exception {
        assertEquals(equilateralTriangle.TR_EQUILATERAL, equilateralTriangle.detectTriangle());
    }
}
