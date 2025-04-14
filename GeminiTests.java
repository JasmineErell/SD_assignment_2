import org.junit.Test;
import static org.junit.Assert.*;

public class GeminiTests {

    @Test(expected = Exception.class)
    public void testDeleteMinEmptyHeap() {
        MinMaxHeap<Integer> heap = new MinMaxHeap<>();
        heap.deleteMin();
    }

    @Test(expected = Exception.class)
    public void testDeleteMaxEmptyHeap() {
        MinMaxHeap<Integer> heap = new MinMaxHeap<>();
        heap.deleteMax();
    }

    @Test
    public void testSingleElementHeap() {
        MinMaxHeap<Integer> heap = new MinMaxHeap<>();
        heap.insert(10);
        assertEquals(Integer.valueOf(10), heap.findMin());
        assertEquals(Integer.valueOf(10), heap.findMax());
        assertEquals(Integer.valueOf(10), heap.deleteMin());
        assertEquals(0, heap.getSize());
    }

    @Test
    public void testResizeHeap() {
        MinMaxHeap<Integer> heap = new MinMaxHeap<>(2);
        heap.insert(5);
        heap.insert(10);
        heap.insert(15); // Should trigger resize
        assertEquals(3, heap.getSize());
        assertEquals(Integer.valueOf(5), heap.findMin());
        assertEquals(Integer.valueOf(15), heap.findMax());
    }

    @Test
    public void testDuplicateElements() {
        MinMaxHeap<Integer> heap = new MinMaxHeap<>();
        heap.insert(10);
        heap.insert(10);
        heap.insert(10);
        assertEquals(3, heap.getSize());
        assertEquals(Integer.valueOf(10), heap.deleteMin());
        assertEquals(Integer.valueOf(10), heap.findMin());
        assertEquals(2, heap.getSize());
    }

    @Test
    public void testAlternatingMinMaxDeletion() {
        MinMaxHeap<Integer> heap = new MinMaxHeap<>();
        heap.insert(1);
        heap.insert(100);
        heap.insert(50);
        heap.insert(2);

        assertEquals(Integer.valueOf(1), heap.deleteMin());
        assertEquals(Integer.valueOf(100), heap.deleteMax());
        assertEquals(Integer.valueOf(2), heap.deleteMin());
        assertEquals(Integer.valueOf(50), heap.deleteMax());

        assertEquals(0, heap.getSize());
    }

    @Test
    public void testHeapWithInitialData() {
        Integer[] initialData = {20, 5, 15, 10, 30};
        MinMaxHeap<Integer> heap = new MinMaxHeap<>(initialData);
        assertEquals(5, heap.getSize());
        assertEquals(Integer.valueOf(5), heap.findMin());
        assertEquals(Integer.valueOf(30), heap.findMax());
    }

    @Test
    public void testInsertionOrderSensitivity() {
        MinMaxHeap<Integer> heap = new MinMaxHeap<>();
        heap.insert(30);
        heap.insert(20);
        heap.insert(10);
        assertEquals(Integer.valueOf(10), heap.findMin());
        assertEquals(Integer.valueOf(30), heap.findMax());

        heap.insert(40);
        assertEquals(Integer.valueOf(40), heap.findMax());

        heap.insert(5);
        assertEquals(Integer.valueOf(5), heap.findMin());
    }

    @Test
    public void testWithGottRiderObjects() {
        GottRider rider1 = new GottRider("Alice", 10L, 4.9);
        GottRider rider2 = new GottRider("Bob", 5L, 3.2);
        GottRider rider3 = new GottRider("Charlie", 7L, 5.0);

        MinMaxHeap<GottRider> heap = new MinMaxHeap<>();
        heap.insert(rider1);
        heap.insert(rider2);
        heap.insert(rider3);

        assertEquals("Bob", heap.findMin().getName());
        assertEquals("Charlie", heap.findMax().getName());
    }
}