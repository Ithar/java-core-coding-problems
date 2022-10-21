package modern.challenge;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ThreadSafeStringBuilder safe = new ThreadSafeStringBuilder();

        for (int i = 0; i < 2; i++) {
            new Thread(safe, "[Example1-thread-" + i+"]").start();
            // INFO:  FINISH -> [Example1-thread-0] [Local-thread-safe[Example1-thread-0]]
            // INFO:  FINISH -> [Example1-thread-1] [Local-thread-safe[Example1-thread-1]]
        }

        UnsafeStringBuilder unSafe = new UnsafeStringBuilder();

        for (int i = 0; i < 2; i++) {
            new Thread(unSafe, "[Example2-thread-" + i+"]").start();
            // INFO:  FINISH -> [Example2-thread-1] [Local-thread-unsafe[Example2-thread-1]]
            // INFO:  FINISH -> [Example2-thread-0] [Local-thread-unsafe[Example2-thread-1][Example2-thread-0]]
        }
    }

}
