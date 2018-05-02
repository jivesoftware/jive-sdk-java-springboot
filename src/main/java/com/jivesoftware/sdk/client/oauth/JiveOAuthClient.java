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

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.inject.Singleton;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jivesoftware.sdk.client.BaseJiveClient;
import com.jivesoftware.sdk.dao.JiveInstanceRepository;
import com.jivesoftware.sdk.dao.TileInstanceRepository;
import com.jivesoftware.sdk.dao.entity.JiveInstance;
import com.jivesoftware.sdk.dao.entity.OAuthCredentials;
import com.jivesoftware.sdk.dao.entity.TileInstance;
import com.jivesoftware.sdk.util.JiveSDKUtils;

/**
 * This code will refresh an oauth token after it expires.
 */
@Component
@Singleton
public class JiveOAuthClient extends BaseJiveClient {
    private static final Logger log = LoggerFactory.getLogger(JiveOAuthClient.class);

    @Autowired 
    private JiveInstanceRepository jiveInstanceRepository;

    @Autowired
    private TileInstanceRepository tileInstanceRepository;

    public JiveOAuthClient() {
        if (log.isTraceEnabled()) { log.trace("constructor called..."); }
    } // end constructor

    public void initAccessTokens(JiveInstance jiveInstance) {

        if (!JiveSDKUtils.isAllExist(jiveInstance.getCredentials().getRefreshToken())) {
            if (log.isDebugEnabled()) { log.debug("Refresh Credentials DO NOT Exist, Requesting Access Token..."); }
            requestAccessTokens(jiveInstance);
            return;
        } // end if

        //TODO: ADD LOGIC TO EXIT IF ACCESS TOKENS ARE STILL VALID

        Client client = buildClient();
        client.register(HttpAuthenticationFeature.basic(jiveInstance.getClientId(), jiveInstance.getClientSecret()));
        WebTarget target = client.target(jiveInstance.getJiveUrl()).path("/oauth2/token");
        Form form = new Form("grant_type", "refresh_token");
        form.param("refresh_token", jiveInstance.getCredentials().getAccessToken());
        form.param("client_id", jiveInstance.getClientId());
        form.param("client_secret", jiveInstance.getClientSecret());

        //TODO:  IMPLEMENT FUTURES
        AsyncInvoker asyncInvoker = target.request(MediaType.APPLICATION_FORM_URLENCODED).async();

        Future<JiveOAuthResponse> responseFuture = asyncInvoker.post(Entity.entity(form, MediaType.APPLICATION_JSON_TYPE),JiveOAuthResponse.class);

        JiveOAuthResponse response = null;
        try {
            response = responseFuture.get();
        } catch (BadRequestException bre) {
            log.error("Error Getting OAuth Access Tokens [/oauth2/token]", bre);
        } catch (InterruptedException ie) {
            log.error("Error Getting OAuth Access Tokens [/oauth2/token]", ie);
        } catch (ExecutionException ee) {
            log.error("Error Getting OAuth Access Tokens [/oauth2/token]", ee);
        } finally {
            response = null;
        } // end try/catch
        responseFuture = null;
        form = null;
        target = null;
        client.close();
        client = null;

//      BACKUP
//      JiveOAuthResponse response = target.request(MediaType.APPLICATION_FORM_URLENCODED).post(Entity.entity(form, MediaType.APPLICATION_JSON_TYPE),JiveOAuthResponse.class);

        if (log.isDebugEnabled()) { log.debug("Refresh Token : Response : " + response); }

        OAuthCredentials credentials = new OAuthCredentials();
        credentials.setAccessToken(response.getAccessToken());
        credentials.setRefreshToken(response.getRefreshToken());
        jiveInstance.setCredentials(credentials);
    } // initAccessTokens

    private void requestAccessTokens(JiveInstance jiveInstance) {

        if (JiveSDKUtils.isAllExist(jiveInstance.getCredentials().getRefreshToken())) {
            if (log.isDebugEnabled()) { log.debug("Refresh Credentials Exist, Refreshing..."); }
            initAccessTokens(jiveInstance);
            return;
        } // end if

        if (log.isDebugEnabled()) { log.debug("Retrieving Access Tokesn..."); }
        Client client = buildClient();
        client.register(HttpAuthenticationFeature.basic(jiveInstance.getClientId(), jiveInstance.getClientSecret()));
        WebTarget target = client.target(jiveInstance.getJiveUrl()).path("/oauth2/token");
        Form form = new Form("grant_type", "authorization_code");
        form.param("code", jiveInstance.getCode());
        form.param("client_id", jiveInstance.getClientId());

        //TODO:  IMPLEMENT FUTURES
        JiveOAuthResponse response = target.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED),JiveOAuthResponse.class);

        if (log.isDebugEnabled()) {
            log.debug("Access Token : Response : " + response);
        }

        OAuthCredentials credentials = new OAuthCredentials();
        credentials.setAccessToken(response.getAccessToken());
        credentials.setRefreshToken(response.getRefreshToken());
        jiveInstance.setCredentials(credentials);

    } // requestAccessTokens

    public OAuthCredentials refreshTileInstanceAccessToken(TileInstance tile) throws BadRequestException {
        JiveInstance jiveInstance = jiveInstanceRepository.findByTenantId(tile.getTenantId());
        //TODO: CONFIRM THAT THIS DOESN'T NEED TO BE pushURL INSTEAD
        TileInstance tileInstance = tileInstanceRepository.findByGlobalTileInstanceId(tile.getGlobalTileInstanceId());

        Client client = buildClient();
        client.register(HttpAuthenticationFeature.basic(jiveInstance.getClientId(), jiveInstance.getClientSecret()));
        WebTarget target = client.target(jiveInstance.getJiveUrl()).path("/oauth2/token");
        Form form = new Form("grant_type", "refresh_token");
        form.param("refresh_token",tileInstance.getCredentials().getRefreshToken());
        form.param("client_id",jiveInstance.getClientId());

        JiveOAuthResponse response = target.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), JiveOAuthResponse.class);

        OAuthCredentials credentials = new OAuthCredentials();
        credentials.setAccessToken(response.getAccessToken());
        credentials.setRefreshToken(response.getRefreshToken());
        return credentials;
    }

    public OAuthCredentials getTileInstanceAccessToken(TileInstance tile) throws BadRequestException {
        JiveInstance jiveInstance = jiveInstanceRepository.findByTenantId(tile.getTenantId());
        //TODO: CONFIRM THAT THIS DOESN'T NEED TO BE pushURL INSTEAD
        TileInstance tileInstance = tileInstanceRepository.findByGlobalTileInstanceId(tile.getGlobalTileInstanceId());

        Client client = buildClient();
        client.register(HttpAuthenticationFeature.basic(jiveInstance.getClientId(), jiveInstance.getClientSecret()));
        WebTarget target = client.target(jiveInstance.getJiveUrl()).path("/oauth2/token");
        Form form = new Form("grant_type", "authorization_code");
        form.param("code",tileInstance.getCode());
        form.param("client_id",jiveInstance.getClientId());

        //TODO:  IMPLEMENT FUTURES
        JiveOAuthResponse response = target.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), JiveOAuthResponse.class);

        OAuthCredentials credentials = new OAuthCredentials();
        credentials.setAccessToken(response.getAccessToken());
        credentials.setRefreshToken(response.getRefreshToken());

        return credentials;
    } // end refreshToken

// FOR REFERENCE
//var accessTokenRefresher = function(operationContext, oauth) {
//    var d = q.defer();
//    var instance = operationContext['instance'];
//    var jiveCommunity = instance['jiveCommunity'];
//
//    tileLibraryLookup(instance).then( function(instanceLibrary) {
//
//        jive.community.findByCommunity( jiveCommunity).then( function(community) {
//            var options = {};
//            if ( community ) {
//                options['client_id'] = community['clientId'];
//                options['client_secret'] = community['clientSecret'];
//                options['refresh_token'] = oauth['refreshToken'];
//                options['jiveUrl'] = community['jiveUrl'];
//
//                jiveClient.refreshAccessToken(options,
//                    function (response) {
//                        if (response.statusCode >= 200 && response.statusCode <= 299) {
//                            var accessTokenResponse = response['entity'];
//
//                            // success
//                            instance['accessToken'] = accessTokenResponse['access_token'];
//                            instance['expiresIn'] = accessTokenResponse['expires_in'];
//                            instance['refreshToken'] = accessTokenResponse['refresh_token'];
//
//                            var updatedOAuth = {
//                                'accessToken' : instance['accessToken'],
//                                'refreshToken' : instance['refreshToken']
//                            };
//
//                            instanceLibrary.save(instance).then(function() {
//                                d.resolve(updatedOAuth);
//                            });
//                        } else {
//                            jive.logger.error('error refreshing access token for ', instance);
//                            d.reject(response);
//                        }
//                    }, function (result) {
//                        // failure
//                        jive.logger.error('error refreshing access token for ', instance, result);
//                        d.reject(result);
//                    }
//                );
//            } else {
//                d.reject();
//            }
//        });
//
//    });
//
//
//    return d.promise;
//};

} // end class
