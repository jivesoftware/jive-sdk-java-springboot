package com.jivesoftware.sdk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jivesoftware.sdk.dao.entity.TileInstance;

public interface TileInstanceDAO extends CrudRepository<TileInstance, Long> {

	@Query("SELECT ti FROM TileInstance ti WHERE globalTileInstanceId=:globalTileInstanceId")
    TileInstance findByGlobalTileInstanceId(@Param("globalTileInstanceId") String globalTileInstanceId);
	
	@Query("SELECT ti FROM TileInstance ti WHERE tenantId=:tenantId AND tileDefName=:tileDefName")
    List<TileInstance> findByTenandIdAndTileDefName(@Param("tenantId") String tenantId, @Param("tileDefName") String tileDefName);
	
	@Query("SELECT ti FROM TileInstance ti WHERE tenantId=:tenantId")
    List<TileInstance> findByTenantId(@Param("tenantId") String tenantId);
	
    
} // end interface