package ats.vann.concurrent;

import java.util.List;
import java.util.concurrent.ThreadFactory;

class FireAndForgetTaskExecutor extends AbstractTaskExecutor {

	public FireAndForgetTaskExecutor(ThreadFactory threadFactory, int threadCount) {
		super(threadFactory, threadCount);
	}

	@Override
	protected void doRunTask(AbstractTask task) {
		getExecutorService().submit(task);
	}

	@Override
	protected void doRunTasks(List<? extends AbstractTask> tasks) {
		for (AbstractTask task : tasks) {
			doRunTask(task);
		}

	}

}
