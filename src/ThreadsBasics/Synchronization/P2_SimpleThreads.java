package ThreadsBasics.Synchronization;

public class P2_SimpleThreads {

    public static void function1() {
        for (int i = 0; i < 20; i++) {
            try {
                System.out.println("function 1 is running for iteration number " + i);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void function2() {
        for (int j = 0; j < 20; j++) {
            try {
                System.out.println("function 2 is running for iteration number " + j);

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

        //ask the threads to start running
        thread1.start();

        thread2.start();
    }
}
