public class MinMaxHeap<T extends Comparable<T>> {

    private T[] H1; // Min-heap array (1-based)
    private T[] H2; // Max-heap array (1-based)
    private int[] P1to2; // Mapping: index in H1 -> index in H2
    private int[] P2to1; // Mapping: index in H2 -> index in H1
    private int size;  // number of elements currently in the heaps
    private static final int DEFAULT_CAPACITY = 100; //I chose arbitrarily, it seems like a reasonable number to me.

    @SuppressWarnings("unchecked")
    public MinMaxHeap() {
        // +1 for 1-based indexing
        H1 = (T[]) new Comparable[DEFAULT_CAPACITY + 1];
        H2 = (T[]) new Comparable[DEFAULT_CAPACITY + 1];
        P1to2 = new int[DEFAULT_CAPACITY + 1];
        P2to1 = new int[DEFAULT_CAPACITY + 1];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public MinMaxHeap(int initialCapacity) {
        H1 = (T[]) new Comparable[initialCapacity + 1];
        H2 = (T[]) new Comparable[initialCapacity + 1];
        P1to2 = new int[initialCapacity + 1];
        P2to1 = new int[initialCapacity + 1];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public MinMaxHeap(T[] initialData) {
        // Make sure I have enough space
        int capacity = Math.max(DEFAULT_CAPACITY + 1,initialData.length * 2 + 1);

        H1 = (T[]) new Comparable[capacity];
        H2 = (T[]) new Comparable[capacity];
        P1to2 = new int[capacity];
        P2to1 = new int[capacity];

        // Copy all elements into both heaps (1-based)
        size = initialData.length;
        for (int i = 0; i < initialData.length; i++) {
            H1[i + 1] = initialData[i];
            H2[i + 1] = initialData[i];
            // Initially, map them 1–1
            P1to2[i + 1] = i + 1;
            P2to1[i + 1] = i + 1;
        }

        // Now build the min-heap in H1 and the max-heap in H2 (O(n) total).
        // During each swap in buildHeap, update the mapping arrays.
        buildMinHeap();
        buildMaxHeap();
    }

    private void buildMinHeap() {
        // Standard bottom-up build-heap from i = size/2 down to 1
        for (int i = size / 2; i >= 1; i--) {
            purcDownMin(i);
        }
    }

    private void buildMaxHeap() {
        // Standard bottom-up build-heap from i = size/2 down to 1
        for (int i = size / 2; i >= 1; i--) {
            purcDownMax(i);
        }
    }

    private void purcDownMin(int i) {
        // typical bubble-down logic (1-based)
        while (true) {
            int left = 2 * i;
            int right = left + 1;
            int smallest = i;

            if (left <= size && H1[left].compareTo(H1[smallest]) < 0) {
                smallest = left;
            }
            if (right <= size && H1[right].compareTo(H1[smallest]) < 0) {
                smallest = right;
            }
            if (smallest != i) {
                swapInH1(i, smallest);
                i = smallest;
            } else {
                break;
            }
        }
    }

    private void purcDownMax(int i) {
        while (true) {
            int left = 2 * i;
            int right = left + 1;
            int largest = i;

            if (left <= size && H2[left].compareTo(H2[largest]) > 0) {
                largest = left;
            }
            if (right <= size && H2[right].compareTo(H2[largest]) > 0) {
                largest = right;
            }
            if (largest != i) {
                swapInH2(i, largest);
                i = largest;
            } else {
                break;
            }
        }
    }

    private void swapInH1(int i, int j) {
        // swap the elements in H1
        T temp = H1[i];
        H1[i] = H1[j];
        H1[j] = temp;

        // also swap the *mapping* to the H2 indices
        int tempMap = P1to2[i];
        P1to2[i] = P1to2[j];
        P1to2[j] = tempMap;

        // and tell H2 that these two elements changed places in H1
        // specifically, if H1[i] is now the old H1[j], we need to update P2to1
        // but we must also swap in H2 the references if needed.
        // Usually you only need to fix P2to1 for the two relevant entries:
        P2to1[P1to2[i]] = i;
        P2to1[P1to2[j]] = j;
    }

    private void swapInH2(int i, int j) {
        // swap the elements in H2
        T temp = H2[i];
        H2[i] = H2[j];
        H2[j] = temp;

        // swap the mapping to the H1 indices
        int tempMap = P2to1[i];
        P2to1[i] = P2to1[j];
        P2to1[j] = tempMap;

        // fix the mapping arrays in H1
        P1to2[P2to1[i]] = i;
        P1to2[P2to1[j]] = j;
    }

    public int getSize() {return this.size;}
    public T[] getH1() { return H1; }
    public T[] getH2() { return H2; }

}