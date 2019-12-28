package ats.vann.concurrent;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

class TaskServiceImpl implements TaskService {
	private final AbstractTaskExecutor taskExecutor;
	Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

	public TaskServiceImpl(AbstractTaskExecutor tasksExecutor) {
		this.taskExecutor = tasksExecutor;
	}

	@Override
	public void runTasks(List<? extends AbstractTask> tasks) {
		taskExecutor.runTasks(tasks);
	}

	@Override
	public void runTask(AbstractTask task) {
		taskExecutor.runTask(task);
	}

	@PostConstruct
	public void postConstruct() {
		logger.info("{} instance {} was constructed.", this.getClass(), this);
	}


	@PreDestroy
	@Override
	public void close() {
		logger.info("Closing {} instance {}...", this.getClass(), this);
		taskExecutor.close();
		logger.info("Closed {} instance {}.", this.getClass(), this);
	}

}
