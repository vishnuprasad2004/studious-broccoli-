import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteCounter {

    private int count = 0;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writelock = lock.writeLock();

    public void increment() {
        writelock.lock();
        try {
            Thread.sleep(600);
            count++;
        } catch (InterruptedException e) {
            System.err.println(e.toString());
        } finally {
            writelock.unlock();
        }
    }
    public int getCount() {
        readLock.lock();
        try {
            return count;
        }  finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        ReadWriteCounter c = new ReadWriteCounter();

        // for reading the counter value
        Runnable read = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " read: " + c.getCount());
            }
        };

        // for incrementing the counter value
        Runnable write = () -> {
            for (int i = 0; i < 10; i++) {
                c.increment();
                System.out.println(Thread.currentThread().getName() + " incremented the count value");
            }
        };

        Thread writeThread = new Thread(write, "Writer thread");
        Thread readThread1 = new Thread(read, "Reader thread 1");
        Thread readThread2 = new Thread(read, "Reader thread 2");

        writeThread.start();
        readThread1.start();
        readThread2.start();

        writeThread.join();
        readThread1.join();
        readThread2.join();

        System.out.println("Final count: " + c.getCount());



    }
}
