package AssignementTwo;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Parallel {

    private static final int ArraySize = 10;
    private static final int[] sharedArray = new int[ArraySize];
    private static final Lock lock = new ReentrantLock();
    private static final Random random = new Random();

    // Function to generate random values for the array
    public static void generateRandomValues() {
        lock.lock();
        try {
            for (int i = 0; i < ArraySize; i++) {
                sharedArray[i] = random.nextInt(1000); // Generate random values between 0 and 999
            }
            System.out.println("Generated random values between 0 and 999 for the array.");
        } finally {
            lock.unlock();
        }
    }

    // Function to sort the array in ascending order
    public static void ascendingSort() {
        lock.lock();
        try {
            for (int i = 0; i < ArraySize - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < ArraySize; j++)
                    if (sharedArray[j] < sharedArray[minIndex]) minIndex = j;
                int temp = sharedArray[minIndex];
                sharedArray[minIndex] = sharedArray[i];
                sharedArray[i] = temp;
            }
            System.out.print("Sorted array in ascending order: ");
            printArray();
        } finally {
            lock.unlock();
        }
    }

    // Function to sort the array in descending order
    public static void descendingSort() {
        lock.lock();
        try {
            for (int i = 0; i < ArraySize - 1; i++) {
                int maxIndex = i;
                for (int j = i + 1; j < ArraySize; j++)
                    if (sharedArray[j] > sharedArray[maxIndex]) maxIndex = j;
                int temp = sharedArray[maxIndex];
                sharedArray[maxIndex] = sharedArray[i];
                sharedArray[i] = temp;
            }
            System.out.print("Array sorted in descending order: ");
            printArray();
        } finally {
            lock.unlock();
        }
    }

    // Helper function to print the contents of the array
    public static void printArray() {
        for (int i = 0; i < ArraySize; i++) {
            System.out.print(sharedArray[i] + " ");
        }
        System.out.println(); // Move to the next line
    }

    // Main function
    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            while (true) {
                generateRandomValues();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    System.out.println("Error with thread 1: " + e);
                }
            }
        }, "Generate Random Values Thread");

        Thread thread2 = new Thread(() -> {
            while (true) {
                ascendingSort();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    System.out.println("Error with thread 2: " + e);
                }
            }
        }, "Ascending Sort Array Thread");

        Thread thread3 = new Thread(() -> {
            while (true) {
                descendingSort();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    System.out.println("Error with thread 3: " + e);
                }
            }
        }, "Descending Sort Array Thread");

        // Start all threads
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

