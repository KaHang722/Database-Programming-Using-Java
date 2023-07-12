package assignment6;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Question2 {
    /*
     *Since question ask to synchronise on sum object, this is implemented here but since java caches integer value and
     * reuses it this can lead to deadlock or improper synchronisation.
     */
    private Integer sum = Integer.valueOf(0);
    private final Object lock = new Object();
    private ExecutorService executor;

    public void perform() {
        /*
         *Without Synchronisation
         */
        perform1();
        /*
         * Synchronisation on sum variable
         */
        perform2();
        /*
         * Synchronisation on constant lock object
         */
        perform3();
    }

    private void perform1() {
        executor = Executors.newCachedThreadPool();

        // Create and launch 100 threads
        for (int i = 0; i < 1000; i++) {
            executor.execute(() -> sum += 1);
        }

        executor.shutdown();

        // Wait until all tasks are finished
        while (!executor.isTerminated()) {
        }

        System.out.println("No Sync : " + sum);
    }

    private void perform2() {
        executor = Executors.newCachedThreadPool();
        sum = Integer.valueOf(0);

        // Create and launch 100 threads
        for (int i = 0; i < 1000; i++) {
            executor.execute(() -> {
                synchronized (sum) {
                    sum += 1;
                }
            });
        }

        executor.shutdown();

        // Wait until all tasks are finished
        while (!executor.isTerminated()) {
        }

        System.out.println("Sync on sum variable : " + sum);
    }

    private void perform3() {
        executor = Executors.newCachedThreadPool();
        sum = Integer.valueOf(0);

        // Create and launch 100 threads
        for (int i = 0; i < 1000; i++) {
            executor.execute(() -> {
                synchronized (lock) {
                    sum += 1;
                }
            });
        }

        executor.shutdown();

        // Wait until all tasks are finished
        while (!executor.isTerminated()) {
        }

        System.out.println("Sync on constant object " + sum);
    }
}
