import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestGottRiders {
    public static void main(String[] args) {
        // Create some GottRider instances
        GottRider riderA = new GottRider("Alice", 10L, 4.5);
        GottRider riderB = new GottRider("Bob", 5L, 3.0);
        GottRider riderC = new GottRider("Charlie", 0L, 0.0);

        // Print their initial state
        System.out.println("Initial riders:");
        System.out.println(riderA);
        System.out.println(riderB);
        System.out.println(riderC);
        System.out.println();

        // Simulate ride completions for each
        riderA.rideCompleted(4L); // Suppose Alice gave 4 stars in her latest ride
        riderB.rideCompleted(5L); // Bob gave 5 stars
        riderC.rideCompleted(2L); // Charlie gave 2 stars

        // Print their state after ride completion
        System.out.println("After one new ride each:");
        System.out.println(riderA);
        System.out.println(riderB);
        System.out.println(riderC);
        System.out.println();

        // Compare two riders explicitly
        System.out.println("Compare riderA to riderB:");
        int comparisonAB = riderA.compareTo(riderB);
        if (comparisonAB < 0) {
            System.out.println(" -> Alice is less than Bob (Alice has lower average review).");
        } else if (comparisonAB == 0) {
            System.out.println(" -> Alice is equal to Bob (same average review).");
        } else {
            System.out.println(" -> Alice is greater than Bob (Alice has higher average review).");
        }
        System.out.println();

        // Sort a list of riders by their average reviews
        List<GottRider> riders = new ArrayList<>();
        riders.add(riderA);
        riders.add(riderB);
        riders.add(riderC);

        System.out.println("Riders before sorting:");
        for (GottRider r : riders) {
            System.out.println(r);
        }
        System.out.println();

        Collections.sort(riders);

        System.out.println("Riders after sorting by average review (ascending order):");
        for (GottRider r : riders) {
            System.out.println(r);
        }
    }
}
