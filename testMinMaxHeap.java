import java.util.Arrays;
import java.util.Random;

public class testMinMaxHeap {
    public static void main(String[] args) {
        // Existing calls
        testNoArgConstructor();
        testCapacityConstructor();
        testArrayConstructor();
        testInsert();
        testDeleteMin();
        testDeleteMax();

        // New “extreme” or edge-case tests:
        testEmptyHeapBehaviors();       // Empty usage, negative capacity, etc.
        testLargeInsertsAndDeletes();   // Stress test
        testDuplicateValues();          // Many duplicates
        testInterleavedOps();           // Random inserts/deletes, checks
    }

    // ====== Existing Tests (unchanged except for the delete references) ======

    private static void testNoArgConstructor() {
        System.out.println("=== Test No-Arg Constructor ===");
        MinMaxHeap<Integer> heap = new MinMaxHeap<>();
        System.out.println("Size after creation (should be 0): " + heap.getSize());
        System.out.println("No-arg constructor test complete.\n");
    }

    private static void testCapacityConstructor() {
        System.out.println("=== Test Capacity Constructor ===");
        MinMaxHeap<Integer> heap = new MinMaxHeap<>(10);
        System.out.println("Size after creation (should be 0): " + heap.getSize());
        System.out.println("Capacity constructor test complete.\n");
    }

    private static void testArrayConstructor() {
        System.out.println("=== Test Constructor (T[] initialData) ===");
        Integer[] initialData = {5, 3, 8, 1, 2, 9, 4};

        System.out.println("Original array:      " + Arrays.toString(initialData));
        MinMaxHeap<Integer> heap = new MinMaxHeap<>(initialData);

        System.out.println("MinHeap (H1) array: " + Arrays.toString(heap.getH1AsComparable()));
        System.out.println("MaxHeap (H2) array: " + Arrays.toString(heap.getH2AsComparable()));
        System.out.println("Heap size:           " + heap.getSize());

        System.out.println("Array constructor test complete.\n");
    }

    public static void testInsert() {
        Integer[] initialData = {5, 3, 8, 1, 2};
        MinMaxHeap<Integer> heap = new MinMaxHeap<>(initialData);

        System.out.println("=== After constructor ===");
        System.out.println("Heap size: " + heap.getSize());
        printH1(heap);
        printH2(heap);

        int newValue = 9;
        System.out.println("\nInserting " + newValue + "...");
        heap.insert(newValue);

        System.out.println("=== After inserting " + newValue + " ===");
        System.out.println("Heap size: " + heap.getSize());
        printH1(heap);
        printH2(heap);
    }

    public static void testDeleteMin() {
        Integer[] initialData = {};
        MinMaxHeap<Integer> heap = new MinMaxHeap<>(initialData);

        System.out.println("=== Before deleteMin ===");
        System.out.println("Heap size: " + heap.getSize());
        printH1(heap);
        printH2(heap);

        System.out.println("\nCalling deleteMin() on empty (void)...");
        heap.deleteMin();

        System.out.println("=== After deleteMin ===");
        System.out.println("Heap size: " + heap.getSize());
        printH1(heap);
        printH2(heap);
    }

    public static void testDeleteMax() {
        Integer[] initialData = {};
        MinMaxHeap<Integer> heap = new MinMaxHeap<>(initialData);

        System.out.println("=== Before deleteMax ===");
        System.out.println("Heap size: " + heap.getSize());
        printH1(heap);
        printH2(heap);

        System.out.println("\nCalling deleteMax() on empty (void)...");
        heap.deleteMax();

        System.out.println("=== After deleteMax ===");
        System.out.println("Heap size: " + heap.getSize());
        printH1(heap);
        printH2(heap);
    }

    private static void printH1(MinMaxHeap<Integer> heap) {
        Comparable<?>[] h1 = heap.getH1AsComparable();
        int size = heap.getSize();
        if (size >= 1) {
            Object[] usedPortion = Arrays.copyOfRange(h1, 1, size + 1);
            System.out.println("H1 (Min-heap) contents: " + Arrays.toString(usedPortion));
        } else {
            System.out.println("H1 is empty.");
        }
    }

    private static void printH2(MinMaxHeap<Integer> heap) {
        Comparable<?>[] h2 = heap.getH2AsComparable();
        int size = heap.getSize();
        if (size >= 1) {
            Object[] usedPortion = Arrays.copyOfRange(h2, 1, size + 1);
            System.out.println("H2 (Max-heap) contents: " + Arrays.toString(usedPortion));
        } else {
            System.out.println("H2 is empty.");
        }
    }

    // ====== “Extreme” / Additional Tests ======

    /**
     * Test behavior of empty heaps, invalid capacities, etc.
     */
    private static void testEmptyHeapBehaviors() {
        System.out.println("\n=== Test: Empty Heap Behaviors ===");

        // 1) No-arg constructor, immediately call findMin, findMax, deleteMin, deleteMax
        MinMaxHeap<Integer> emptyHeap = new MinMaxHeap<>();
        System.out.println("Created empty heap; size = " + emptyHeap.getSize());
        System.out.println("Calling findMin() on empty (expect null or exception): ");
        try {
            System.out.println("findMin() returned: " + emptyHeap.findMin());
        } catch (Exception e) {
            System.out.println("Caught exception: " + e);
        }

        System.out.println("Calling deleteMin() on empty (void; expect no change or exception): ");
        try {
            emptyHeap.deleteMin();
            System.out.println("Size after deleteMin(): " + emptyHeap.getSize());
        } catch (Exception e) {
            System.out.println("Caught exception: " + e);
        }

        System.out.println("Calling deleteMax() on empty (void; expect no change or exception): ");
        try {
            emptyHeap.deleteMax();
            System.out.println("Size after deleteMax(): " + emptyHeap.getSize());
        } catch (Exception e) {
            System.out.println("Caught exception: " + e);
        }

        // 2) Negative capacity constructor (if your implementation doesn’t forbid)
        try {
            MinMaxHeap<Integer> negCapacityHeap = new MinMaxHeap<>(-1);
            System.out.println("Created heap with negative capacity; size = " + negCapacityHeap.getSize());
        } catch (Exception e) {
            System.out.println("Caught exception for negative capacity: " + e);
        }

        System.out.println("Empty heap behaviors test complete.\n");
    }

    /**
     * Test inserting large numbers of elements, then removing them all (min, then max).
     */
    private static void testLargeInsertsAndDeletes() {
        System.out.println("=== Test: Large Inserts and Deletes ===");
        MinMaxHeap<Integer> heap = new MinMaxHeap<>(1);

        // Insert a range of values
        int numElements = 50;  // Increase for a heavier stress test
        for (int i = 0; i < numElements; i++) {
            heap.insert(i);
        }
        System.out.println("Inserted " + numElements + " elements [0..49].");
        System.out.println("Size is now: " + heap.getSize());
        System.out.println("Min should be 0:  " + heap.findMin());
        System.out.println("Max should be 49: " + heap.findMax());

        // Remove all via deleteMin
        System.out.println("\nRemoving elements via deleteMin...");
        while (heap.getSize() > 0) {
            // Optionally, check the top min before removing:
            // Integer currentMin = heap.findMin();
            heap.deleteMin();
        }

        System.out.println("Heap size after removing all with deleteMin: " + heap.getSize());

        // Insert again, then remove with deleteMax
        for (int i = 0; i < numElements; i++) {
            heap.insert(i);
        }
        System.out.println("\nInserted " + numElements + " elements again [0..49].");
        System.out.println("Now removing elements via deleteMax...");
        while (heap.getSize() > 0) {
            // Optionally, check the top max before removing:
            // Integer currentMax = heap.findMax();
            heap.deleteMax();
        }
        System.out.println("Heap size after removing all with deleteMax: " + heap.getSize());

        System.out.println("Large inserts/deletes test complete.\n");
    }

    /**
     * Test heaps with many duplicate values.
     */
    private static void testDuplicateValues() {
        System.out.println("=== Test: Duplicate Values ===");
        Integer[] duplicates = {5, 5, 5, 5, 5};
        MinMaxHeap<Integer> heap = new MinMaxHeap<>(duplicates);

        System.out.println("Inserted: " + Arrays.toString(duplicates));
        System.out.println("Size is now: " + heap.getSize());
        System.out.println("Min: " + heap.findMin() + ", Max: " + heap.findMax());

        System.out.println("Removing all via deleteMin...");
        while (heap.getSize() > 0) {
            heap.deleteMin();
        }
        System.out.println("Size after removing all: " + heap.getSize());

        // Insert duplicates again
        for (int i = 0; i < duplicates.length; i++) {
            heap.insert(5);
        }
        System.out.println("\nRe-inserted 5 duplicates, removing via deleteMax...");
        while (heap.getSize() > 0) {
            heap.deleteMax();
        }
        System.out.println("Size after removing all: " + heap.getSize());

        System.out.println("Duplicate values test complete.\n");
    }

    /**
     * Interleaved inserts/deletes with random values.
     */
    private static void testInterleavedOps() {
        System.out.println("=== Test: Interleaved Operations (Random) ===");
        MinMaxHeap<Integer> heap = new MinMaxHeap<>();
        Random rand = new Random();

        // Insert some random values
        for (int i = 0; i < 20; i++) {
            int val = rand.nextInt(100);
            heap.insert(val);
            System.out.println("Inserted " + val + " (size now: " + heap.getSize() + ")");
        }

        // Interleave deletions
        for (int i = 0; i < 10; i++) {
            // 50% chance deleteMin, 50% chance deleteMax
            if (rand.nextBoolean()) {
                if (heap.getSize() > 0) {
                    System.out.println("Calling deleteMin() (void) on heap...");
                    heap.deleteMin();
                }
            } else {
                if (heap.getSize() > 0) {
                    System.out.println("Calling deleteMax() (void) on heap...");
                    heap.deleteMax();
                }
            }
            System.out.println("Size now: " + heap.getSize() +
                    ", min: " + heap.findMin() +
                    ", max: " + heap.findMax());
        }

        System.out.println("Interleaved ops test complete.\n");
    }
}
