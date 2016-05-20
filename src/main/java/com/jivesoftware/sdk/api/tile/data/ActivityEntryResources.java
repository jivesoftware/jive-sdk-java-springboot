package com.jivesoftware.sdk.api.tile.data;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Data class for activity entry resources
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ActivityEntryResources {
    private EntityResource extprops;
    private EntityResource self;
    private EntityResource attachments;
    private EntityResource comments;

    public EntityResource getAttachments() {
        return attachments;
    }

    public void setAttachments(EntityResource attachments) {
        this.attachments = attachments;
    }

    public EntityResource getComments() {
        return comments;
    }

    public void setComments(EntityResource comments) {
        this.comments = comments;
    }

    public EntityResource getExtprops() {
        return extprops;
    }

    public void setExtprops(EntityResource extprops) {
        this.extprops = extprops;
    }

    public EntityResource getSelf() {
        return self;
    }

    public void setSelf(EntityResource self) {
        this.self = self;
    }
}
