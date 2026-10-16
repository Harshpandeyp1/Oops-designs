import Logger.Logger;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Program started");

        Logger a = Logger.getInstance();
        Logger b = Logger.getInstance();

        System.out.println("Same object? " + (a == b));

        a.log("Application started");
        b.log("Same logger, different variable");

        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() ->
                    System.out.println("Hash: " + System.identityHashCode(Logger.getInstance()))
            );
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }
    }
}