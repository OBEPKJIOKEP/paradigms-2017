import java.util.Arrays;

/**
 * Created by Greg on 13.02.2017.
 */
public class BinarySearchMissing {

    public static void main(String[] args) {
        boolean implementation = true; // true - iterative, false - recursive
        int key = Integer.parseInt(args[0]);
        int[] array = Arrays.stream(args).skip(1).mapToInt(Integer::parseInt).toArray();
        System.out.println(implementation ? 
                                    iterBinarySearch(key, array) : 
                                    recursiveBinarySearch(key, array, -1, array.length));
    }

    // ip(insertion point) = (∃i: key ≥ a[i], min{i | key ≥ a[i]}) ∨
    // ∨ ip = (∄i: key = a[i], a.length)

    // pre: i, j ∈ [0, a.length):  i ≤ j a[i] ≥ a[j] ∧ a[-1] = +∞ ∧ a[a.length] = -∞
    // post: (R = ip, ip ≤ a.length ∧ array[ip] == key) ∨
    // ∨ (R = -ip - 1, !(ip ≤ a.length ∧ array[ip] == key)
    static int iterBinarySearch(int key, int[] array) {
        int l = -1, r = array.length;
        // inv: l < ip ≤ r
        while (r - l > 1) {
            int m = (r + l) / 2; // l < m ≤ r, m - середина (l, r]
            if (array[m] > key) {
                // pre ∧ (a[0] ≥ a[m] > key) => m < ip
                // inv ∧ (l < m < ip) => m < ip ≤ r
                // (r - m) ~ (r - l) / 2
                l = m;
                // l < ip ≤ r - inv
            } else {
                // pre ∧ (x ≥ a[m] ≥ a[a.length - 1]) => ip ≤ m
                // inv ∧ (ip ≤ m ≤ r) => l < ip ≤ m
                // (m - l) ~ (r - l) / 2
                r = m;
                // l < ip ≤ r - inv
            }
        }
        // r - 1 ≤ l(not while) < r(inv) ∧ l < ip ≤ r(inv) =>
        // => (l = r - 1) ∧ l < ip ≤ r =>
        // => ip = r
        return (r < array.length && array[r] == key) ? r : -r - 1;// obvious cause post
    }

    // ip(insertion point) = (∃i: key ≥ a[i], min{i | key ≥ a[i]}) ∨
    // ∨ ip = (∄i: key = a[i], a.length)

     // pre: -1 ≤ l < ip ≤ r ≤ a.length ∧ a[-1] = +∞ ∧ a[a.length] = -∞
     // ∧ i, j ∈ [0, a.length):  i ≤ j a[i] ≥ a[j]
     // post: (R = ip, ip < a.length ∧ array[ip] == key) ∨
     // ∨ (R = -ip - 1, !(ip < a.length ∧ array[ip] == key)
    static int recursiveBinarySearch(int key, int[] array, int l, int r) {
        if (r - l <= 1) {
            // r - 1 ≤ l(not while) < r(inv) ∧ l < ip ≤ r(inv) =>
            // => (l = r - 1) ∧ l < ip ≤ r =>
            // => ip = r
            return (r < array.length && array[r] == key) ? r : -r - 1;// obvious cause post
        }
        int m = (l + r) / 2; // l < m ≤ r, m - середина (l, r]
        if (array[m] > key) {
            // pre ∧ (a[0] ≥ a[m] > key) => m < ip
            // pre ∧ inv ∧ (l < m < ip) => m < ip ≤ r
            // (r - m) ~ (r - l) / 2
            // (m > l ≥ -1 ∧ r ≤ a.length ∧ m < ip ≤ r) =>
            // => (m ≥ -1 ∧ r ≤ a.length ∧ m < ip ≤ r)- pre
            return recursiveBinarySearch(key, array, m, r);
        } else {
                // pre ∧ (x ≥ a[m] ≥ a[a.length - 1]) => ip ≤ m
                // pre ∧ inv ∧ (ip ≤ m ≤ r) => l < ip ≤ m
                // (m - l) ~ (r - l) / 2
                // (l ≥ -1 ∧ m ≤ r ≤ a.length ∧ l < ip ≤ m) =>
                // => (l ≥ -1 ∧ m ≤ a.length ∧ l < ip ≤ m)
            return recursiveBinarySearch(key, array, l, m);
        }
    }
}
