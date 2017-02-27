/**
 * Created by Greg on 23.02.2017.
 */

// inv: begin ≥ 0 ∧ end ≥ 0 ∧ size ≥ 0 ∧ (end > begin, ∀i ∈ [begin, end) a[i] ≠ null ∨ 
// ∨ (end <= begin ∧ size ≠ 0), ∀i ∈ [begin, a.size] ∪ [0, end) a[i] ≠ null)

// immutable = begin = begin' ∧ end = end' ∧ 
// ((end > begin, ∀i ∈ [begin, end) a[i] = a[i]') ∨ 
// ∨ (end <= begin ∧ size ≠ 0, ∀i ∈ [begin, a.size] ∪ [0, end) a[i] = a[i]'))
public class ArrayQueueModule {
    private static int begin = 0, end = 0, size = 0;
    private static Object[] elements = new Object[5];

    // pre: element ≠ null
    // post: end = (end' + 1) % a.length ∧ size = size' + 1 ∧
    // ∧ begin = begin' ∧ a[end'] = element ∧ 
    // ∧ ((end' > begin, ∀i ∈ [begin, end') a[i] = a[i]') ∨ 
    // ∨ (end' ≤ begin ∧ size ≠ 0, ∀i ∈ [begin, a.size] ∪ [0, end') a[i] = a[i]'))
    public static void enqueue(Object element) {
        assert element != null;

        ensureCapacity(size + 1);
        elements[end] = element;
        end = ++end % elements.length;
        size++;
    }

    // pre: size > 0
    // post: R = a[begin] ∧ immutable
    public static Object element() {
        assert size > 0;

        return elements[begin];
    }

    // pre: size > 0
    // post: R = a[begin] ∧ begin = (begin' + 1) % a.length ∧ 
    // ∧ size = size' - 1 ∧ end = end' ∧
    // ∧ ((end > begin, ∀i ∈ [begin, end) a[i] = a[i]') ∨ 
    // ∨ (end ≤ begin ∧ size ≠ 0, ∀i ∈ [begin, a.size] ∪ [0, end) a[i] = a[i]')) 
    public static Object dequeue() {
        assert size() > 0;

        Object result = elements[begin];
        elements[begin] = null;
        begin = ++begin % elements.length;
        size--;
        return result;
    }
    
    // post: R = size ∧ immutable
    public static int size() {
        return size;
    }

    // post: R = (size == 0) ∧ immutable
    public static boolean isEmpty() {
        return size == 0;
    }

    // post: begin = 0 ∧ end = 0 ∧ size = 0  
    public static void clear() {
        begin = 0;
        end = 0;
        size = 0;
        elements = new Object[5];
    }

    public static Object[] toArray() {
        return expandArray(size);
    }

    private static Object[] expandArray(int newArraySize) {
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

    private static void ensureCapacity(int capacity) {
        if (capacity <= elements.length) {
            return;
        }
        elements = expandArray(2 * capacity);
        end = size;
        begin = 0;
    }
}
