/**
 * Created by Greg on 23.02.2017.
 */
public class ArrayQueueADT {
    private int begin = 0, end = 0, size = 0;
    private Object[] elements = new Object[5];

    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert element != null;

        ensureCapacity(queue, queue.size + 1);
        queue.elements[queue.end] = element;
        queue.end = ++queue.end % queue.elements.length;
        queue.size++;
    }

    public static Object element(ArrayQueueADT queue) {
        assert queue.size > 0;

        return queue.elements[queue.begin];
    }

    public static Object dequeue(ArrayQueueADT queue) {
        assert queue.size > 0;

        Object result = queue.elements[queue.begin];
        queue.elements[queue.begin] = null;
        queue.begin = ++queue.begin % queue.elements.length;
        queue.size--;
        return result;
    }

    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }

    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    }
    public static void clear(ArrayQueueADT queue) {
        queue.begin = 0;
        queue.end = 0;
        queue.size = 0;
        queue.elements = new Object[5];
    }

    private static void ensureCapacity(ArrayQueueADT queue, int capacity) {
        if (capacity <= queue.elements.length) {
            return;
        }
        Object[] newElements = new Object[2 * capacity];
        int i = queue.begin, j = 0;
        while (i < (queue.begin != queue.end ? queue.end : 
                queue.end + queue.elements.length)) {
            newElements[j++] = queue.elements[(i++) % queue.elements.length];
        }
        queue.end = queue.size;
        queue.begin = 0;
        queue.elements = newElements;
    }
}
