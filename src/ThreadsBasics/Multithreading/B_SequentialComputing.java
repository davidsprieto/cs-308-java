package ThreadsBasics.Multithreading;

public class B_SequentialComputing {

    public static void function1() {
        for (int i = 0; i < 10; i++)
            System.out.println("Thread " + Thread.currentThread().getName() + " is running and value of i: " + i);
    }

    public static void function2() {
        for (int j = 0; j < 10; j++)
            System.out.println("Thread " + Thread.currentThread().getName() + " is running and value of j: " + j);
    }

    public static void function3() {
        for (int k = 0; k < 10; k++)
            System.out.println("Thread " + Thread.currentThread().getName() + " is running and value of k: " + k);
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Sequential programming");

        /* When a Java program starts up, one thread begins running immediately.
         * This is usually called the main thread of our program. */

        function1();          //run function1 first by the main thread

        Thread.sleep(1000);   //you can ask the current running thread to sleep (stop work) for number of milliseconds

        function2();          //run function2 second by the main thread

        Thread.sleep(1000);   //you can ask the current running thread to sleep (stop work) for number of milliseconds

        function3();          //run function3 third by the main thread

        Thread.sleep(1000);   //you can ask the current running thread to sleep (stop work) for number of milliseconds

        System.out.println("Exiting the main Thread");

    }
}
