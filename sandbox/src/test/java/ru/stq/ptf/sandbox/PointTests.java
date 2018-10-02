package ru.stq.ptf.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.sandbox.Point;

public class PointTests {

    @Test
    public void distancePoint()
    {
        Point p1 = new Point(1, 6);
        Point p2 = new Point(5, 7);
        Assert.assertEquals(p2.distance(p1), 4.123105625617661);
    }

    @Test
    public void distanceBetweenSamePoint()
    {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 1);
        Assert.assertEquals(p2.distance(p1), 0.0);
    }

    @Test
    public void distanceWhithNegativePoint()
    {
        Point p1 = new Point(-1, -1);
        Point p2 = new Point(-5, -3);
        Assert.assertEquals(p2.distance(p1), 4.47213595499958);
    }
}
