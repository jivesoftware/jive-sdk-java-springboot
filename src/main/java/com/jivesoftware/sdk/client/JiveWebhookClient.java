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

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.jivesoftware.sdk.dao.entity.JiveInstance;
import com.jivesoftware.sdk.dao.entity.OAuthCredentials;
import com.jivesoftware.sdk.services.data.webhook.Webhook;

/**
 * Created by rrutan on 8/15/14.
 */
public class JiveWebhookClient extends JiveAPIClient {
    private static final Logger log = LoggerFactory.getLogger(JiveWebhookClient.class);

    private static final String WEBHOOK_PATH = "/api/core/v3/webhooks";
    private static final String WEBHOOK_PATH_ID = "/api/core/v3/webhooks/?";
    
    @Value("${jive.addon.url}")
    private String addonURL;
    
    @Value("${jive.addon.port}")
    private int addonPort;

    class WebhookCreate implements Serializable {

        @JsonSerialize(include=JsonSerialize.Inclusion.ALWAYS)
        private String events;
        @JsonSerialize(include=JsonSerialize.Inclusion.ALWAYS)
        private String callback;
        @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
        private String object;

        public WebhookCreate() {}
        public WebhookCreate(String events, String callback, String object) {
            this.events = events;
            this.callback = callback;
            this.object = object;
        } // end constructor

        public String getEvents() { return events;  }
        public void setEvents(String events) {
            this.events = events;
        } // end get/setEvents

        public String getCallback() {   return callback;    }
        public void setCallback(String callback) {
            this.callback = callback;
        } // end get/setCallback

        public String getObject() { return object;  }
        public void setObject(String object) {
            this.object = object;
        } // end get/setObject

    } // end inner-class

    public Webhook createWebhook(JiveInstance jiveInstance, OAuthCredentials oauthCredentials, String callbackURL, String placeURI, Webhook.ContentEvent... types) throws WebhookClientException {
        return createWebhook(jiveInstance,oauthCredentials,buildAbsoluteURIFromRelative(callbackURL), placeURI, types);
    } // end createWebhook

    public Webhook createWebhook(JiveInstance jiveInstance, OAuthCredentials oauthCredentials, URI callbackURI, String placeURI, Webhook.ContentEvent... types) throws WebhookClientException {

        if (jiveInstance == null) {
            throw new WebhookClientException("Invalid Jive Instance");
        } // end if

        Webhook webhook = null;

        try {
            URI apiCall = new URI(jiveInstance.getJiveUrl() + WEBHOOK_PATH);
            if (log.isDebugEnabled()) { log.debug("Calling [" + apiCall.toString() + "] ...");  }
            webhook = (Webhook)call(
                    HttpMethods.POST,
                    apiCall,
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_JSON,
                    Entity.entity(
                        new WebhookCreate(getWebhookEvents(types), callbackURI.toString(), placeURI),
                        MediaType.APPLICATION_JSON
                    ),
                    oauthCredentials,
                    null,
                    Webhook.class);
            if (webhook != null) {
                if (log.isDebugEnabled()) { log.debug("Webhook["+"TODO:INSERT WEBHOOK ID"+"] Create Successful.");  }
            } // end if
        } catch (JiveClientException jce) {
            log.error("Unable to Create Webhook["+callbackURI+","+placeURI);
            throw new WebhookClientException("Error Creating Webhook",jce);
        } catch (URISyntaxException use) {
            log.error("Unexpected Exception, should not happen");
            throw new WebhookClientException("Unexpected Exception",use);
        } // end try/catch

        return webhook;
    } // end createWebhook

    public Webhook createWebhook(JiveInstance jiveInstance, String callbackURL, Webhook.SystemEvent... types) throws WebhookClientException {
        return createWebhook(jiveInstance,buildAbsoluteURIFromRelative(callbackURL),types);
    } // end createWebhook

    public Webhook createWebhook(JiveInstance jiveInstance, URI callbackURI, Webhook.SystemEvent... types) throws WebhookClientException {

        if (jiveInstance == null) {
            throw new WebhookClientException("Invalid Jive Instance");
        } // end if

        Webhook webhook = null;

        try {
            URI apiCall = new URI(jiveInstance.getJiveUrl() + WEBHOOK_PATH);
            if (log.isDebugEnabled()) { log.debug("Calling [" + apiCall.toString() + "] ...");  }
            webhook = (Webhook)call(
                    HttpMethods.POST,
                    apiCall,
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_JSON,
                    Entity.entity(
                            new WebhookCreate(getWebhookEvents(types), callbackURI.toString(), null),
                            MediaType.APPLICATION_JSON
                    ),
                    jiveInstance.getCredentials(),
                    null,
                    Webhook.class);
            if (webhook != null) {
                if (log.isDebugEnabled()) { log.debug("Webhook["+"TODO:INSERT WEBHOOK ID"+"] Create Successful.");  }
            } // end if
        } catch (JiveClientException jce) {
            log.error("Unable to Create Webhook["+callbackURI+"]");
            throw new WebhookClientException("Error Creating Webhook",jce);
        } catch (URISyntaxException use) {
            log.error("Unexpected Exception, should not happen");
            throw new WebhookClientException("Unexpected Exception",use);
        } // end try/catch

        return webhook;
    } // end createWebhook

    public void deleteWebhook(JiveInstance jiveInstance, Webhook webhook) throws WebhookClientException {

        if (jiveInstance == null) {
            throw new WebhookClientException("Invalid Jive Instance");
        } // end if

        if (webhook == null) {
            throw new WebhookClientException("Invalid Webhook Instance");
        } // end if

        try {
            URI apiCall = new URI(jiveInstance.getJiveUrl() + WEBHOOK_PATH_ID.replace("?",String.valueOf("TODO:INSERT WEBHOOK ID")));
            if (log.isDebugEnabled()) { log.debug("Calling [" + apiCall.toString() + "] ...");  }
            Webhook newWebhook = (Webhook)call(
                    HttpMethods.DELETE,
                    apiCall,
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_JSON,
                    webhook,
                    jiveInstance.getCredentials(),
                    null,
                    Webhook.class);
            if (newWebhook == null) {
                //TODO:  validate THIS SHOULD BE NULL IF ITS DELETED, YES?
                if (log.isDebugEnabled()) { log.debug("Webhook["+"TODO:INSERT WEBHOOK ID"+"] Deleted Successfully.");  }
            } // end if
        } catch (JiveClientException jce) {
            log.error("Unable to Update Webhook["+"TODO:INSERT WEBHOOK ID"+"]");
            throw new WebhookClientException("Error Creating Webhook",jce);
        } catch (URISyntaxException use) {
            log.error("Unexpected Exception, should not happen");
            throw new WebhookClientException("Unexpected Exception",use);
        } // end try/catch

    } // end deleteWebhook

    public Webhook updateWebhook(JiveInstance jiveInstance, Webhook webhook) throws WebhookClientException {

        if (jiveInstance == null) {
            throw new WebhookClientException("Invalid Jive Instance");
        } // end if

        if (webhook == null) {
            throw new WebhookClientException("Invalid Webhook Instance");
        } // end if

        Webhook newWebhook = null;

        try {
            URI apiCall = new URI(jiveInstance.getJiveUrl() + WEBHOOK_PATH_ID.replace("?",String.valueOf("TODO:INSERT WEBHOOK ID")));
            if (log.isDebugEnabled()) { log.debug("Calling [" + apiCall.toString() + "] ...");  }
            newWebhook = (Webhook)call(
                    HttpMethods.PUT,
                    apiCall,
                    MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_JSON,
                    webhook,
                    jiveInstance.getCredentials(),
                    null,
                    Webhook.class);
            if (newWebhook != null) {
                if (log.isDebugEnabled()) { log.debug("Webhook["+"TODO:INSERT WEBHOOK ID"+"] => ["+"TODO:INSERT WEBHOOK ID"+"] Update Successful.");  }
            } // end if
        } catch (JiveClientException jce) {
            log.error("Unable to Update Webhook["+"TODO:INSERT WEBHOOK ID"+"]");
            throw new WebhookClientException("Error Creating Webhook",jce);
        } catch (URISyntaxException use) {
            log.error("Unexpected Exception, should not happen");
            throw new WebhookClientException("Unexpected Exception",use);
        } // end try/catch

        return newWebhook;
    } // end updateWebhook

    private URI buildAbsoluteURIFromRelative(String url) throws WebhookClientException {
        if (url != null) {
            if (url.indexOf("/") == 0) {
                return UriBuilder.fromUri(addonURL).port(addonPort).path(url).build();
            } // end if

            try {
                return new URI(url);
            } catch (URISyntaxException use) {
                throw new WebhookClientException("Invalid URI",use);
            } // end try/catch

        } // end if
        throw new WebhookClientException("Invalid URI [null]");
    } // end buildAbsoluteURIFromRelative

    private String getWebhookEvents(Object[] webhookTypes) {
        if (webhookTypes != null && webhookTypes.length > 0) {
            StringBuffer sbuf = new StringBuffer();
            for (Object type : webhookTypes) {
                sbuf.append(",").append(type.toString());
            } // end for each
            return sbuf.substring(1);
        } // end if
        return "";
    } // end getWebhookEvents

} // end class
