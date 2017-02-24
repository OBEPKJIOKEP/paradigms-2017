/**
 * Created by Greg on 23.02.2017.
 */
public class ArrayQueueModule {
    private static int begin = 0, end = 0, size = 0;
    private static Object[] elements = new Object[5];

    public static void enqueue(Object element) {
        assert element != null;

        ensureCapacity(size + 1);
        elements[end] = element;
        end = ++end % elements.length;
        size++;
    }

    public static Object element() {
        assert size > 0;

        return elements[begin];
    }

    public static Object dequeue() {
        assert size() > 0;

        Object result = elements[begin];
        elements[begin] = null;
        begin = ++begin % elements.length;
        size--;
        return result;
    }

    public static int size() {
        return size;
    }

    public static boolean isEmpty() {
        return size == 0;
    }
    public static void clear() {
        begin = 0;
        end = 0;
        size = 0;
        elements = new Object[5];
    }

    private static void ensureCapacity(int capacity) {
        if (capacity <= elements.length) {
            return;
        }
        Object[] newElements = new Object[2 * capacity];
        int i = begin, j = 0;
        while (i < (begin != end ? end : end + elements.length)) {
            newElements[j++] = elements[(i++) % elements.length];
        }
        end = size;
        begin = 0;
        elements = newElements;
    }
}
