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

package com.jivesoftware.sdk.client.oauth;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Zvoykish
 * Date: 18/7/13
 * Time: 7:36 PM
 */
public class JiveOAuthResponse {
    private static final Logger log = LoggerFactory.getLogger(JiveOAuthResponse.class);

    public final static String SCOPE = "scope";
    public final static String TOKEN_TYPE = "token_type";
    public final static String EXPIRES_IN = "expires_in";
    public final static String ACCESS_TOKEN = "access_token";
    public final static String REFRESH_TOKEN = "refresh_token";

    private String scope;
    private String token_type;
    private long expires_in;
    private String access_token;
    private String refresh_token;

    @JsonCreator
    public JiveOAuthResponse(
            @JsonProperty(SCOPE) String scope,
            @JsonProperty(TOKEN_TYPE) String tokenType,
            @JsonProperty(EXPIRES_IN) long expiresIn,
            @JsonProperty(ACCESS_TOKEN) String accessToken,
            @JsonProperty(REFRESH_TOKEN) String refreshToken) {
        this.scope = scope;
        this.token_type = tokenType;
        this.expires_in = expiresIn;
        this.access_token = accessToken;
        this.refresh_token = refreshToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getTokenType() {
        return token_type;
    }

    public void setTokenType(String token_type) {
        this.token_type = token_type;
    }

    public long getExpiresIn() {
        return expires_in;
    }

    public void setExpiresIn(long expires_in) {
        this.expires_in = expires_in;
    }

    public String getAccessToken() {
        return access_token;
    }

    public void setAccessToken(String access_token) {
        this.access_token = access_token;
    }

    public String getRefreshToken() {
        return refresh_token;
    }

    public void setRefreshToken(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JiveOAuthResponse that = (JiveOAuthResponse) o;

        if (expires_in != that.expires_in) return false;
        if (access_token != null ? !access_token.equals(that.access_token) : that.access_token != null) return false;
        if (refresh_token != null ? !refresh_token.equals(that.refresh_token) : that.refresh_token != null)
            return false;
        if (scope != null ? !scope.equals(that.scope) : that.scope != null) return false;
        if (token_type != null ? !token_type.equals(that.token_type) : that.token_type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = scope != null ? scope.hashCode() : 0;
        result = 31 * result + (token_type != null ? token_type.hashCode() : 0);
        result = 31 * result + (int) (expires_in ^ (expires_in >>> 32));
        result = 31 * result + (access_token != null ? access_token.hashCode() : 0);
        result = 31 * result + (refresh_token != null ? refresh_token.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "JiveOAuthResponse{" +
                "scope='" + scope + '\'' +
                ", token_type='" + token_type + '\'' +
                ", expires_in=" + expires_in +
                ", access_token='" + access_token + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                '}';
    }

} //end class
