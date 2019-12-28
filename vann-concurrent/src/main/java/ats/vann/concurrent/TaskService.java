package ats.vann.concurrent;

import java.util.List;

public interface TaskService extends AutoCloseable {

	void runTask(AbstractTask task);

	void runTasks(List<? extends AbstractTask> tasks);

}
