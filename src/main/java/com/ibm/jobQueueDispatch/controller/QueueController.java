package com.ibm.jobQueueDispatch.controller;


import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.jobQueueDispatch.model.QueueItem;
import com.ibm.jobQueueDispatch.service.RequestQueueService;

@RestController
@RequestMapping("/QueueController")
public class QueueController {
	
	private static final Logger logger = LoggerFactory.getLogger(RequestQueueService.class);
	
	@Autowired
	private RequestQueueService service;
	
	@Async("threadPoolTaskExecutor")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> dispatchJob() throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			QueueItem item = service.getItem();
			logger.info(("Task number " + i + ": Payload " + item.getPayload()));
			TimeUnit.SECONDS.sleep(5);
		}
		return ResponseEntity.ok().body("Done");
	}

}
