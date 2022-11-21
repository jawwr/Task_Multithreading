import java.util.concurrent.atomic.AtomicInteger;

public class Lucky {
    static AtomicInteger luckyX = new AtomicInteger(0);
    static AtomicInteger count = new AtomicInteger(0);

    static class LuckyThread extends Thread {
        @Override
        public void run() {
            while (luckyX.get() < 999999) {
                final int x = luckyX.incrementAndGet();
                if (((x % 10) + (x / 10) % 10 + (x / 100) % 10 == (x / 1000) % 10 + (x / 10000) % 10 + (x / 100000) % 10) && x <= 999999) {
                    System.out.println(Thread.currentThread().getName() + ": " + x);

                    count.incrementAndGet();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new LuckyThread();
        Thread t2 = new LuckyThread();
        Thread t3 = new LuckyThread();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + count.get());
    }
}