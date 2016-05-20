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

/**
 * Created by rrutan on 11/10/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JivePersonDetails {

    private boolean enabled;

    private boolean external;

    private boolean federated;

    private String lastProfileUpdate;

    private JivePersonLevel level;

    private String locale;

    private boolean externalContributor;

    private boolean sendable;

    private String timeZone;

    private String username;

    private boolean visible;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isExternal() {
        return external;
    }

    public void setExternal(boolean external) {
        this.external = external;
    }

    public boolean isFederated() {
        return federated;
    }

    public void setFederated(boolean federated) {
        this.federated = federated;
    }

    public String getLastProfileUpdate() {
        return lastProfileUpdate;
    }

    public void setLastProfileUpdate(String lastProfileUpdate) {
        this.lastProfileUpdate = lastProfileUpdate;
    }

    public JivePersonLevel getLevel() {
        return level;
    }

    public void setLevel(JivePersonLevel level) {
        this.level = level;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public boolean isExternalContributor() {
        return externalContributor;
    }

    public void setExternalContributor(boolean externalContributor) {
        this.externalContributor = externalContributor;
    }

    public boolean isSendable() {
        return sendable;
    }

    public void setSendable(boolean sendable) {
        this.sendable = sendable;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
