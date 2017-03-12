/**
 * Created by Greg on 23.02.2017.
 */

// inv: queue.size > 0, queue.a[0], ... ,queue.a[size - 1] ≠ null) ∨ 
// ∨ (queue.size = 0, a = ∅) 

// immutable = (queue.size = queue.size' ∧
// ∧ ∀i ∈ [0, queue.size) queue.a[i] = queue.a[i]')
public class ArrayQueueADT {
    private int begin = 0, size = 0;
    private Object[] elements = new Object[5];

    // pre: element ≠ null ∧ queue ≠ null
    // post: queue.size = queue.size' + 1 ∧ queue.a[queue.size'] = queue.element ∧
    // ∧ ∀i ∈ [0, queue.size') queue.a[i] = queue.a[i]'
    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert element != null : "element = null!";
        assert queue != null : "queue = null!";

        ensureCapacity(queue, queue.size + 1);
        queue.elements[(queue.begin + queue.size) % queue.elements.length] = element;
        queue.size++;
    }

    // pre: size > 0 ∧ queue ≠ null
    // post: R = a[0] ∧ immutable
    public static Object element(ArrayQueueADT queue) {
        assert queue != null : "queue = null!";
        assert queue.size > 0 : "size = 0!";

        return queue.elements[queue.begin];
    }

    // pre: queue.size > 0 ∧ queue ≠ null
    // post: R = queue.a[0] ∧ queue.size = queue.size' - 1 ∧ 
    // ∧  ∀i ∈ [1, queue.size) queue.a[i - 1] = queue.a[i]'
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue != null : "queue = null!";
        assert queue.size > 0 : "size = 0!";

        Object result = queue.elements[queue.begin];
        queue.elements[queue.begin] = null;
        queue.begin = ++queue.begin % queue.elements.length;
        queue.size--;
        return result;
    }

    // pre: queue ≠ null
    // post: R = queue.size ∧ immutable
    public static int size(ArrayQueueADT queue) {
        assert queue != null : "queue = null!";

        return queue.size;
    }

    // pre: queue ≠ null
    // post: R = (queue.size == 0) ∧ immutable
    public static boolean isEmpty(ArrayQueueADT queue) {
        assert queue != null : "queue = null!";

        return queue.size == 0;
    }

    // pre: queue ≠ null
    // post: queue.size = 0  
    public static void clear(ArrayQueueADT queue) {
        assert queue != null : "queue = null!";

        queue.begin = 0;
        queue.size = 0;
        queue.elements = new Object[5];
    }

    // pre: queue ≠ null
    // post: R = "[queue.a[0], ..., queue.a[queue.size - 1]]") ∨ immutable
    public static String toStr(ArrayQueueADT queue) {
        assert queue != null : "queue = null!";

        if (queue.size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        int i = queue.begin;
        while (i <  queue.begin + queue.size) {
            sb.append(", ").append(queue.elements[i++ % queue.elements.length]);
        }
        return "[" + sb.substring(2) + "]";
    }
    
    private static void ensureCapacity(ArrayQueueADT queue, int capacity) {
        if (capacity <= queue.elements.length) {
            return;
        }
        Object[] newElements = new Object[2 * capacity];
        if (queue.begin + queue.size < queue.elements.length) {
            System.arraycopy(queue.elements, queue.begin, newElements, 0, queue.size);
        } else {
            System.arraycopy(queue.elements, queue.begin, newElements, 0,
                             queue.elements.length - queue.begin);
            System.arraycopy(queue.elements, 0, newElements, queue.elements.length - queue.begin,
                             (queue.begin + queue.size) % queue.elements.length);
        }
        queue.elements = newElements;
        queue.begin = 0;
    }
}
