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
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.List;
import java.util.Map;

/**
 * Created by rrutan on 11/10/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include= JsonSerialize.Inclusion.NON_DEFAULT)
public class JivePerson {

    private long id;
    private Map<String,JiveResource> resources;
    private long followerCount;
    private String published; //TODO: CONVERT TO DATE
    private String updated; //TODO: CONVERT TO DATE
    private String displayName;
    private List<JiveEmail> emails;
    private long followingCount;

    @JsonProperty("jive")
    private JivePersonDetails details;

    private JivePersonName name;

    private int thumbnailId;

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

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<JiveEmail> getEmails() {
        return emails;
    }

    public void setEmails(List<JiveEmail> emails) {
        this.emails = emails;
    }

    public long getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(long followingCount) {
        this.followingCount = followingCount;
    }

    public JivePersonDetails getDetails() {
        return details;
    }

    public void setDetails(JivePersonDetails details) {
        this.details = details;
    }

    public JivePersonName getName() {
        return name;
    }

    public void setName(JivePersonName name) {
        this.name = name;
    }

    public int getThumbnailId() {
        return thumbnailId;
    }

    public void setThumbnailId(int thumbnailId) {
        this.thumbnailId = thumbnailId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
