package com.jivesoftware.sdk.services.data.health;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jivesoftware.sdk.util.DateTimeUtils;

import io.swagger.annotations.ApiModel;
import jersey.repackaged.com.google.common.collect.Lists;

@ApiModel( value = "HealthStatus", description = "TODO: HealthStatus" )
public class HealthStatus {
    private static final Logger log = LoggerFactory.getLogger(HealthStatus.class);

    private String status;
    private String lastUpdate;
    private List<HealthMessage> messages;
    private List<HealthResource> resources;

    enum Status {
        ok, fault, unknown, intermittent, maintenance;
    }

    public HealthStatus() {
        status = null;
        lastUpdate = null;
        messages = Lists.newArrayList();
        resources = Lists.newArrayList();
    } // end constructor

    public String getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status.name();
    }

    public void setStatus(String status) {
        try {
            this.status = Status.valueOf(status).name();
        } catch (IllegalArgumentException iae) {
            if (log.isWarnEnabled()) { log.warn("Invalid HealthStatus.Status["+status+"], defaulting to unknown");   }
            this.status = Status.unknown.name();
        } // end try/catch
    } // end setStatus

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

    public List<HealthResource> getResources() {
        return resources;
    }

    public void addResource(HealthResource resource) {
        this.resources.add(resource);
    }

    public void removeResource(HealthResource resource) {
        this.resources.remove(resource);
    }

    public void setResources(List<HealthResource> resources) {
        this.resources = resources;
    }

} // end class
