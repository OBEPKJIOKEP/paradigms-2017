/**
 * Created by Greg on 23.02.2017.
 */
public class ArrayQueueADT {
    private int begin = 0, end = 0, size = 0;
    private Object[] elements = new Object[5];

    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert element != null || queue != null;

        ensureCapacity(queue, queue.size + 1);
        queue.elements[queue.end] = element;
        queue.end = ++queue.end % queue.elements.length;
        queue.size++;
    }

    public static Object element(ArrayQueueADT queue) {
        assert queue != null || queue.size > 0;

        return queue.elements[queue.begin];
    }

    public static Object dequeue(ArrayQueueADT queue) {
        assert queue != null || queue.size > 0;

        Object result = queue.elements[queue.begin];
        queue.elements[queue.begin] = null;
        queue.begin = ++queue.begin % queue.elements.length;
        queue.size--;
        return result;
    }

    public static int size(ArrayQueueADT queue) {
        assert queue != null;

        return queue.size;
    }

    public static boolean isEmpty(ArrayQueueADT queue) {
        assert queue != null;

        return queue.size == 0;
    }
    public static void clear(ArrayQueueADT queue) {
        assert queue != null;

        queue.begin = 0;
        queue.end = 0;
        queue.size = 0;
        queue.elements = new Object[5];
    }

    public static Object[] toArray(ArrayQueueADT queue) {
        return expandArray(queue, queue.size);
    }

    private static Object[] expandArray(ArrayQueueADT queue, int newArraySize) {
        Object[] newElements = new Object[newArraySize];
        if(queue.size == 0) {
            return newElements;
        }
        if(queue.begin < queue.end) {
            System.arraycopy(queue.elements, queue.begin, newElements, 0, queue.size);
        } else {
            System.arraycopy(queue.elements, queue.begin, newElements, 0, 
                queue.elements.length - queue.begin);
            System.arraycopy(queue.elements, 0, newElements, 
                queue.elements.length - queue.begin, queue.end);
        }
        return newElements;
    }

    private static void ensureCapacity(ArrayQueueADT queue, int capacity) {
        if (capacity <= queue.elements.length) {
            return;
        }
        queue.elements = expandArray(queue, 2 * capacity);
        queue.end = queue.size;
        queue.begin = 0;
    }
}
