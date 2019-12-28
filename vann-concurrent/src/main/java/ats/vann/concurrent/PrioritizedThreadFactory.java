package ats.vann.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

class PrioritizedThreadFactory implements ThreadFactory {
    private final AtomicInteger counter = new AtomicInteger(1);
    private final String threadNamePrefix;
    private final int threadPriority;

    public PrioritizedThreadFactory(Priority priority) {
        threadNamePrefix = priority.getDescription() + "-priority-thread-";
        threadPriority = priority.getThreadPriority();
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(null, r, getThreadName(), 0);
        t.setDaemon(false);
        t.setPriority(threadPriority);
        return t;
    }

    private String getThreadName() {
        return threadNamePrefix + counter.getAndIncrement();
    }

}
