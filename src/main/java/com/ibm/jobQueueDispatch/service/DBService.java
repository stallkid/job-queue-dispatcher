package com.ibm.jobQueueDispatch.service;

import java.sql.Timestamp;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.jobQueueDispatch.model.QueueItem;
import com.ibm.jobQueueDispatch.repository.RequestQueueRepository;

@Service
public class DBService {
	
	@Autowired
	private RequestQueueRepository repository;
	
	public void instantiateTestDatabase() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		QueueItem obj = new QueueItem(null, "test", "PENDING", timestamp, timestamp);
		
		repository.saveAll(Arrays.asList(obj, obj, obj, obj));
		
	}

}
