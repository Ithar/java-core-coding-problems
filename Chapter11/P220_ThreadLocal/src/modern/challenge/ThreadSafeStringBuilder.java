package modern.challenge;

import java.util.Random;
import java.util.logging.Logger;

class ThreadSafeStringBuilder implements Runnable {

    private static final Logger logger = Logger.getLogger(ThreadSafeStringBuilder.class.getName());

    private static final ThreadLocal<StringBuilder> threadSafe = ThreadLocal.withInitial(() -> {
        return new StringBuilder("Local-thread-safe"); // Creates a local instance only the current thread has access to
    });


    @Override
    public void run() {

        try {
            Thread.sleep(new Random().nextInt(2000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Here we got the String Builder, but each local Thread gets a local copy
        threadSafe.get().append(Thread.currentThread().getName());

        logger.info(() -> " FINISH -> " + Thread.currentThread().getName()+ " [" + threadSafe.get() + "]");

        threadSafe.set(null);

    }
}
