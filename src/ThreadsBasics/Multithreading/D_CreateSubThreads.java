package ThreadsBasics.Multithreading;

public class D_CreateSubThreads {

    public static void function1() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread " + Thread.currentThread().getName() + " is running and value of i: " + i);

            //you can add sleep anywhere, but need to be surrounded by try/catch
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void function2() {
        for (int j = 0; j < 10; j++)
            System.out.println("Thread " + Thread.currentThread().getName() + " is running and value of j: " + j);
    }

    public static void function3() {
        for (int k = 0; k < 10; k++)
            System.out.println("Thread " + Thread.currentThread().getName() + " is running and value of k: " + k);
    }

    public static void main(String[] args) {

        System.out.println("Main thread and one child thread ...");

        //creating thread1, and set some properties (e.g., name)
        Thread thread1 = new Thread(
                new Runnable() {
                    public void run() {
                        function1();        //assign function1 execution to thread1
                    }
                }
        );
        thread1.setName("first child thread");


        //creating thread2, and set some properties (e.g., name)
        Thread thread2 = new Thread(
                new Runnable() {
                    public void run() {
                        function2();        //assign function2 execution to thread2
                    }
                }
        );
        thread2.setName("second child thread");


        // In Parallel:
        //---Run function1 on thread1
        //---Run function2 on thread2
        //---Run function3 on main thread, Funciton3 is running sequentially on the main thread

        thread1.start();     //ask thread1 to run the assigned function (function1) in parallel with the main thread

        thread2.start();     //ask thread2 to run the assigned function (function2) in parallel with the main thread

        function3();         //run function3 on the main thread
    }
}
