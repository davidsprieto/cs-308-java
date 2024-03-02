package ThreadsBasics.Multithreading;

public class F_KillThread_method2 {

    public static void function1() {

        while (!Thread.interrupted()) {    //keep this thread until it is interrupted

            System.out.println("Thread " + Thread.currentThread().getName() + " is running");

            //if an interrupt occurs while the thread is sleeping, this will through an exception.
            //so if you use the interrupt method to stop a thread, don't use Thread.sleep() as we did in previous sketches
        }

        System.out.println("Exiting Thread " + Thread.currentThread().getName());
    }


    public static void main(String[] args) {

		/*
		 	A thread is automatically destroyed when the run() method has completed.
			But it might be required to kill/stop a thread before it completes its life cycle.
			Previously, methods suspend(), resume() and stop() were used to manage the execution of threads.
			But these methods were deprecated by Java 2 because they could result in system failures.

			Modern ways to suspend/stop a thread are by using a boolean flag and Thread.interrupt() method.
		 */


        /*
         * 2nd Method: Using Thread.interrupt() method:
         * Whenever an interrupt is sent to a thread, it should stop whatever task it is performing.
         * It is very likely that whenever the thread receives an interrupt, it is to be terminated.
         * The thread can call "if(Thread.interrupted())" to check for received interrupts.
         * Other threads can call "Thread.interrput()" method.
         * Interrrupt is a flag (default value is false), and the function .interrupt() sets this flag to true
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
            Thread.sleep(2000);     //Make the main thread sleep for 2 seconds
            //In another word, let thread1 work for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*
         * isAlive() tests if this thread is alive.
         * A thread is alive if it has been started and has not yet died.
         */
        System.out.println("Is alive? " + thread1.isAlive()); // Checks if this thread is alive


        thread1.interrupt();        //the current thread (the main thread) interrupts thread1


        try {
            Thread.sleep(5000);     //Make the main thread sleep for 5 seconds
            //In another word, give some time for thread1 to be terminated
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("Is alive? " + thread1.isAlive()); // Checks if this thread is alive


        //continue the work of the main thread
        System.out.println("Exiting the main Thread");
    }
}
