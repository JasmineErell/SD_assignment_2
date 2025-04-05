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

    public void insert(T element) {
        //Check capacity (if needed)
        if (size + 1 >= H1.length) {
            expandCapacity();
        }
        //Place the new element at index `size` in both heaps
        size++;
        H1[size] = element;
        H2[size] = element;

        // Initialize the mapping (H1[size] <--> H2[size])
        P1to2[size] = size;
        P2to1[size] = size;

        purcUpMin(size);
        purcUpMax(size);
    }

    public void deleteMin() {
        if (size == 0)
        {
            System.out.println("Heap is empty, nothing to delete");
        }

        T minValue = H1[1];
        int h2Index = P1to2[1];

        // Move last element to root in both heaps
        swapInH1(1, size);
        swapInH2(h2Index, size);

        size--;

        //H1[size+1] and H2[size+1] are out of the heap
        H1[size + 1] = null;
        H2[size + 1] = null;
        // If desired, clear the old mappings for that slot:
        P1to2[size + 1] = 0;
        P2to1[size + 1] = 0;

        // Purc down to fix order
        if (size >= 1) {
            purcDownMin(1);
            purcDownMax(h2Index);
        }
    }

    public void deleteMax() {
        if (size == 0)
        {
            System.out.println("Heap is empty, nothing to delete");
        }

        T maxValue = H2[1];
        int h1Index = P2to1[1];

        // Move last element to root in both heaps
        swapInH2(1, size);
        swapInH1(h1Index, size);

        size--;

        //H1[size+1] and H2[size+1] are out of the heap
        H1[size + 1] = null;
        H2[size + 1] = null;
        // If desired, clear the old mappings for that slot:
        P1to2[size + 1] = 0;
        P2to1[size + 1] = 0;

        // Purc down to fix order
        if (size >= 1) {
            purcDownMin(h1Index);
            purcDownMax(1);
        }
    }

    //ALL OF THE HELP FUNCTIONS NEDDED FOR IMPLEMENTATION:
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

    private void purcUpMin(int i) {
        while (i > 1) {
            int parent = i / 2;
            System.out.println(H1[i]);
            if (H1[i].compareTo(H1[parent]) < 0) {
                swapInH1(i, parent);
                i = parent;
            } else {
                break;
            }
        }
    }

    private void purcUpMax(int i) {
        while (i > 1) {
            int parent = i / 2;
            if (H2[i].compareTo(H2[parent]) > 0) {
                swapInH2(i, parent);
                i = parent;
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

    @SuppressWarnings("unchecked")
    private void expandCapacity() {
        int oldLength = H1.length;       // current array size
        int newLength = oldLength * 2;   // double it, or choose another strategy

        // Create new arrays for both heaps and the mapping arrays
        T[] newH1 = (T[]) new Comparable[newLength];
        T[] newH2 = (T[]) new Comparable[newLength];
        int[] newP1to2 = new int[newLength];
        int[] newP2to1 = new int[newLength];

        // Copy existing elements from index 1..size
        for (int i = 1; i <= size; i++) {
            newH1[i] = H1[i];
            newH2[i] = H2[i];
            newP1to2[i] = P1to2[i];
            newP2to1[i] = P2to1[i];
        }

        // Update references
        H1 = newH1;
        H2 = newH2;
        P1to2 = newP1to2;
        P2to1 = newP2to1;
    }

    public int getSize() {return this.size;}
    public Comparable<?>[] getH1AsComparable() {return H1;}
    public Comparable<?>[] getH2AsComparable() {return H2;}


}