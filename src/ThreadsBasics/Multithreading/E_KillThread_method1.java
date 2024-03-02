package ThreadsBasics.Multithreading;

public class E_KillThread_method1 {

    static volatile boolean exit = false;

    public static void function1() {

        while (!exit) {
            System.out.println("Thread " + Thread.currentThread().getName() + " is running");

            //wait 1 second between the iterations
            try {
                Thread.sleep(1000);                //make the thread that runs function1 to sleep for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {

		/*
		 	A thread is automatically destroyed when the run() method has completed.
			But it might be required to kill/stop a thread before it completes its life-cycle.
			Previously, methods suspend(), resume() and stop() were used to manage the execution of threads.
			But these methods were deprecated by Java 2 because they could result in system failures.
			Modern ways to suspend/stop a thread are by using a boolean flag and Thread.interrupt() method.
		 */


		/*
		 * 1st method "volatile boolean flag":
		 * 		We can define a boolean variable which is used for stopping/killing threads.
		 * 		Whenever we want to stop a thread, this variable will be set to true.

				static volatile boolean exit = false;

		 * The Java volatile keyword is used to mark a Java variable as "being stored in main memory".
		 * With volatile variable, every read/write operation will be done from/to the computer's main memory, and not from the CPU cache.
		 * This guarantees visibility of changes to this variable across threads (thread safe),
		 * as the threads cannot have locally cached values of it.
		 *
		 * Otherwise, each thread may copy variables from main memory into a CPU cache while working on them,
		 * and if your computer contains more than one CPU, each thread may run on a different CPU (different Caches).
		 */

        Thread thread1 = new Thread(
                new Runnable() {
                    public void run() {
                        function1();
                    }
                }
        );
        thread1.setName("Thread 1");
        thread1.start();

        try {
            Thread.sleep(5000);     //Make the main thread sleep for 5 seconds
            //In another word, let thread1 work for 5 seconds
        } catch (Exception e) {
            e.printStackTrace();
        }

        exit = true;                //now the main thread is working and we can flag the exit of thread1

        System.out.println("Exiting thread1");

        try {
            Thread.sleep(5000);     //Make the main thread sleep for 5 seconds
            //In another word, give some time for thread1 to be terminated
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
         * isAlive() tests if this thread is alive.
         * A thread is alive if it has been started and has not yet died.
         */
        System.out.println("Is alive? " + thread1.isAlive()); // Checks if this thread is alive


        //continue the work of the main thread

        System.out.println("Exiting the main Thread");
    }

}

