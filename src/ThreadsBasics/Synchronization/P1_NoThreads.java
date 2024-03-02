package ThreadsBasics.Synchronization;

public class P1_NoThreads {

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
        //main thread running both function1 and function2

        function1();    //call function 1

        function2();    //call function 2
    }
}
