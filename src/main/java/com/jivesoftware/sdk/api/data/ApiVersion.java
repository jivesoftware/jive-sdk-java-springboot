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

import java.util.List;
import java.util.Map;

/**
 * Created by rrutan on 11/7/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiVersion {

    public String jiveVersion;
    private List<Map<String,String>> jiveCoreVersions;
    private String instanceURL;
    private boolean ssoForOAuthGrantEnabled;
    private Map<String,String> jiveEdition;

    public String getJiveVersion() {
        return jiveVersion;
    }

    public void setJiveVersion(String jiveVersion) {
        this.jiveVersion = jiveVersion;
    }

    public List<Map<String,String>> getJiveCoreVersions() {
        return jiveCoreVersions;
    }

    public void setJiveCoreVersions(List<Map<String,String>> jiveCoreVersions) {
        this.jiveCoreVersions = jiveCoreVersions;
    }

    public String getInstanceURL() {
        return instanceURL;
    }

    public void setInstanceURL(String instanceURL) {
        this.instanceURL = instanceURL;
    }

    public boolean isSsoForOAuthGrantEnabled() {
        return ssoForOAuthGrantEnabled;
    }

    public void setSsoForOAuthGrantEnabled(boolean ssoForOAuthGrantEnabled) {
        this.ssoForOAuthGrantEnabled = ssoForOAuthGrantEnabled;
    }

    public Map<String, String> getJiveEdition() {
        return jiveEdition;
    }

    public void setJiveEdition(Map<String, String> jiveEdition) {
        this.jiveEdition = jiveEdition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiVersion that = (ApiVersion) o;

        if (ssoForOAuthGrantEnabled != that.ssoForOAuthGrantEnabled) return false;
        if (instanceURL != null ? !instanceURL.equals(that.instanceURL) : that.instanceURL != null) return false;
        if (jiveCoreVersions != null ? !jiveCoreVersions.equals(that.jiveCoreVersions) : that.jiveCoreVersions != null)
            return false;
        if (jiveEdition != null ? !jiveEdition.equals(that.jiveEdition) : that.jiveEdition != null) return false;
        if (jiveVersion != null ? !jiveVersion.equals(that.jiveVersion) : that.jiveVersion != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = jiveVersion != null ? jiveVersion.hashCode() : 0;
        result = 31 * result + (jiveCoreVersions != null ? jiveCoreVersions.hashCode() : 0);
        result = 31 * result + (instanceURL != null ? instanceURL.hashCode() : 0);
        result = 31 * result + (ssoForOAuthGrantEnabled ? 1 : 0);
        result = 31 * result + (jiveEdition != null ? jiveEdition.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ApiVersion{" +
                "jiveVersion='" + jiveVersion + '\'' +
                ", jiveCoreVersions=" + jiveCoreVersions +
                ", instanceURL='" + instanceURL + '\'' +
                ", ssoForOAuthGrantEnabled=" + ssoForOAuthGrantEnabled +
                ", jiveEdition=" + jiveEdition +
                '}';
    }
} // end class
