public class ArrayQueueTest {
    public static void fill(ArrayQueue queue, int from, int to) {
        for (int i = from; i < to; i++) {
            queue.enqueue(i);
        }
    }

    public static void fill(ArrayQueue queue) {
        fill(queue, 0, 20);
    }

    public static void printQueue(ArrayQueue queue) {
        while (!queue.isEmpty()) {
            System.out.println(
                queue.size() + " " +
                queue.element() + " " +
                queue.dequeue()
            );
        }
    }

    public static void linearTest(ArrayQueue queue) {
        fill(queue);
        printQueue(queue);
        }

    public static void clearTest(ArrayQueue queue) {
        fill(queue, 0, 10);
        queue.clear();
        fill(queue, 10, 20);
        printQueue(queue);
    }

    public static void pushPopTest(ArrayQueue queue) {
        for (int i = 0; i < 20; i++)
        {
            queue.enqueue(i);
            printQueue(queue);
        }
    }
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue();
        System.out.println("Running linear test:");
        linearTest(queue);
        System.out.println("Running clear test: ");
        clearTest(queue);
        System.out.println("Running pushpop test: ");
        pushPopTest(queue);
    }
}
