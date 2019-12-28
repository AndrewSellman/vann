package ats.vann.concurrent;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class TaskPool<T extends AbstractTask> implements AutoCloseable {
	private final GenericObjectPool<T> pool;
	Logger logger = LoggerFactory.getLogger(TaskPool.class);

	public TaskPool(GenericObjectPool<T> pool) {
		this.pool = pool;
	}

	public List<T> borrow(int howManyTasks) throws Exception {
		List<T> borrowedTasks = getNewBorrowList(howManyTasks);
		for (int i = 0; i < howManyTasks; i++) {
			borrowedTasks.add(pool.borrowObject());
		}
		return borrowedTasks;
	}

	public void recycle(List<T> tasks) {
		if (tasks == null) {
			return;
		}

		for (T task : tasks) {
			recycle(task);
		}
	}

	private void recycle(T task) {
		pool.returnObject(task);
	}

	@Override
	public void close() {
		logger.info("Closing pool {} instance {}...", this.getClass(), this);
		pool.close();
		logger.info("Closed pool {} instance {}.", this.getClass(), this);
	}

	public long getBorrowedCount() {
		return pool.getBorrowedCount();
	}

	public long getRecycledCount() {
		return pool.getReturnedCount();
	}

	public void clear() {
		pool.clear();
	}

	protected abstract List<T> getNewBorrowList(int howManyTasks);

}
