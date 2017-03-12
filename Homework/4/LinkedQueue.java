/**
 * Created by Greg on 04.03.2017.
 */
public class LinkedQueue extends AbstractQueue{
    private Node begin = null, end = null;

    public void enqueueImpl(Object element) {
        Node prevEnd = end;
        end = new Node(element, null);
        if (size == 0) {
            begin = end;
        } else {
            prevEnd.next = end;
        }
        /*if(size == 0) {
            begin = new Node(element, null);
            end = begin;
        } else {
            end.next = new Node(element, null);
            end = end.next;
        }*/
    }

    public Object element() {
            return begin.value;
    }

    public Object dequeueImpl() {
        Object result = begin.value;
        begin = begin.next;
        if(size == 1) {
            end = null;
        }
        return result;
    }

    public void clearImpl() {
        begin = null;
        end = null;
    }
}
