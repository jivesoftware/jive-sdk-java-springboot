/*
 *
 *  * Copyright 2013 Jive Software
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *       http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 */

package com.jivesoftware.sdk.dao.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by rrutan on 1/30/14.
 */
@Entity
@Table(name="tileInstance")
public class TileInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tile_instance_id")
    private Long id;

    private String code;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, String> config = new HashMap<String, String>();

	/**
	 * The url to push tile data.
	 */
    private String jivePushUrl;

	/**
	 * The jiveURL for this jive instance.
	 */
    private String jiveUrl;

	/**
	 * The unique identifier for this jive instance.
	 */
    private String tenantId;
    private String itemType;
    private String tileDefName;

	/**
	 * A globally unique identifier for this tile instance
	 */
	private String globalTileInstanceId;

	/**
	 * Additional credentials if needed.
	 */
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="url", column=@Column(name="tileUrl")),
        @AttributeOverride(name="accessToken", column=@Column(name="tileAccessToken")),
        @AttributeOverride(name="refreshToken", column=@Column(name="tileRefreshToken"))
    })
    private OAuthCredentials credentials;


    public TileInstance() {
        credentials = new OAuthCredentials();
    } // end constructor

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<String, String> getConfig() {
        return config;
    }

    public void setConfig(Map<String, String> config) {
        this.config = config;
    }

    public String getJivePushUrl() {
        return jivePushUrl;
    }

    public void setJivePushUrl(String jivePushUrl) {
        this.jivePushUrl = jivePushUrl;
    }

    public String getJiveUrl() {
        return jiveUrl;
    }

    public void setJiveUrl(String jiveUrl) {
        this.jiveUrl = jiveUrl;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getTileDefName() {
        return tileDefName;
    }

    public void setTileDefName(String tileDefName) {
        this.tileDefName = tileDefName;
    }

	public String getGlobalTileInstanceId() {
		return globalTileInstanceId;
	}

	public OAuthCredentials getCredentials() {
		return credentials;
	}

	public void setCredentials(OAuthCredentials credentials) {
		this.credentials = credentials;
	}
	
	
} // end class