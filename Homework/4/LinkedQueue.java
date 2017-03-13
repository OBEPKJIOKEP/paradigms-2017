/**
 * Created by Greg on 04.03.2017.
 */
public class LinkedQueue extends AbstractQueue{
    private Node begin = null, end = null;

    protected void enqueueImpl(Object element) {
        Node prevEnd = end;
        end = new Node(element, null);
        if (size == 0) {
            begin = end;
        } else {
            prevEnd.next = end;
        }
    }

    protected Object elementImpl() {
        return begin.value;
    }

    protected Object dequeueImpl() {
        Object result = begin.value;
        begin = begin.next;
        if(size == 1) {
            end = null;
        }
        return result;
    }

    protected void clearImpl() {
        begin = null;
        end = null;
    }
}
