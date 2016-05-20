package com.jivesoftware.sdk.api.tile.data;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Activity jive app data
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityJiveApp {
    private String appUUID;
    private String view;
    private Map<String, String> context;

    public String getAppUUID() {
        return appUUID;
    }
    public void setAppUUID(String appUUID) {
        this.appUUID = appUUID;
    }
    public String getView() {
        return view;
    }
    public void setView(String view) {
        this.view = view;
    }
    public Map<String, String> getContext() {
        return context;
    }
    public void setContext(Map<String, String> context) {
        this.context = context;
    }
}
