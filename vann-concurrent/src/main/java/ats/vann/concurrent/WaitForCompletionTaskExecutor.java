package ats.vann.concurrent;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;

class WaitForCompletionTaskExecutor extends AbstractTaskExecutor {

	public WaitForCompletionTaskExecutor(ThreadFactory threadFactory, int threadCount) {
		super(threadFactory, threadCount);
	}

	@Override
	protected void doRunTask(AbstractTask task) {
		CountDownLatch taskGroup = task.getTaskGroup();
		getExecutorService().submit(task);
		await(taskGroup);
	}

	@Override
	protected void doRunTasks(List<? extends AbstractTask> tasks) {
		CountDownLatch taskGroup = tasks.get(0).getTaskGroup();
		for (AbstractTask task : tasks) {
			getExecutorService().submit(task);
		}
		await(taskGroup);
	}

	private void await(CountDownLatch taskGroup) {
		try {
			taskGroup.await();
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
			throw new IllegalStateException("Unable to complete tasks.", ie);
		}
	}

}
