package ThreadsBasics.Multithreading;

public class C_CreateSubThread {

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

    public static void main(String[] args) {

        System.out.println("Main thread and one child thread ...");

        //Within the main thread, we can now create "child" thread who can further create more "child" threads.

		/* the child is an object of type "Thread", and the constructor the Runnable interface.
		   Once started, this Thread will call the Runnable instances "run()"" method is a separate thread.*/

        Thread thread1 = new Thread(
                new Runnable() {
                    public void run() {
                        function1();        //assign function1 execution to thread1
                    }
                }
        );

        thread1.setName("first child thread");   //set a name for this newly created child thread
        thread1.setPriority(8);                  //set a priority for this newly created child thread


        //------Now lets run function1 in parallel with (function2 and function3)
        //------Run function1 on thread1
        //------Function2 and function3 are running sequentially on the main thread
        //-- ---The order matters, which thread starts first before the CPU switches to the other thread

        thread1.start();     //ask thread1 to run in parallel with the main thread
        //, to execute the assigned function (function1)

        function2();         //run function2 on the main thread

        function3();         //run function3 on the main thread

    }
}
