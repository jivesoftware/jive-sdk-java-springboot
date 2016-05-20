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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Zvoykish
 * Date: 17/7/13
 * Time: 4:20 PM
 */
public class JiveOAuthRequest {
    private static final Logger log = LoggerFactory.getLogger(JiveOAuthRequest.class);

    public static final String CODE = "code";
    public static final String GRANT_TYPE = "grant_type";
    public static final String CLIENT_ID = "client_id";
    public static final String AUTHORIZATION_CODE = "authorization_code";
    private String jiveInstanceUrl;
    private String clientId;
    private String clientSecret;
    private String code;

    public JiveOAuthRequest(String jiveInstanceUrl, String clientId, String clientSecret, String code) {
        this.jiveInstanceUrl = jiveInstanceUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.code = code;
    }

    public String getJiveInstanceUrl() {
        return jiveInstanceUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getCode() {
        return code;
    }

    public Map<String, String> getBodyParams() {
        Map<String, String> map = new HashMap<String, String>();
        map.put(GRANT_TYPE, AUTHORIZATION_CODE);
        map.put(CODE, code);
        map.put(CLIENT_ID, clientId);
        return map;
    }
}
