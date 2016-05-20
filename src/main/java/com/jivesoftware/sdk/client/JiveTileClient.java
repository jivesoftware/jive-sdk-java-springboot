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

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.inject.Singleton;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jivesoftware.sdk.api.tile.data.ActivityEntry;
import com.jivesoftware.sdk.api.tile.data.ActivityPushTile;
import com.jivesoftware.sdk.client.oauth.JiveOAuthClient;
import com.jivesoftware.sdk.dao.TileInstanceRepository;
import com.jivesoftware.sdk.dao.entity.TileInstance;
import com.jivesoftware.sdk.util.JiveSDKUtils;

/**
 *
 * NOTE:  HAD TO MOVE AWAY FROM GENERICS SO I CAN DO INJECTIONS
 * Created by rrutan on 2/3/14.
 */
@Component
@Singleton
public class JiveTileClient extends BaseJiveClient {
    private static final Logger log = LoggerFactory.getLogger(JiveTileClient.class);

    @Autowired
    private TileInstanceRepository tileInstanceRepository;

    @Autowired
    private JiveOAuthClient jiveOAuthClient;

    public JiveTileClient() {
        if (log.isTraceEnabled()) { log.trace("constructor called..."); }
    } // end constructor

    /**
     * Update data in tile instance.
     */
    public void pushData(TileInstance tileInstance, Object data) throws JiveClientException {
        if (log.isTraceEnabled()) { log.trace("pushData called..."); }
        if (log.isDebugEnabled()) { log.debug("pushData => ["+tileInstance.getJivePushUrl()+"]"); }

        try {
            DataBlock dataBlock = new DataBlock(data);
            makeRequest(tileInstance, tileInstance.getJivePushUrl(), HttpMethod.PUT, dataBlock, String.class);
        }
        catch (TargetGoneException e) {
            log.info("Received 410 from data push request, assuming tile deleted in Jive and removing it, instance url: " + tileInstance.getJivePushUrl());
            tileInstanceRepository.delete(tileInstance);
        }
    }

    public Object fetchData(TileInstance tileInstance, Class clazz) throws JiveClientException {

        initAccessTokens(tileInstance);

        Client client = buildClient();

        //TODO: CONFIRM THE URL NEEDED TO FETCH DATA, PRETTY SURE THIS ISNT jivePushURL
        WebTarget target = client.target(tileInstance.getJivePushUrl());

        AsyncInvoker asyncInvoker =  target.request(MediaType.APPLICATION_JSON_TYPE)
                                           .header(HttpHeaders.AUTHORIZATION,tileInstance.getCredentials().getAuthorizationHeader())
                                           .async();

        Future<Response> responseFuture = asyncInvoker.get();

        Response response = null;

        try {
            response = responseFuture.get();
            //TODO: CHECK THAT THIS IS THE CORRECT RESPONSE CODE
            if (response.getStatus() == 204) {
                //TODO: CONFIRM THE URL NEEDED TO FETCH DATA, PRETTY SURE THIS ISNT jivePushURL
                if (log.isInfoEnabled()) { log.info("Successful Fetch ["+tileInstance.getJivePushUrl()+"]"); }
                //TODO: EXPORT TO UTILITY CLASS
                //TODO: NEED TO USE JACKSON AND CONVERT ENTITY TO ITS ORIGINAL OBJECT TYPE?
                return response.getEntity();
            } // end if
        } catch (BadRequestException bre) {
            log.error("Error Fetching Data From Tile [" + tileInstance.getJivePushUrl() + "]", bre);
            throw JiveClientException.buildException("Error Fetching Data to Tile [" + tileInstance.getJivePushUrl() + "]",bre,tileInstance,null,clazz);
        } catch (InterruptedException ie) {
            log.error("Error Fetching Data From Tile [" + tileInstance.getJivePushUrl() + "]", ie);
            throw JiveClientException.buildException("Error Fetching Data to Tile [" + tileInstance.getJivePushUrl() + "]",ie,tileInstance,null,clazz);
        } catch (ExecutionException ee) {
            log.error("Error Fetching Data From Tile [" + tileInstance.getJivePushUrl() + "]", ee);
            throw JiveClientException.buildException("Error Fetching Data to Tile [" + tileInstance.getJivePushUrl() + "]",ee,tileInstance,null,clazz);
        } // end try/catch

        asyncInvoker = null;
        target = null;
        client.close();
        client = null;

        return null;
    } // end fetchData

    /**
     * Post new external activity object into activity tile stream. Returns the created ActivityEntry.
     *
     * @param tileInstance Instance of activity tile stream
     * @param data External activity object content
     */
    public ActivityEntry pushActivity(TileInstance tileInstance, ActivityPushTile data) throws JiveClientException {
        ActivityEntry created = null;
        try {
            created = makeRequest(tileInstance, tileInstance.getJivePushUrl(), HttpMethod.POST, data, ActivityEntry.class);
        }
        catch (TargetGoneException e) {
            log.info("Received 410 from activity push request, assuming tile deleted in Jive and removing it, instance url: " + tileInstance.getJivePushUrl());
            tileInstanceRepository.delete(tileInstance);
        }
        return created;
    }

    /**
     * Update existing external activity object in an activity tile stream. Returns the updated ActivityEntry.
     *
     * @param tileInstance Instance of activity tile stream
     * @param externalObjectUrl API url for the external object
     * @param data External activity object content
     *
     * @throws TargetGoneException Thrown when receiving a 410 response. This means that external activity object has been deleted in Jive.
     */
    public ActivityEntry updateActivity(TileInstance tileInstance, String externalObjectUrl, ActivityPushTile data) throws JiveClientException {
        return makeRequest(tileInstance, externalObjectUrl, HttpMethod.PUT, data, ActivityEntry.class);
    }

    /**
     * Make http request to url, with given method and content. Tile instance is used to insert
     * auth headers into the request.
     *
     * @param tileInstance Tile instance where data is pushed, or the parent activity stream tile, when updating external activity object
     * @param url Full url to request target
     * @param methodName String name of http method
     * @param content Request content, will be sent as JSON
     * @param returnType Class type of response entity. Use String.class if you dont care about the response
     * @throws TargetGoneException Thrown when jive responds with 410, meaning the object identified by url has been removed
     */
    public <T> T makeRequest(TileInstance tileInstance, String url, String methodName, Object content, Class<T> returnType) throws TargetGoneException {
        initAccessTokens(tileInstance);
        Client client = buildClient();

        WebTarget target = client.target(tileInstance.getJivePushUrl());

        AsyncInvoker asyncInvoker = target.request(MediaType.APPLICATION_JSON_TYPE)
                .header(HttpHeaders.AUTHORIZATION, tileInstance.getCredentials().getAuthorizationHeader())
                .async();

        Entity contentEntity = Entity.entity(content, MediaType.APPLICATION_JSON_TYPE);
        Future<Response> responseFuture = asyncInvoker.method(methodName, contentEntity);
        Response response = null;
        try {
            response = responseFuture.get();
            if (response.getStatus() == Response.Status.GONE.getStatusCode()) {
                // target (tile instance, external stream object, etc..) has been removed from Jive
                // throw a checked exception so that caller can react accordingly
                throw new TargetGoneException("", tileInstance);
            }
            return response.readEntity(returnType);
        }
        catch (ForbiddenException fe) {
            log.debug("403 response from a " + methodName + " to " + url);
            refreshAccessTokens(tileInstance);
            return makeRequest(tileInstance, url, methodName, content, returnType); // recursive call to retry
        }
        catch (InterruptedException e) {
            // todo useless to catch these... refactor somehow
            log.error("Error when making a " + methodName + " to " + url, e);
            throw new RuntimeException("Failed to make a request to jive");
        }
        catch (ExecutionException e) {
            log.error("Error when making a " + methodName + " to " + url, e);
            throw new RuntimeException("Failed to make a request to jive");
        }
        finally {
            if (response != null ) {
                response.close();
            }
            client.close();
        }
    }

    private void refreshAccessTokens(TileInstance tileInstance) {
        tileInstance.setCredentials(jiveOAuthClient.refreshTileInstanceAccessToken(tileInstance));

        tileInstanceRepository.save(tileInstance);
        if (log.isDebugEnabled()) { log.debug("Successfully Updated Tile Instance!"); }
    }

    private void initAccessTokens(TileInstance tileInstance) {
        if (log.isTraceEnabled()) { log.trace("Initializing OAuth Access Tokens"); }
        if (!JiveSDKUtils.isAllExist(tileInstance.getCredentials().getRefreshToken())) {
            if (log.isDebugEnabled()) { log.debug("Refresh OAuth Token Missing ..."); }
            tileInstance.setCredentials(jiveOAuthClient.getTileInstanceAccessToken(tileInstance));

            //TODO: SHOULD I SAVE EXPIRES TIME / CALCULATE AND REFRESH PROACTIVELY?

            tileInstanceRepository.save(tileInstance);
            if (log.isDebugEnabled()) { log.debug("Successfully Updated Tile Instance!"); }

        } // end if

    } // end initAccessTokens

} // end JiveTileClient