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
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.List;
import java.util.Map;

/**
 * Created by rrutan on 11/7/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include= JsonSerialize.Inclusion.NON_DEFAULT)
public class JiveContent {
    private long id;
    private Map<String,JiveResource> resources;
    private long followerCount;
    private long likeCount;
    private String published; //TODO: CONVERT TO DATE
    private List<String> tags;
    private String updated; //TODO: CONVERT TO DATE
    private String iconCss;
    private Map<String,String> parentPlace;
    private String contentID;
    private JivePerson author;

    private Map<String,String> content; //TODO: CREATE A STRUCTURED CLASS FOR THIS FIELD

    private String parent;
    private int replyCount;
    private String status;
    private String subject;
    private int viewCount;
    private boolean visibleToExternalContributors;

    private List<JiveAttachment> attachments;

    private String type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<String, JiveResource> getResources() {
        return resources;
    }

    public void setResources(Map<String, JiveResource> resources) {
        this.resources = resources;
    }

    public long getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(long followerCount) {
        this.followerCount = followerCount;
    }

    public long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getIconCss() {
        return iconCss;
    }

    public void setIconCss(String iconCss) {
        this.iconCss = iconCss;
    }

    public Map<String, String> getParentPlace() {
        return parentPlace;
    }

    public void setParentPlace(Map<String, String> parentPlace) {
        this.parentPlace = parentPlace;
    }

    public String getContentID() {
        return contentID;
    }

    public void setContentID(String contentID) {
        this.contentID = contentID;
    }

    public JivePerson getAuthor() {
        return author;
    }

    public void setAuthor(JivePerson author) {
        this.author = author;
    }

    public Map<String, String> getContent() {
        return content;
    }

    public void setContent(Map<String, String> content) {
        this.content = content;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public boolean isVisibleToExternalContributors() {
        return visibleToExternalContributors;
    }

    public void setVisibleToExternalContributors(boolean visibleToExternalContributors) {
        this.visibleToExternalContributors = visibleToExternalContributors;
    }

    public List<JiveAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<JiveAttachment> attachments) {
        this.attachments = attachments;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
