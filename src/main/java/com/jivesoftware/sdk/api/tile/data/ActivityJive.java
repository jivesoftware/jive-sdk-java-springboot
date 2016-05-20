package com.jivesoftware.sdk.api.tile.data;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Tile activity jive data 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityJive {
    private ActivityJiveApp app;

    public ActivityJiveApp getApp() {
        return app;
    }

    public void setApp(ActivityJiveApp app) {
        this.app = app;
    }
}
