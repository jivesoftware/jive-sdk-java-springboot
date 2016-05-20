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
import org.springframework.beans.factory.annotation.Autowired;

import com.jivesoftware.sdk.dao.entity.JiveInstance;
import com.jivesoftware.sdk.event.JiveInstanceEvent;
import com.jivesoftware.sdk.event.JiveInstanceEventListener;
import com.jivesoftware.sdk.event.TileInstanceEvent;
import com.jivesoftware.sdk.util.JiveSDKUtils;

/**
 * Created by rrutan on 2/7/14.
 */
public class JiveOAuthEventListener implements JiveInstanceEventListener {
    private static final Logger log = LoggerFactory.getLogger(JiveOAuthEventListener.class);

    @Autowired
    private JiveOAuthClient jiveOAuthClient;

//    private void fireOAuthEvent(OAuthEvent.Type type, Object context) {
//        if (log.isDebugEnabled()) { log.debug("firingOAuthEvent [" + type + "]..."); }
//        Map<String, Object> data = Maps.newHashMap();
//        data.put(OAuthEvent.CONTEXT,context);
//        oauthEvent.fire(new OAuthEvent(type, data));
//    } // end fireOAuthEvent

    @Override
    public boolean accepts(JiveInstanceEvent event) {
        boolean accept = TileInstanceEvent.Type.RegisterSuccess.equals(event.getType());
        if (log.isTraceEnabled()) { log.trace("accepts [event="+event.getType()+"]["+accept+"]..."); }
        return accept;
    } // end accepts

    @Override
    public void process(JiveInstanceEvent event) throws JiveInstanceEventException {
        if (log.isDebugEnabled()) { log.debug("onJiveInstanceRegisterSuccessEvent["+event.getType()+"] called..."); }
        JiveInstance jiveInstance = (JiveInstance)event.getContext();

        if (JiveSDKUtils.isAllExist(jiveInstance.getCode())) {
            //TODO: CHECK IF CODES NEED TO BE REFRESHED USING EXPIRE
            if (log.isDebugEnabled()) { log.debug("Refreshing OAuth Access Tokens["+jiveInstance.getTenantId()+"] ..."); }

            jiveOAuthClient.initAccessTokens(jiveInstance);

            if (log.isDebugEnabled()) { log.debug("Refreshing OAuth Access Tokens ... DONE"); }
//            fireOAuthEvent(OAuthEvent.Type.RefreshSuccess,jiveInstance);
        } // end if
    } // end onJiveInstanceRegisterSuccessEvent


} // end class
