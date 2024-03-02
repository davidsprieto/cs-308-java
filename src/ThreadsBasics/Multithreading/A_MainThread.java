package ThreadsBasics.Multithreading;

public class A_MainThread {

    public static void main(String[] args) {

        System.out.println("Set/Get information about the Main Thread.....");

        /* When a Java program starts up, one thread starts to run immediately to execute the program.
         * This is usually called the "main thread" of our program. */

        // Thread.currentThread() is to access, at any time, the thread currently in the run state
        // After accessing the current thread, we can set/get some descriptive information (e.g., name, priority)


        // We can print the name of the running thread (which is the main thread in this sketch)
        System.out.println(Thread.currentThread().getName());

        // We can print the priority of the running thread (which is the main thread in this sketch)
        /*
         * The priority of a thread is in range of 1 (min) to 10 (max), the default priority is 5
         * Thread with the highest priority will get execution chance prior to other lower threads.
         * Default priority for all other threads depends on the priority of parent thread,
         * and the priority of the thread can be changed at anytime.
         */
        System.out.println("Main thread priority : " + Thread.currentThread().getPriority());


        Thread.currentThread().setName("Our Main Thread");
        Thread.currentThread().setPriority(10);
        System.out.println("Main thread Name : " + Thread.currentThread().getName());
        System.out.println("Main thread priority : " + Thread.currentThread().getPriority());

        //with the end of the program, the main thread terminates
        System.out.println("Exiting the main Thread");
    }
}
