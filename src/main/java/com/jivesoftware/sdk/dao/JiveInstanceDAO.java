package com.jivesoftware.sdk.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jivesoftware.sdk.dao.entity.JiveInstance;

public interface JiveInstanceDAO extends CrudRepository<JiveInstance, Long> {

	@Query("SELECT i FROM JiveInstance i WHERE tenant_id=:tenantId")
    JiveInstance findByTenantId(@Param("tenantId") String tenantId);
    
} // end interface