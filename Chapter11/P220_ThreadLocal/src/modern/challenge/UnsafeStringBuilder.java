package modern.challenge;

import java.util.Random;
import java.util.logging.Logger;

class UnsafeStringBuilder implements Runnable {

    private static final Logger logger = Logger.getLogger(ThreadSafeStringBuilder.class.getName());

    private static final StringBuilder threadLocal = new StringBuilder("Local-thread-unsafe");

    @Override
    public void run() {

        try {
            Thread.sleep(new Random().nextInt(2000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Here we got the String Builder, but each local Thread gets a local copy
        threadLocal.append(Thread.currentThread().getName());

        logger.info(() -> " FINISH -> " + Thread.currentThread().getName()+ " [" + threadLocal + "]");
    }

}
