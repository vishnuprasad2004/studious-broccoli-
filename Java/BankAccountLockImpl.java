import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount  {

    // ReentrantLock class implements Lock interface
    // ReentrantLock keeps track of count of lock acquisition -> prevents deadlock on the same thread
    // if 2 lock.lock() calls happen end-to-end, it just updates the count
    // cannot do many lock.unlock() calls tho, will raise -> IllegalMonitorStateException
    private final Lock lock = new ReentrantLock();
    private int balance = 1000;

    public void withdrawWithRetry(int amount) {
        boolean done = false;
        int i=0;
        int TOTAL_RETRIES_COUNT = 5;
        while(!done && i<= TOTAL_RETRIES_COUNT) { // keep trying

            System.out.println("Retry Count " + i + " " + Thread.currentThread().getName() + " attempting to withdraw " + amount);
            try {
                // try to acquire lock for 1 second
                if(lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                    try {
                        if(balance >= amount) {
                            System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal");
                            Thread.sleep(3000); // simulate processing
                            balance -= amount;
                            System.out.println(Thread.currentThread().getName() + " completed the withdrawal");
                        } else {
                            System.out.println(Thread.currentThread().getName() + " insufficient balance");
                        }
                        done = true; // success, exit loop
                    } finally {
                        lock.unlock();
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " couldn't acquire lock, retrying...");
                    Thread.sleep(500); // small delay before retrying
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            } finally {
                ++i;
                System.out.println(" ================ Current Balance : " + getBalance());
            }
        }
    }


    public void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);
        try {
            if(lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                if(balance >= amount) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal");
                        Thread.sleep(3000);
                        balance -= amount;
                        System.out.println(Thread.currentThread().getName() + " completed the withdrawal");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    } finally {
                        lock.unlock();
                    }

                } else {
                    System.out.println(Thread.currentThread().getName() + " insufficient balance");
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " couldn't acquire lock, please try later.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // still didn't fully understand this
        } finally {
            System.out.println(" ================ Current Balance : " + getBalance());
        }
        if(Thread.currentThread().isInterrupted()) {
            System.out.println(Thread.currentThread().getName() + " was interrupted.");
        }
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}

public class BankAccountLockImpl {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount(); // shared resource both threads will try to access this code
        // 2 threads act like 2 withdrawal actions simultaneously

        new Thread(() -> {
            acc.withdraw(100);
        }, "Vishnu").start();

        new Thread(() -> {
            acc.withdrawWithRetry(300);
        }, "Vishal").start();


    }

}

// OUTPUT:
// Vishnu attempting to withdraw 100
// Vishnu proceeding with withdrawal
// Retry Count 0 Vishal attempting to withdraw 300
// Vishal couldn't acquire lock, retrying...
//  ================ Current Balance : 1000
// Retry Count 1 Vishal attempting to withdraw 300
// Vishal couldn't acquire lock, retrying...
// Vishnu completed the withdrawal
//  ================ Current Balance : 900
//  ================ Current Balance : 900
// Retry Count 2 Vishal attempting to withdraw 300
// Vishal proceeding with withdrawal
// Vishal completed the withdrawal
//  ================ Current Balance : 600