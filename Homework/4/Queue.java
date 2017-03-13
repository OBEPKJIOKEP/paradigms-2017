/**
 * Created by Greg on 04.03.2017.
 */

// inv: size > 0, a[0], ... ,a[size - 1] ≠ null) ∨ 
// ∨ (size = 0, a = ∅) 

// immutable = (size = size' ∧
// ∧ ∀i ∈ [0, size) a[i] = a[i]')
public interface Queue {
    // pre: element ≠ null
    // post: size = size' + 1 ∧ a[size'] = element ∧
    // ∧ ∀i ∈ [0, size') a[i] = a[i]'
    void enqueue(Object element);
    // pre: size > 0
    // post: R = a[0] ∧ immutable
    Object element();
     // pre: size > 0
    // post: R = a[0] ∧ size = size' - 1 ∧ 
    // ∧  ∀i ∈ [1, size) a[i - 1] = a[i]'
    Object dequeue();
    // post: R = size ∧ immutable
    int size();
    // post: R = (size == 0) ∧ immutable
    boolean isEmpty();
    // post: size = 0
    void clear();
}
