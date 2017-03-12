/**
 * Created by Greg on 04.03.2017.
 */
public class ArrayQueue extends AbstractQueue{
    private int begin = 0;
    private Object[] elements = new Object[5];

    public void enqueueImpl(Object element) {
        ensureCapacity(size + 1);
        elements[(begin + size) % elements.length] = element;
    }

    public Object element() {
        return elements[begin];
    }

    public Object dequeueImpl() {
        Object result = elements[begin];
        elements[begin] = null;
        begin = ++begin % elements.length;
        return result;
    }

    public void clearImpl() {
        elements = new Object[5];
        begin = 0;
    }

    private void ensureCapacity(int capacity) {
        if(capacity <= elements.length) {
            return;
        }
        Object[] newElements = new Object[2 * capacity];
        if(begin < (begin + size) % elements.length){
            System.arraycopy(elements, begin, newElements, 0, size);
        } else {
            System.arraycopy(elements, begin, newElements, 0, elements.length - begin);
            System.arraycopy(elements, 0, newElements, elements.length - begin, (begin + size) % elements.length);
        }
        elements = newElements;
        begin = 0;
    }
}
