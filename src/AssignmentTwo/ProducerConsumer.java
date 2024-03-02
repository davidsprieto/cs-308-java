package AssignmentTwo;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer {


    /*
     * In this question use mutex lock(s) to enable process synchronization
     *
     * Thread 1 and thread 2 share a single buffer (1D Array), where:
     * thread 1 adds item to the shared buffer only if there is a free space
     * thread 2 consumes item from the shared buffer only if there is an available item in the buffer
     *
     */


    // shared resources between thread 1 and thread 2 are:
    // DO NOT CHANGE THESE VARIABLES
    public static int BufferSize = 5;                    //the size of the buffer
    public static int count = 0;                         //keeps track of the number of items currently in buffer
    public static int[] buffer = new int[BufferSize];    //the buffer to add data into and consume data from


    // add below any further resources you think the producer and consumer functions must share

    public static Lock lock = new ReentrantLock();

    //--------------------------------------------end of shared resources section


    // this function simply displays the content of the shared buffer and which thread made the call
    // DO NOT CHANGE THIS FUNCTION
    public static void displayStatus() {

        if (Thread.currentThread().getName().equals("producer"))
            System.out.println("Producer successfully added the new item to the shared buffer");
        else
            System.out.println("Consumer successfully removed an item from the shared buffer");

        System.out.print(" the " + Thread.currentThread().getName() + " is displaying the content of the buffer: ");
        for (int i = 0; i < BufferSize; i++) {
            System.out.print(buffer[i] + "  ");
        }
        System.out.println(" and the value of the count is: " + count);
    }


    // this function accepts an input integer item to be added to the shared buffer
    public static void Producer(int item) {
        lock.lock();
        try {
            System.out.println("The Producer is trying to add the new item (" + item + ") to the shared buffer");

            // Add the input item only if there is a free space in the shared buffer
            // use if statements rather than waiting while loops
            // Call the displayStatus() function after you add the item and before release the lock
            // Implement the producer functionality in the area below

            if (count < BufferSize) {
                for (int i = 0; i < BufferSize; i++) {
                    if (buffer[i] == -1) {
                        buffer[i] = item;
                        count++;
                        break;
                    }
                }
                displayStatus();
            }

            //--------------------------------------------end of Producer function
        } catch (Exception e) {
            System.out.println("Problem with the producer function " + e);
        } finally {
            lock.unlock();
        }
    }


    // this function removes an item from the shared buffer
    public static void Consumer() {
        lock.lock();
        try {
            System.out.println("The Consumer is trying to consume the ready item from the buffer");

            // Consume one item (overwrite its value with -1) from the buffer only if there is an available item in the buffer
            // use if statements rather than waiting while loops
            // Call the displayStatus() function after you remove the item and before release the lock
            // Implement the consumer functionality in the area below

            if (count > 0) {
                for (int i = 0; i < BufferSize; i++) {
                    if (buffer[i] != -1) {
                        buffer[i] = -1;
                        count--;
                        break;
                    }
                }
                displayStatus();
            }

            //--------------------------------------------end of Consumer function
        } catch (Exception e) {
            System.out.println("Problem with the consumer function " + e);
        } finally {
            lock.unlock();
        }
    }


    // this is the main function
    // DO NOT CHANGE THIS SECTION
    public static void main(String[] args) {

        count = 0;
        for (int i = 0; i < BufferSize; i++) {
            buffer[i] = -1;         //free spots
        }


        //create thread 1 to run function 1
        Thread thread1 = new Thread(() -> {
            while (true) {
                try {
                    Producer(1 + new Random().nextInt(9));          //random value between 1 and 10
                    Thread.sleep(200 + (int) (Math.random() * 500));   //random delay between 200 and 500
                } catch (Exception e) {
                    System.out.println("Problem with thread 1: " + e);
                }
            }
        });


        //create thread 2 to run function 2
        Thread thread2 = new Thread(() -> {
            while (true) {
                try {
                    Consumer();
                    Thread.sleep(200 + (int) (Math.random() * 500));   //random delay between 200 and 500
                } catch (Exception e) {
                    System.out.println("Problem with thread 2: " + e);
                }
            }
        });

        //ask the threads to start running
        thread1.setName("producer");
        thread1.start();

        thread2.setName("consumer");
        thread2.start();
    }
}
