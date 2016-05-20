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

/**
 * Created by rrutan on 11/10/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JiveActivity {

    private JiveActor actor;
    private String content;

    @JsonProperty("jive")
    private JiveActivityDetails details;


    private JiveActivityObject object;

    //TODO: NEED TO CONFIRM IF WE NEED TO CREATE YET ANOTHER TYPED OBJECT OR IF THIS IS SAFE TO RE-USE
    private JiveActor provider;

    private String published; //TODO: CONVERT TO DATE

    //TODO: NEED TO CONFIRM IF WE NEED TO CREATE YET ANOTHER TYPED OBJECT OR IF THIS IS SAFE TO RE-USE
    private JiveActor target;

    private String title;

    private String updated; //TODO: CONVERT TO DATE

    private String url;

    private String verb;


    public JiveActor getActor() {
        return actor;
    }

    public void setActor(JiveActor actor) {
        this.actor = actor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public JiveActivityDetails getDetails() {
        return details;
    }

    public void setDetails(JiveActivityDetails details) {
        this.details = details;
    }

    public JiveActivityObject getObject() {
        return object;
    }

    public void setObject(JiveActivityObject object) {
        this.object = object;
    }

    public JiveActor getProvider() {
        return provider;
    }

    public void setProvider(JiveActor provider) {
        this.provider = provider;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public JiveActor getTarget() {
        return target;
    }

    public void setTarget(JiveActor target) {
        this.target = target;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }
}
