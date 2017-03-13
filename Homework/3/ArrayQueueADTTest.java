public class ArrayQueueADTTest {
    public static void fill(ArrayQueueADT queue, int from, int to) {
        for (int i = from; i < to; i++) {
            ArrayQueueADT.enqueue(queue, i);
        }
    }

    public static void fill(ArrayQueueADT queue) {
        fill(queue, 0, 20);
    }

    public static void printQueue(ArrayQueueADT queue) {
        while (!ArrayQueueADT.isEmpty(queue)) {
            System.out.println(
                ArrayQueueADT.size(queue) + " " +
                ArrayQueueADT.element(queue) + " " +
                ArrayQueueADT.dequeue(queue)
            );
        }
    }

    public static void linearTest(ArrayQueueADT queue) {
        fill(queue);
        printQueue(queue);
        }

    public static void clearTest(ArrayQueueADT queue) {
        fill(queue, 0, 10);
        ArrayQueueADT.clear(queue);
        fill(queue, 10, 20);
        printQueue(queue);
    }

    public static void pushPopTest(ArrayQueueADT queue) {
        for (int i = 0; i < 20; i++)
        {
            ArrayQueueADT.enqueue(queue, i);
            printQueue(queue);
        }
    }
    public static void main(String[] args) {
        ArrayQueueADT queue = new ArrayQueueADT();
        System.out.println("Running linear test:");
        linearTest(queue);
        System.out.println("Running clear test: ");
        clearTest(queue);
        System.out.println("Running pushpop test: ");
        pushPopTest(queue);
    }
}
