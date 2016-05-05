package com.jivesoftware.sdk.services.data.health;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.annotations.ApiModel;

@ApiModel( value = "HealthMessage", description = "TODO: HealthMessage" )
public class HealthMessage {
    private static final Logger log = LoggerFactory.getLogger(HealthMessage.class);

    private String level;

    private String summary;

    private String detail;

    private String fix;

    enum Level {
        debug, info, warn, error
    }

    public HealthMessage() {
        this.level = null;
        this.summary = null;
        this.detail = null;
        this.fix = null;
    } // end constructor

    public String getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level.name();
    } // end setLevel

    public void setLevel(String level) {
        try {
            this.level = Level.valueOf(level).name();
        } catch (IllegalArgumentException iae) {
            if (log.isWarnEnabled()) { log.warn("Invalid HealthMessage.Level["+level+"], defaulting to info");   }
            this.level = Level.info.name();
        } // end try/catch
    } // end setStatus

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getFix() {
        return fix;
    }

    public void setFix(String fix) {
        this.fix = fix;
    }

} // end class