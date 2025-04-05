import java.util.Arrays;
public class testMinMaxHeap {
    public static void main(String[] args) {
        testNoArgConstructor();
        testCapacityConstructor();
        testArrayConstructor();
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
        System.out.println("MinHeap (H1) array: " + Arrays.toString(heap.getH1()));
        System.out.println("MaxHeap (H2) array: " + Arrays.toString(heap.getH2()));

        // 5) Print the size
        System.out.println("Heap size:           " + heap.getSize());

        System.out.println("=== Test complete ===\n");

        System.out.println("Array constructor test complete.\n");
    }
}
