import org.junit.Test;
import static org.junit.Assert.*;

public class ChatBasicGottRiderTest {
    private static final double EPS = 0.001;

    @Test
    public void averageOfManyRides() {
        GottRider rider = new GottRider("MultiRider", 5L, 4.0); // Total stars = 20

        rider.rideCompleted(5L); // 25/6
        rider.rideCompleted(3L); // 28/7
        rider.rideCompleted(4L); // 32/8

        assertEquals(4.0, rider.getAverageReviewGiven(), EPS);
    }

    @Test
    public void zeroRidesEdgeCase() {
        GottRider rider = new GottRider("NoRides", 0L, 0.0);

        assertEquals(0.0, rider.getAverageReviewGiven(), EPS);
        assertEquals("NoRides", rider.getName());

        rider.rideCompleted(3L);
        assertEquals(3.0, rider.getAverageReviewGiven(), EPS);
    }

    @Test
    public void longNameToStringTest() {
        GottRider rider = new GottRider("AlexandertheGreat", 10L, 4.2);

        String output = rider.toString();
        assertTrue(output.contains("AlexandertheGreat"));
        assertTrue(output.contains("4.2"));
        assertTrue(output.contains("10"));
    }

    @Test
    public void compareToIdenticalRatings() {
        GottRider a = new GottRider("A", 3L, 4.0);
        GottRider b = new GottRider("B", 3L, 4.0);

        assertEquals(0, a.compareTo(b));
        assertEquals(0, b.compareTo(a));
    }

    @Test
    public void compareToVariousScenarios() {
        GottRider a = new GottRider("Alice", 1L, 1.0);
        GottRider b = new GottRider("Bob", 1L, 5.0);

        assertTrue(a.compareTo(b) < 0);
        assertTrue(b.compareTo(a) > 0);

        a.rideCompleted(5L); // avg becomes (1 + 5) / 2 = 3.0
        assertTrue(a.compareTo(b) < 0);

        a.rideCompleted(5L); // avg = (1 + 5 + 5) / 3 = 3.67
        assertTrue(a.compareTo(b) < 0);

        a.rideCompleted(5L); // avg = (1 + 5 + 5 + 5) / 4 = 4.0
        assertTrue(a.compareTo(b) < 0);

        a.rideCompleted(5L); // avg = 4.2
        assertTrue(a.compareTo(b) < 0);

        a.rideCompleted(5L); // avg = 4.33
        assertTrue(a.compareTo(b) < 0);

        a.rideCompleted(5L); // avg = 4.43
        assertTrue(a.compareTo(b) < 0);

        a.rideCompleted(5L); // avg = 4.5
        assertTrue(a.compareTo(b) < 0);

        a.rideCompleted(5L); // avg = 4.56
        assertTrue(a.compareTo(b) < 0);

        a.rideCompleted(5L); // avg = 4.6
        assertTrue(a.compareTo(b) < 0);

        a.rideCompleted(5L); // avg = 4.64
        assertTrue(a.compareTo(b) < 0);

        a.rideCompleted(5L); // avg = 4.67
        assertTrue(a.compareTo(b) < 0);

        a.rideCompleted(5L); // avg = 4.69...
        assertTrue(a.compareTo(b) < 0);

        a.rideCompleted(5L); // avg = 4.71...
        assertTrue(a.compareTo(b) < 0);

        a.rideCompleted(5L); // avg = 4.73...
        assertTrue(a.compareTo(b) < 0);
    }

    @Test
    public void rideCompletedDoesNotThrow() {
        GottRider rider = new GottRider("Safe", 2L, 3.0);
        try {
            rider.rideCompleted(4L);
        } catch (Exception e) {
            fail("rideCompleted should not throw an exception.");
        }
    }
}
