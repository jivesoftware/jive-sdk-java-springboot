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

package com.jivesoftware.sdk.api.data;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.Map;

/**
 * Created by rrutan on 11/10/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JiveActivityDetails {

    private String collection;
    private String collectionUpdated; //TODO: CONVERT TO DATE
    private JiveActivityParent parent;
    private JiveActor parentActor;
    private int parentReplyCount;
    private int replyCount;
    private long objectID;
    private int objectType;
    private String iconCss;
    private boolean canReply;

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getCollectionUpdated() {
        return collectionUpdated;
    }

    public void setCollectionUpdated(String collectionUpdated) {
        this.collectionUpdated = collectionUpdated;
    }

    public JiveActivityParent getParent() {
        return parent;
    }

    public void setParent(JiveActivityParent parent) {
        this.parent = parent;
    }

    public JiveActor getParentActor() {
        return parentActor;
    }

    public void setParentActor(JiveActor parentActor) {
        this.parentActor = parentActor;
    }

    public int getParentReplyCount() {
        return parentReplyCount;
    }

    public void setParentReplyCount(int parentReplyCount) {
        this.parentReplyCount = parentReplyCount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public long getObjectID() {
        return objectID;
    }

    public void setObjectID(long objectID) {
        this.objectID = objectID;
    }

    public int getObjectType() {
        return objectType;
    }

    public void setObjectType(int objectType) {
        this.objectType = objectType;
    }

    public String getIconCss() {
        return iconCss;
    }

    public void setIconCss(String iconCss) {
        this.iconCss = iconCss;
    }

    public boolean isCanReply() {
        return canReply;
    }

    public void setCanReply(boolean canReply) {
        this.canReply = canReply;
    }
} // end class

