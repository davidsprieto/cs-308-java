package AssignmentTwo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WithdrawDeposit {

    /*
     * In this question use mutex lock(s) to enable process synchronization
     *
     * Thread 1 and thread 2 (in the main function) share a single bank account (initial balance of 1000$).
     * Thread 1 can deposit certain input amount to the balance only if the current balance is less than 2000$
     * thread 2 can withdraw certain input amount from the balance only if the current balance is greater than or equal to the input amount.
     *
     */


    // shared resources between thread 1 and thread 2 are:
    public static int balance = 1000;                    // the initial value of the account's balance
    //DO NOT CHANGE THIS VARIABLE

    // add below any further resources you think the deposit and withdraw threads/functions must share

    public static Lock lock = new ReentrantLock();

    //--------------------------------------------end of shared resources section


    // this function simply displays the current balance of the shared account and which thread made the call
    // DO NOT CHANGE THIS FUNCTION
    public static void displayStatus() {
        if (Thread.currentThread().getName().equals("withdraw"))
            System.out.println("The withdraw function successfully took the amount and the current value of the account's balance is: $" + balance);
        else
            System.out.println("The deposit function successfully added the amount and the current value of the account's balance is: $" + balance);
    }


    // this function accepts an input integer amount value to deposit into the shared account
    public static void Deposit(int amount) {
        lock.lock();
        try {
            System.out.println("The deposit function is trying to add $" + amount + " to the shared balance: $" + balance);

            // Deposit the input amount to the balance only if the current balance is less than 2000$
            // Deposit doesn't wait until this condition is true (If the condition is false, skip adding the amount), thus use if statements rather than waiting while loops
            // Call the displayStatus() function after you deposit the amount and before release the lock
            // Implement the deposit functionality, as detailed above, in the area below

            if (balance < 2000) {
                balance += amount;
                displayStatus();
            }

            //--------------------------------------------end of Deposit function
        } catch (Exception e) {
            System.out.println("Problem with the deposit function " + e);
        } finally {
            lock.unlock();
        }
    }


    // this function accepts an input integer amount value to withdraw from the shared account
    public static void Withdraw(int amount) {
        lock.lock();
        try {
            System.out.println("The withdraw is trying to remove $" + amount + " from the shared balance: $" + balance);

            // withdraw the input amount from the balance only if the current balance is greater than or equal to input amount
            // Withdraw doesn't wait until this condition is true (if the condition is false, skip withdrawing the amount), thus use if statements rather than waiting while loops
            // Call the displayStatus() function after you remove the amount and before release the lock
            // Implement the withdrawal functionality, as detailed above, in the area below

            if (balance >= amount) {
                balance -= amount;
                displayStatus();
            }

            //--------------------------------------------end of Withdraw function
        } catch (Exception e) {
            System.out.println("Problem with the withdraw function " + e);
        } finally {
            lock.unlock();
        }
    }


    // this is the main function
    // DO NOT CHANGE THIS SECTION
    public static void main(String[] args) {

        //create thread 1 to run function 1
        Thread thread1 = new Thread(() -> {
            while (true) {
                try {
                    Deposit(200 + (int) (Math.random() * 1000));    //random value between 200 and 1000$
                    Thread.sleep(200 + (int) (Math.random() * 500));   //random delay between 200 and 500
                } catch (Exception e) {
                    System.out.println("Problem with thread 1 " + e);
                }
            }
        });

        //create thread 2 to run function 2
        Thread thread2 = new Thread(() -> {
            while (true) {
                try {
                    Withdraw(200 + (int) (Math.random() * 1000));   //random value between 200 and 1000$
                    Thread.sleep(200 + (int) (Math.random() * 500));   //random delay between 200 and 500
                } catch (Exception e) {
                    System.out.println("Problem with thread 2 " + e);
                }
            }
        });

        //ask the threads to start running
        thread1.setName("deposit");
        thread1.start();
        thread2.setName("withdraw");
        thread2.start();
    }
}
