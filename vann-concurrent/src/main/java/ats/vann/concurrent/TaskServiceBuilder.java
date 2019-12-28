package ats.vann.concurrent;

import java.util.concurrent.ThreadFactory;

public class TaskServiceBuilder {
	private Priority priority;
	private boolean fireAndForget;
	private int threadCount;

	public TaskServiceBuilder() {
		normalPriority().waitForCompletion();
	}

	public TaskService build() {
		ThreadFactory threadFactory = new PrioritizedThreadFactory(priority);
		AbstractTaskExecutor taskExecutor = buildTaskExecutor(threadFactory);
		return new TaskServiceImpl(taskExecutor);
	}

	public TaskServiceBuilder waitForCompletion() {
		fireAndForget = false;
		return this;
	}

	public TaskServiceBuilder fireAndForget() {
		fireAndForget = true;
		return this;
	}

	public TaskServiceBuilder normalPriority() {
		priority = Priority.NORMAL;
		return this;
	}

	public TaskServiceBuilder lowPriority() {
		priority = Priority.LOW;
		return this;
	}

	public TaskServiceBuilder highPriority() {
		priority = Priority.HIGH;
		return this;
	}

	public TaskServiceBuilder setThreadCount(int threadCount) {
		this.threadCount = threadCount;
		return this;
	}

	private AbstractTaskExecutor buildTaskExecutor(ThreadFactory threadFactory) {
		if (fireAndForget) {
			return new FireAndForgetTaskExecutor(threadFactory, threadCount);
		} else {
			return new WaitForCompletionTaskExecutor(threadFactory, threadCount);
		}
	}

}
