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

import com.jivesoftware.sdk.api.tile.data.TableTile;
import com.jivesoftware.sdk.client.JiveClientException;
import com.jivesoftware.sdk.dao.entity.TileInstance;

/**
 * Created by rrutan on 2/4/14.
 */
public abstract class JiveTableTile extends BaseJiveTile implements JiveTile<TableTile>{

    private static final Logger log = LoggerFactory.getLogger(JiveTableTile.class);

    @Override
    public TableTile fetchData(TileInstance tileInstance) throws JiveClientException {
        if (log.isDebugEnabled()) { log.debug("fetchData["+ tileInstance.getJivePushUrl()+"] called"); }
        return (TableTile)jiveTileClient.fetchData(tileInstance, TableTile.class);
    } // end fetchData

    @Override
    public void pushData(TileInstance tileInstance, TableTile data) throws JiveClientException {
        if (log.isDebugEnabled()) { log.debug("pushData["+ tileInstance.getJivePushUrl()+"] called"); }
        jiveTileClient.pushData(tileInstance,data);
    } // end pushData

}
