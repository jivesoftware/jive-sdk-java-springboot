package com.jivesoftware.sdk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jivesoftware.sdk.dao.entity.JiveInstance;
import com.jivesoftware.sdk.dao.entity.WebhookInstance;

public interface WebhookInstanceRepository extends CrudRepository<WebhookInstance, Long> {

	@Query("SELECT i FROM WebhookInstance i WHERE webhookURI=:webhookURI")
    List<JiveInstance> findByURI(@Param("webhookURI") String webhookURI);
    
} // end interface