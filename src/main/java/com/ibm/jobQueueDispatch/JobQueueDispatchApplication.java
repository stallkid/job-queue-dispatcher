package com.ibm.jobQueueDispatch;

import java.sql.Timestamp;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.ibm.jobQueueDispatch.model.QueueItem;
import com.ibm.jobQueueDispatch.repository.RequestQueueRepository;

@SpringBootApplication
@EnableAsync
public class JobQueueDispatchApplication implements CommandLineRunner {
	
	@Autowired
	private RequestQueueRepository repository;
	
	@Bean("threadPoolTaskExecutor")
    public TaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(1000);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setThreadNamePrefix("Async-");
        return executor;
    }

	public static void main(String[] args) {
		SpringApplication.run(JobQueueDispatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		QueueItem obj1 = new QueueItem(null, "test1", "PENDING", timestamp, timestamp);
		QueueItem obj2 = new QueueItem(null, "test2", "PENDING", timestamp, timestamp);
		QueueItem obj3 = new QueueItem(null, "test3", "PENDING", timestamp, timestamp);
		QueueItem obj4 = new QueueItem(null, "test4", "PENDING", timestamp, timestamp);
		QueueItem obj5 = new QueueItem(null, "test5", "PENDING", timestamp, timestamp);
		QueueItem obj6 = new QueueItem(null, "test6", "PENDING", timestamp, timestamp);
		QueueItem obj7 = new QueueItem(null, "test7", "PENDING", timestamp, timestamp);
		QueueItem obj8 = new QueueItem(null, "test8", "PENDING", timestamp, timestamp);
		QueueItem obj9 = new QueueItem(null, "test9", "PENDING", timestamp, timestamp);
		QueueItem obj10 = new QueueItem(null, "test10", "PENDING", timestamp, timestamp);
		
		repository.saveAll(Arrays.asList(obj1, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10));
		
	}

}
