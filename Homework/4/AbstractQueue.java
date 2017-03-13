/**
 * Created by Greg on 04.03.2017.
 */
public abstract class AbstractQueue implements Queue {
    protected int size = 0;

    public void enqueue(Object element) {
        assert element != null : "element = 0!";

        enqueueImpl(element);
        size++;
    }

    abstract void enqueueImpl(Object element);

    public Object element() {
        assert size > 0 : "size = 0!";

        return elementImpl();
    }

    abstract Object elementImpl();

    public Object dequeue() {
        assert size > 0 : "size = 0!";

        Object result = dequeueImpl();
        size--;
        return result;
    }

    abstract Object dequeueImpl();

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        clearImpl();
    }

    abstract void clearImpl();
}
