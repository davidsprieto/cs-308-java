package ThreadsBasics.Synchronization;

import java.util.concurrent.*;


public class P4_Semaphore {


	/*
		Important to note:

		    Mutex Lock in literature uses acquire() and release()
		               in Java these functions are lock() and unlock() respectively

		    Semaphore  in literature uses wait() and signal()
		               in Java these functions are acquire() and release() respectively
	*/


    //semaphore lock
    //counting semaphore with two instances, change the number of instances and track the acquire/release sequence

    public static Semaphore semaphore = new Semaphore(2);


    public static void function1() {
        for (int i = 0; i < 5; i++) {
            try {

                System.out.println("function 1 trying to acquire the lock");

                semaphore.acquire();

                System.out.println("function 1 acquired the lock ... start the critical section");

                //critical section
                System.out.println("function 1 Locks remaining >> " + semaphore.availablePermits());
                //end of critical section

                semaphore.release();

                System.out.println("exit the critical section ... function 1 Locks Released");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void function2() {
        for (int j = 0; j < 5; j++) {
            try {

                System.out.println("function 2 trying to acquire the lock");

                semaphore.acquire();

                System.out.println("function 2 acquired the lock ... start the critical section");

                //critical section
                System.out.println("function 2 Locks remaining >> " + semaphore.availablePermits());
                //end of critical section

                semaphore.release();

                System.out.println("exit the critical section ... function 2 Locks Released");


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {

        //create thread 1 to run function 1
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                function1();
            }
        });

        //create thread 2 to run function 2
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                function2();
            }
        });

        thread1.start();

        thread2.start();

    }
}
