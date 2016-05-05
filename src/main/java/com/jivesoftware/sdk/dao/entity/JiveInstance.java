package com.jivesoftware.sdk.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class JiveInstance {
    @Id
    @GeneratedValue
    @Column(name = "jive_instance_id")
    private Long id;

    @Column(unique=true)
    private String tenantId = null;
    private String jiveSignatureURL = null;
    private String timestamp = null;
    private String jiveUrl = null;
    private String jiveSignature = null;
    private String clientSecret = null;
    private String clientId = null;
    private String code = null;
    private String scope = null;
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name="jiveurl", column=@Column(name="oauthUrl")),
        @AttributeOverride(name="accessToken", column=@Column(name="oauthAccessToken")),
        @AttributeOverride(name="refreshToken", column=@Column(name="oauthRefreshToken"))
    })
    private OAuthCredentials credentials = null;

    public JiveInstance() {
        credentials = new OAuthCredentials();
    } // end constructor

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getJiveSignatureURL() {
        return jiveSignatureURL;
    }

    public void setJiveSignatureURL(String jiveSignatureURL) {
        this.jiveSignatureURL = jiveSignatureURL;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getJiveUrl() {
        return jiveUrl;
    }

    public void setJiveUrl(String jiveUrl) {
        this.jiveUrl = jiveUrl;
    }

    public String getJiveSignature() {
        return jiveSignature;
    }

    public void setJiveSignature(String jiveSignature) {
        this.jiveSignature = jiveSignature;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public OAuthCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(OAuthCredentials credentials) {
        this.credentials = credentials;
    }

	@Override
	public String toString() {
		return "JiveInstance [id=" + id + ", tenantId=" + tenantId + ", jiveUrl=" + jiveUrl + ", jiveSignature=" + jiveSignature + ", clientId=" + clientId + "]";
	}

} // end class
