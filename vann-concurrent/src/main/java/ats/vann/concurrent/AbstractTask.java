package ats.vann.concurrent;

import java.util.concurrent.CountDownLatch;

public abstract class AbstractTask implements Runnable, Task {
	private CountDownLatch taskGroup;

	public CountDownLatch getTaskGroup() {
		return taskGroup;
	}

	public void setTaskGroup(CountDownLatch taskGroup) {
		this.taskGroup = taskGroup;
	}

	@Override
	public final void run() {
		try {
			execute();
		} finally {
			if (taskGroup != null) {
				taskGroup.countDown();
			}
		}
	}

	public void recycle() {
		this.taskGroup = null;
	}

}
