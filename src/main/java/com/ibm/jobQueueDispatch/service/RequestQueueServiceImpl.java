package com.ibm.jobQueueDispatch.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.jobQueueDispatch.model.QueueItem;
import com.ibm.jobQueueDispatch.repository.RequestQueueRepository;

@Service
public class RequestQueueServiceImpl implements RequestQueueService {
	
	@Autowired
	RequestQueueRepository repository;

	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public QueueItem getItem() {
		QueueItem topItem = repository.topItem();
		repository.setProcessingStatusFor(topItem.getId());
		return topItem;
	}

}
