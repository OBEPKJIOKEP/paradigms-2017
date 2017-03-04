/**
 * Created by Greg on 23.02.2017.
 */

// в даном контракте разрешается не использовать this, во всех упоминаниях не static полей он неявный
// inv: begin ∈ [0, a.length] ∧ end ∈ [0, a.length] ∧ size ≥ 0 ∧ 
// ∧ (end > begin, ∀i ∈ [begin, end) a[i] ≠ null ∨ 
// ∨ (end ≤ begin ∧ size ≠ 0), ∀i ∈ [begin, a.length] ∪ [0, end) a[i] ≠ null)

// immutable = begin = begin' ∧ end = end' ∧ 
// ((end > begin, ∀i ∈ [begin, end) a[i] = a[i]') ∨ 
// ∨ (end ≤ begin ∧ size ≠ 0, ∀i ∈ [begin, a.length] ∪ [0, end) a[i] = a[i]'))
public class ArrayQueue {
    private int begin = 0, end = 0, size = 0;
    private Object[] elements = new Object[5];

    // pre: element ≠ null
    // post: end = (end' + 1) % a.length ∧ size = size' + 1 ∧
    // ∧ begin = begin' ∧ a[end'] = element ∧ 
    // ∧ (end' > begin, ∀i ∈ [begin, end') a[i] = a[i]') ∨ 
    // ∨ (end' ≤ begin ∧ size ≠ 0, ∀i ∈ [begin, a.length] ∪ [0, end') a[i] = a[i]')
    public void enqueue(Object element) {
        assert element != null;

        ensureCapacity(size + 1);
        elements[end] = element;
        end = ++end % elements.length;
        size++;
    }

    // pre: size > 0
    // post: R = a[begin] ∧ immutable
    public Object element() {
        assert size > 0;

        return elements[begin];
    }

    // pre: size > 0
    // post: R = a[begin] ∧ begin = (begin' + 1) % a.length ∧ 
    // ∧ size = size' - 1 ∧ end = end' ∧
    // ∧ (end > begin, ∀i ∈ [begin, end) a[i] = a[i]') ∨ 
    // ∨ (end ≤ begin ∧ size ≠ 0, ∀i ∈ [begin, a.length] ∪ [0, end) a[i] = a[i]') 
    public Object dequeue() {
        assert size > 0;

        Object result = elements[begin];
        elements[begin] = null;
        begin = ++begin % elements.length;
        size--;
        return result;
    }

    // post: R = size ∧ immutable
    public int size() {
        return size;
    }

    // post: R = (size == 0) ∧ immutable
    public boolean isEmpty() {
        return size == 0;
    }

    // post: begin = 0 ∧ end = 0 ∧ size = 0  
    public void clear() {
        begin = 0;
        end = 0;
        size = 0;
        elements = new Object[5];
    }

    // post:(end > begin, R = "[a[begin], ..., a[end - 1]]") ∨
    // ∨ (end ≤ begin ∧ size ≠ 0, R = "[a[begin], ..., a[a.length - 1], a[0], ..., a[end - 1]]" ∧
    // ∧ immutable  
    public String toStr() {
        if(size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        int i = begin;
        while(i < (begin < end ? end : end + elements.length)) {
          sb.append(", ").append(elements[i++ % elements.length]);
        }
        return "[" + sb.substring(2) + "]";
    }
    
    // pre: capacity > 0
    // post: (immutable, capacity ≤ a.length ) ∨
    // ∨ ((begin = 0 ∧ end = size ∧ size = size' ∧
    // ∧ (end' > begin', ∀i ∈ [begin', end') a[i - begin'] = a[i]') ∨
    // ∨ (end' ≤ begin' ∧ size' ≠ 0, ∀i ∈ [begin', a.length') a[i - begin'] = a[i]' ∧
    // ∧ ∀i ∈ [0, end') a[i + (a.length' - begin')] = a[i]') ∧
    // ∧ ∀i ∈ [size', 2 * capacity) a[i] = null), capacity > a.length)
    private void ensureCapacity(int capacity) {
        if (capacity <= elements.length) {
            return;
        }
        Object[] newElements = new Object[2 * capacity];
        if(begin < end) {
            System.arraycopy(elements, begin, newElements, 0, size);
        } else {
            System.arraycopy(elements, begin, newElements, 0, elements.length - begin);
            System.arraycopy(elements, 0, newElements, elements.length - begin, end);
        }
        elements = newElements;
        end = size;
        begin = 0;
    }
}
