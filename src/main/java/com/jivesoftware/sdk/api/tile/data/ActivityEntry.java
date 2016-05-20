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

package com.jivesoftware.sdk.api.tile.data;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Tile activity entry data
 * Created by rrutan on 2/9/14.
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityEntry {
    private ActivityAction action; // required
    private ActivityActor actor;
    private ActivityObject object; // required
    private ActivityJive jive;

    private String id;
    private ActivityEntryResources resources;

    private ActivityStatus status;

    public ActivityStatus getStatus() {
        return status;
    }

    public void setStatus(ActivityStatus status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ActivityEntryResources getResources() {
        return resources;
    }

    public void setResources(ActivityEntryResources resources) {
        this.resources = resources;
    }

    public ActivityAction getAction() {
        return action;
    }

    public void setAction(ActivityAction action) {
        this.action = action;
    }

    public ActivityActor getActor() {
        return actor;
    }

    public void setActor(ActivityActor actor) {
        this.actor = actor;
    }

    public ActivityObject getObject() {
        return object;
    }

    public void setObject(ActivityObject object) {
        this.object = object;
    }

    public ActivityJive getJive() {
        return jive;
    }

    public void setJive(ActivityJive jive) {
        this.jive = jive;
    }
}
