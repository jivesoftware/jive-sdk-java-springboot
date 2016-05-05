package com.jivesoftware.sdk.dao.entity;

public class OAuthCredentials {
	
    private String url;

    private String accessToken;

    private String refreshToken;

    public OAuthCredentials() {
        url = "";
        accessToken = "";
        refreshToken = "";
    }

    public OAuthCredentials(String url, String accessToken, String refreshToken) {
        this.url = url;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
    
    public String getUrl() {
        return url;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
