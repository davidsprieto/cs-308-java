package ThreadsBasics.Multithreading;

public class G_ThreadJoin {

    public static Thread thread1;
    public static Thread thread2;

    public static void function1() {

        //-----------------------Thread 1 is running this function
        for (int i = 0; i < 20; i++) {
            System.out.println("Thread " + Thread.currentThread().getName() + " is running");

        }
    }

    public static void function2() {

        //-----------------------Thread 2 is running this function
        try {
            thread1.join();           // Wait for thread1 to finish

            //thread1.join(5000);     // Waiting for 5 seconds for thread1 to finish

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int i = 0; i < 20; i++) {
            System.out.println("Thread " + Thread.currentThread().getName() + " is running");

        }
    }

    public static void main(String[] args) {

		/*
			We can prevent/delay the execution of a thread by using one of the following methods:
				yield(), sleep() and join() methods
		*/

		/*
		    join(): is used to join/restrict the start of a thread�s execution to the end of other thread�s execution
		    -- that means, thread B does not start running until thread A ends.

				// 1st use: waits for thread A to die.

				If any executing thread B calls join() on thread A (i.e; A.join())
					Immediately, thread B will enter into waiting state (blocked) until A completes its execution.


				// 2nd use: waits at most certain amount of milliseconds for thread A to die

				join(long millis) method waits at most this much milliseconds for certain thread to die.
				with a timeout value of 0 means to wait forever
				Giving a timeout within join(), will make the join() effect to be nullified after the specific timeout.

		 */

        thread1 = new Thread(
                new Runnable() {
                    public void run() {
                        function1();
                    }
                }
        );
        thread1.setName("first child");


        thread2 = new Thread(
                new Runnable() {
                    public void run() {
                        function2();
                    }
                }
        );
        thread2.setName("second child");


        thread1.start();
        thread2.start();
    }
}
