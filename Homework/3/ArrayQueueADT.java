/**
 * Created by Greg on 23.02.2017.
 */

// в даном контракте разрешается не использовать queue, во всех упоминаниях не static полей,  
// он неявный(иначе была бы каша)

// inv: begin ∈ [0, a.length] ∧ end ∈ [0, a.length] ∧ size ≥ 0 ∧ 
// ∧ (end > begin, ∀i ∈ [begin, end) a[i] ≠ null ∨ 
// ∨ (end ≤ begin ∧ size ≠ 0), ∀i ∈ [begin, a.length] ∪ [0, end) a[i] ≠ null)

// immutable = begin = begin' ∧ end = end' ∧ 
// ((end > begin, ∀i ∈ [begin, end) a[i] = a[i]') ∨ 
// ∨ (end ≤ begin ∧ size ≠ 0, ∀i ∈ [begin, a.length] ∪ [0, end) a[i] = a[i]'))
public class ArrayQueueADT {
    private int begin = 0, end = 0, size = 0;
    private Object[] elements = new Object[5];

    // pre: element ≠ null ∧ queue ≠ null
    // post: end = (end' + 1) % a.length ∧ size = size' + 1 ∧
    // ∧ begin = begin' ∧ a[end'] = element ∧ 
    // ∧ (end' > begin, ∀i ∈ [begin, end') a[i] = a[i]') ∨ 
    // ∨ (end' ≤ begin ∧ size ≠ 0, ∀i ∈ [begin, a.length] ∪ [0, end') a[i] = a[i]')
    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert element != null || queue != null;

        ensureCapacity(queue, queue.size + 1);
        queue.elements[queue.end] = element;
        queue.end = ++queue.end % queue.elements.length;
        queue.size++;
    }

    // pre: size > 0 ∧ queue ≠ null
    // post: R = a[begin] ∧ immutable
    public static Object element(ArrayQueueADT queue) {
        assert queue != null || queue.size > 0;

        return queue.elements[queue.begin];
    }

    // pre: size > 0 ∧ queue ≠ null
    // post: R = a[begin] ∧ begin = (begin' + 1) % a.length ∧ 
    // ∧ size = size' - 1 ∧ end = end' ∧
    // ∧ (end > begin, ∀i ∈ [begin, end) a[i] = a[i]') ∨ 
    // ∨ (end ≤ begin ∧ size ≠ 0, ∀i ∈ [begin, a.length] ∪ [0, end) a[i] = a[i]') 
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue != null || queue.size > 0;

        Object result = queue.elements[queue.begin];
        queue.elements[queue.begin] = null;
        queue.begin = ++queue.begin % queue.elements.length;
        queue.size--;
        return result;
    }

    // pre: queue ≠ null
    // post: R = size ∧ immutable
    public static int size(ArrayQueueADT queue) {
        assert queue != null;

        return queue.size;
    }

    // pre: queue ≠ null
    // post: R = (size == 0) ∧ immutable
    public static boolean isEmpty(ArrayQueueADT queue) {
        assert queue != null;

        return queue.size == 0;
    }

    // pre: queue ≠ null
    // post: begin = 0 ∧ end = 0 ∧ size = 0  
    public static void clear(ArrayQueueADT queue) {
        assert queue != null;

        queue.begin = 0;
        queue.end = 0;
        queue.size = 0;
        queue.elements = new Object[5];
    }

    // pre: queue ≠ null
    // post:(end > begin, R = "[a[begin], ..., a[end - 1]]") ∨
    // ∨ (end ≤ begin ∧ size ≠ 0, R = "[a[begin], ..., a[a.length - 1], a[0], ..., a[end - 1]]" ∧
    // ∧ immutable  
    public static String toStr(ArrayQueueADT queue) {
        if(queue.size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        int i = queue.begin;
        while(i < (queue.begin < queue.end ? 
                                queue.end : 
                                queue.end + queue.elements.length)) {
          sb.append(", ").append(queue.elements[i++ % queue.elements.length]);
        }
        return "[" + sb.substring(2) + "]";
    }
    
    // pre: capacity > 0 ∧ queue ≠ null
    // post: (immutable, capacity ≤ a.length ) ∨
    // ∨ ((begin = 0 ∧ end = size ∧ size = size' ∧
    // ∧ (end' > begin', ∀i ∈ [begin', end') a[i - begin'] = a[i]') ∨
    // ∨ (end' ≤ begin' ∧ size' ≠ 0, ∀i ∈ [begin', a.length') a[i - begin'] = a[i]' ∧
    // ∧ ∀i ∈ [0, end') a[i + (a.length' - begin')] = a[i]') ∧
    // ∧ ∀i ∈ [size', 2 * capacity) a[i] = null), capacity > a.length)
    private static void ensureCapacity(ArrayQueueADT queue, int capacity) {
        if (capacity <= queue.elements.length) {
            return;
        }
        Object[] newElements = new Object[2 * capacity];
        if(queue.begin < queue.end) {
            System.arraycopy(queue.elements, queue.begin, newElements, 0, queue.size);
        } else {
            System.arraycopy(queue.elements, queue.begin, newElements, 0, 
                                        queue.elements.length - queue.begin);
            System.arraycopy(queue.elements, 0, newElements, 
                                        queue.elements.length - queue.begin, queue.end);
        }
        queue.elements = newElements;
        queue.end = queue.size;
        queue.begin = 0;
    }
}
