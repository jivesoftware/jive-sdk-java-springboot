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

package com.jivesoftware.sdk.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import java.util.List;

/**
 * Created by rrutan on 8/14/14.
 */
@Component
@Singleton
public class OAuthEventPublisher {
    private static final Logger log = LoggerFactory.getLogger(OAuthEventPublisher.class);

    private List<OAuthEventListener> oauthEventListeners;

    public List<OAuthEventListener> getOauthEventListeners() { return oauthEventListeners; }
    public void setOauthEventListeners(List<OAuthEventListener> oauthEventListeners) {
        this.oauthEventListeners = oauthEventListeners;
    } // end get/setOauthEventListeners

    public void publishEvent(OAuthEvent event) {
        if (oauthEventListeners != null && oauthEventListeners.size() > 0) {
            for (OAuthEventListener listener : oauthEventListeners) {
                if (log.isTraceEnabled()) { log.trace("["+ listener.getClass().getSimpleName()+"]..."); }
                if (listener.accepts(event)) {
                    if (log.isDebugEnabled()) { log.debug("[" + listener.getClass().getSimpleName() + "] accepted event[" + event.getType() + "]..."); }
                    try {
                        listener.process(event);
                    } catch (OAuthEventListener.OAuthEventException oee) {
                        //TODO: ADD FUNCTIONALITY INTO EXCEPTION TO CONTROL BREAKING LOOP
                        log.error("Unable to Process OAuth Event, continuing to other listeners",oee);
                    } // end try/catch
                } // end if
            } // end for
        } else {
            if (log.isDebugEnabled()) { log.debug("No OAuthEventListeners Registered, ignoring..."); }
            // end if
        } // end if
    } // end publishEvent

} // end class