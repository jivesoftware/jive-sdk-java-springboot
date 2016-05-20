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

package com.jivesoftware.sdk.api.tile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jivesoftware.sdk.api.tile.data.ExtendedProps;
import com.jivesoftware.sdk.client.JiveTileClient;
import com.jivesoftware.sdk.dao.entity.TileInstance;

/**
 * Created by rrutan on 2/9/14.
 */
public abstract class BaseJiveTile {
    private static final Logger log = LoggerFactory.getLogger(BaseJiveTile.class);

    @Autowired
    protected JiveTileClient jiveTileClient;

    public ExtendedProps fetchExtendedProperties(TileInstance tileInstance) {
        if (log.isTraceEnabled()) { log.trace("fetchExtendedProperties called ..."); }
//        JiveTileClient client = getTileClient();

        //TODO: IMPLEMENT
        if (log.isWarnEnabled()) { log.warn("Unimplemented!");  }

        return new ExtendedProps();
    } // end fetchExtendedProperties

    public void pushExtendedProperties(TileInstance tileInstance,ExtendedProps extendedProps) {
        if (log.isTraceEnabled()) { log.trace("pushExtendedProperties called ..."); }
//        JiveTileClient client = getTileClient();

        //TODO: IMPLEMENT
        if (log.isWarnEnabled()) { log.warn("Unimplemented!"); }

    } // end pushExtendedProperties


    public void removeExtendedProperties(TileInstance tileInstance) {
        if (log.isTraceEnabled()) { log.trace("removeExtendedProperties called ..."); }
//        JiveTileClient client = getTileClient();

        //TODO: IMPLEMENT
        if (log.isWarnEnabled()) { log.warn("Unimplemented!");  }

    } // end removeExtendedProperties

} // end class
