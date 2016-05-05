package com.jivesoftware.sdk.services.data.health;

import java.util.Date;
import java.util.List;

import com.jivesoftware.sdk.util.DateTimeUtils;

import io.swagger.annotations.ApiModel;
import jersey.repackaged.com.google.common.collect.Lists;

@ApiModel( value = "HealthResource", description = "TODO: HealthResource" )
public class HealthResource {

    private String name;

    private String url;

    private String status;

    private String lastUpdate;

    private List<HealthMessage> messages;

    public HealthResource() {
        this.name = null;
        this.url = null;
        this.status = null;
        this.lastUpdate = null;
        this.messages = Lists.newArrayList();
    } // end constructor

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = DateTimeUtils.dateToIso(lastUpdate);
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<HealthMessage> getMessages() {
        return messages;
    }

    public void addMessage(HealthMessage message) {
        this.messages.add(message);
    }

    public void removeMessage(HealthMessage message) {
        this.messages.remove(message);
    }

    public void setMessages(List<HealthMessage> messages) {
        this.messages = messages;
    }

} // end class
