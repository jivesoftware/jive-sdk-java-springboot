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

package com.jivesoftware.sdk.client;

import java.util.Map;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.annotate.JsonProperty;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jivesoftware.sdk.client.filter.DebugClientResponseFilter;
import com.jivesoftware.sdk.client.filter.StripAllowIllegalResourceCallFilter;

import jersey.repackaged.com.google.common.collect.Maps;

/**
 * Created by rrutan on 2/13/14.
 */
public class BaseJiveClient {
    private static final Logger log = LoggerFactory.getLogger(BaseJiveClient.class);

    public enum HttpMethods {
        GET, POST, UPDATE, DELETE, PATCH, PUT, HEAD;
    } // end HttpMethods

    public static final String HEADER_JIVE_RUN_AS = "X-Jive-Run-As";

    protected Client buildClient() {
        Client client = ClientBuilder.newClient();

        client.register(StripAllowIllegalResourceCallFilter.class);

        if (log.isDebugEnabled()) {
            client.register(DebugClientResponseFilter.class);
        } // end if

        client.register(JacksonFeature.class);

        return client;
    } // end buildClient

    protected AsyncInvoker getAsyncInvoker(WebTarget target, String requestContentType,
                              JiveAuthorizationSupport authorizationSupport, JiveRunAs runAs, Map<String,String> additionalHeaders) {

        if (target == null) {
            return null;
        } // end if

        Invocation.Builder builder = target.request(requestContentType);
        if (authorizationSupport != null) {
            builder.header(HttpHeaders.AUTHORIZATION, authorizationSupport.getAuthorizationHeader());
        } // end if

        if (runAs != null) {
            if (log.isDebugEnabled()) { log.trace("Adding "+HEADER_JIVE_RUN_AS+" to Request ..."); }
            builder.header(HEADER_JIVE_RUN_AS,runAs.getStrategy().name() + " " + runAs.getKey());
        } // end if

        if (additionalHeaders != null) {
            if (log.isDebugEnabled()) { log.debug("Adding Additional Headers ..."); }
            for (String key : additionalHeaders.keySet()) {
                if (log.isTraceEnabled()) { log.trace(key+" : "+additionalHeaders.get(key)); }
                builder.header(key,additionalHeaders.get(key));
            } // end for
        } // end if

        return builder.async();
    } // end initTarget


    class JiveBasicAuthorization implements JiveBasicAuthSupport {

        private String username;
        private String password;

        JiveBasicAuthorization(String username, String password) {
            this.username = username;
            this.password = password;
        } // end constructor

        @Override
        public String getAuthorizationHeader() {
            StringBuffer sbuf = new StringBuffer(AUTHORIZATION_BASIC_PREFIX);
            sbuf.append(new String(Base64.encodeBase64((username + ":" + password).getBytes())));
            return sbuf.toString();
        } // end getAuthorizationHeader

    } // end class

    public JiveBasicAuthorization getBasicAuth(String username, String password) {
        return new JiveBasicAuthorization(username,password);
    } //end getBasicAuth

    class DataBlock {
        @JsonProperty("data")
        private Object data;
        @JsonProperty("message")
        private Map<String,Object> message;
        @JsonProperty("status")
        private Map<String,Object> status;

        DataBlock() {
            this.message = Maps.newHashMap();
            this.status = Maps.newHashMap();
        }

        DataBlock(Object data) {
            this();
            this.data = data;
        } // end constructor

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Map<String, Object> getMessage() {
            return message;
        }

        public void setMessage(Map<String, Object> message) {
            this.message = message;
        }

        public Map<String, Object> getStatus() {
            return status;
        }

        public void setStatus(Map<String, Object> status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "DataBlock{" +
                    "data=" + data +
                    ", message=" + message +
                    ", status=" + status +
                    '}';
        }
    } // end class



} // end class
