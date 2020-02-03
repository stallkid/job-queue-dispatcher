package com.ibm.jobQueueDispatch.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ibm.jobQueueDispatch.model.QueueItem;

@Repository
public interface RequestQueueRepository extends JpaRepository<QueueItem, Integer> {
	
	@Query(value = "SELECT * FROM request_queue WHERE status='PENDING' ORDER BY created_at ASC LIMIT 1 FOR UPDATE SKIP LOCKED;", nativeQuery = true)
    QueueItem topItem();
	
	@Modifying
    @Transactional
    @Query(value = "update request_queue set status = 'PROCESSING', last_updated = now() where id = ?1 and status='PENDING'", nativeQuery= true)
    void setProcessingStatusFor(Integer id);

}
