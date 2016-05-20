package com.jivesoftware.sdk.api.tile.data;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.List;

/**
 * Data class for a generic resource entry in jive objects
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class EntityResource {
    private List<String> allowed;
    private String ref;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public List<String> getAllowed() {
        return allowed;
    }

    public void setAllowed(List<String> allowed) {
        this.allowed = allowed;
    }
}
