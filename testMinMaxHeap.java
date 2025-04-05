import java.util.Arrays;
public class testMinMaxHeap {
    public static void main(String[] args) {
        testDeleteMax();
    }

    private static void testNoArgConstructor() {
        System.out.println("=== Test No-Arg Constructor ===");
        MinMaxHeap<Integer> heap = new MinMaxHeap<>();

        // Just to confirm that size = 0, arrays allocated
        System.out.println("Size after creation (should be 0): " + heap.getSize());
        // If you have a method to print or check internal arrays, call it here.
        // e.g.: heap.printHeaps() or something similar.

        System.out.println("No-arg constructor test complete.\n");
    }

    private static void testCapacityConstructor() {
        System.out.println("=== Test Capacity Constructor ===");
        MinMaxHeap<Integer> heap = new MinMaxHeap<>(10);

        // Confirm initial size/capacity
        System.out.println("Size after creation (should be 0): " + heap.getSize());

        // Optionally, insert some data and see if everything remains consistent
        // e.g., heap.insert(5); heap.insert(2); heap.insert(9); ...
        // Then check min or max or see if your internal arrays look correct.

        System.out.println("Capacity constructor test complete.\n");
    }

    private static void testArrayConstructor() {
        System.out.println("=== Test Constructor (T[] initialData) ===");

        // 1) Create an array of integers to pass in
        Integer[] initialData = {5, 3, 8, 1, 2, 9, 4};

        // 2) Print the original array
        System.out.println("Original array:      " + Arrays.toString(initialData));

        // 3) Build a MinMaxHeap from the array
        MinMaxHeap<Integer> heap = new MinMaxHeap<>(initialData);

        // 4) Print the resulting H1 (min-heap) and H2 (max-heap)
        //    We assume you have public T[] getH1(), T[] getH2() methods.
        //    Because of 1-based indexing, we’ll skip index 0 when printing.
        System.out.println("MinHeap (H1) array: " + Arrays.toString(heap.getH1AsComparable()));
        System.out.println("MaxHeap (H2) array: " + Arrays.toString(heap.getH2AsComparable()));

        // 5) Print the size
        System.out.println("Heap size:           " + heap.getSize());

        System.out.println("=== Test complete ===\n");

        System.out.println("Array constructor test complete.\n");
    }

    public static void testInsert() {
        // 1) Create an initial array of Integers
        Integer[] initialData = {5, 3, 8, 1, 2};

        // 2) Construct a MinMaxHeap from that array
        MinMaxHeap<Integer> heap = new MinMaxHeap<>(initialData);

        // 3) Print initial state
        System.out.println("=== After constructor ===");
        System.out.println("Heap size: " + heap.getSize());
        printH1(heap);
        printH2(heap);

        // 4) Insert exactly one new integer
        int newValue = 9;
        System.out.println("\nInserting " + newValue + "...");
        heap.insert(newValue);

        // 5) Print the final state
        System.out.println("=== After inserting " + newValue + " ===");
        System.out.println("Heap size: " + heap.getSize());
        printH1(heap);
        printH2(heap);
    }

    public static void testDeleteMin() {
        // 1) Create an initial array of Integers
        Integer[] initialData = {5, 3, 8, 1, 2};

        // 2) Construct a MinMaxHeap from that array
        MinMaxHeap<Integer> heap = new MinMaxHeap<>(initialData);

        // 3) Print initial state
        System.out.println("=== Before deleteMin ===");
        System.out.println("Heap size: " + heap.getSize());
        printH1(heap);
        printH2(heap);

        // 4) Call deleteMin
        heap.deleteMin();
        System.out.println("\nCalled deleteMin(), removed: " );

        // 5) Print new state
        System.out.println("=== After deleteMin ===");
        System.out.println("Heap size: " + heap.getSize());
        printH1(heap);
        printH2(heap);
    }
    public static void testDeleteMax() {
        // 1) Create an initial array of Integers
        Integer[] initialData = {5, 3, 8, 1, 2};

        // 2) Construct a MinMaxHeap from that array
        MinMaxHeap<Integer> heap = new MinMaxHeap<>(initialData);

        // 3) Print initial state
        System.out.println("=== Before deleteMin ===");
        System.out.println("Heap size: " + heap.getSize());
        printH1(heap);
        printH2(heap);

        // 4) Call deleteMin
        heap.deleteMax();
        System.out.println("\nCalled deleteMin(), removed: " );

        // 5) Print new state
        System.out.println("=== After deleteMin ===");
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
}
