/**
 * Created by Greg on 23.02.2017.
 */

// inv: size > 0, a[0], ... ,a[size - 1] ≠ null) ∨ 
// ∨ (size = 0, a = ∅) 

// immutable = (size = size' ∧
// ∧ ∀i ∈ [0, size) a[i] = a[i]')
public class ArrayQueueModule {
    private static int begin = 0, size = 0;
    private static Object[] elements = new Object[5];

    // pre: element ≠ null
    // post: size = size' + 1 ∧ a[size'] = element ∧
    // ∧ ∀i ∈ [0, size') a[i] = a[i]'
    public static void enqueue(Object element) {
        assert element != null : "element = 0!";

        ensureCapacity(size + 1);
        elements[(begin + size) % elements.length] = element;
        size++;
    }

    // pre: size > 0
    // post: R = a[0] ∧ immutable
    public static Object element() {
        assert size > 0 : "size = 0!";

        return elements[begin];
    }

    // pre: size > 0
    // post: R = a[0] ∧ size = size' - 1 ∧ 
    // ∧  ∀i ∈ [1, size) a[i - 1] = a[i]'
    public static Object dequeue() {
        assert size > 0 : "size = 0!";

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

    // post: size = 0
    public static void clear() {
        begin = 0;
        size = 0;
        elements = new Object[5];
    }

    // post: R = "[a[0], ..., a[size - 1]]") ∨ immutable
    public static String toStr() {
        if(size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        int i = begin;
        while(i <  begin + size) {
            sb.append(", ").append(elements[i++ % elements.length]);
        }
        return "[" + sb.substring(2) + "]";
    }

    private static void ensureCapacity(int capacity) {
        if (capacity <= elements.length) {
            return;
        }
        Object[] newElements = new Object[2 * capacity];
        if (begin + size < elements.length) {
            System.arraycopy(elements, begin, newElements, 0, size);
        } else {
            System.arraycopy(elements, begin, newElements, 0, elements.length - begin);
            System.arraycopy(elements, 0, newElements, elements.length - begin, (begin + size) % elements.length);
        }
        elements = newElements;
        begin = 0;
    }
}
