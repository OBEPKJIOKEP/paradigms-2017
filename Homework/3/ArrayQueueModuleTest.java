public class ArrayQueueModuleTest {
    public static void fill(int from, int to) {
        for (int i = from; i < to; i++) {
            ArrayQueueModule.enqueue(i);
        }
    }

    public static void fill() {
        fill(0, 20);
    }

    public static void printQueue() {
        while (!ArrayQueueModule.isEmpty()) {
            System.out.println(
                ArrayQueueModule.size() + " " 
                ArrayQueueModule.element() + " " +
                ArrayQueueModule.dequeue()
            );
        }
    }

    public static void linearTest() {
        fill();
        printQueue();
        }
    }

    public static void clearTest() {
        fill(0, 10);
        ArrayQueueModule.clear();
        fill(10, 20)
        printQueue();
    }

    public static pushPopTest() {
        for (int i = 0; i < 100; i++)
        {
            ArrayQueueModule.enqueue(i);
            printQueue();
        }
    }
    public static void main(String[] args) {
       System.out.println("Running linear test:");
       linearTest();
       System.out.println("Running clear test: ");
       clearTest();
       System.out.println("Running pushpop test: ");
       pushPopTest();
    }
}
