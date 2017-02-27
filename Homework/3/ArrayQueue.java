/**
 * Created by Greg on 23.02.2017.
 */
public class ArrayQueue {
    private int begin = 0, end = 0, size = 0;
    private Object[] elements = new Object[5];

    public void enqueue(Object element) {
        assert element != null;

        ensureCapacity(size + 1);
        elements[end] = element;
        end = ++end % elements.length;
        size++;
    }

    public Object element() {
        assert size > 0;

        return elements[begin];
    }

    public Object dequeue() {
        assert size > 0;

        Object result = elements[begin];
        elements[begin] = null;
        begin = ++begin % elements.length;
        size--;
        return result;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    public void clear() {
        begin = 0;
        end = 0;
        size = 0;
        elements = new Object[5];
    }

    public Object[] toArray() {
        return expandArray(size);
    }

    private Object[] expandArray(int newArraySize) {
        Object[] newElements = new Object[newArraySize];
        if(size == 0) {
            return newElements;
        }
        if(begin < end) {
            System.arraycopy(elements, begin, newElements, 0, size);
        } else {
            System.arraycopy(elements, begin, newElements, 0, elements.length - begin);
            System.arraycopy(elements, 0, newElements, elements.length - begin, end);
        }
        return newElements;
    }

    private void ensureCapacity(int capacity) {
        if (capacity <= elements.length) {
            return;
        }
        elements = expandArray(2 * capacity);
        end = size;
        begin = 0;
    }
}
