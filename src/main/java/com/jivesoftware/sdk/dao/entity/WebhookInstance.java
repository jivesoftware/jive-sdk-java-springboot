package com.jivesoftware.sdk.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class WebhookInstance {

    @Id
    @GeneratedValue
	private long ID;
    
    private Date buggy;
    private String callback;
    private boolean enabled;
    private String events;
    private int followerCount;
    
    @Column(name = "webhookURI")
    private String id;
    
    private int likeCount;
    private String object;
    private Date published;
    //private ??? resources;
    private String[] tags;
    private Date updated;
    private boolean followed;

    public long getID() {
		return ID;
	}
	public void setID(long ID) {
		this.ID = ID;
	}
	public Date getBuggy() {
		return buggy;
	}
	public void setBuggy(Date buggy) {
		this.buggy = buggy;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getEvents() {
		return events;
	}
	public void setEvents(String events) {
		this.events = events;
	}
	public int getFollowerCount() {
		return followerCount;
	}
	public void setFollowerCount(int followerCount) {
		this.followerCount = followerCount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public Date getPublished() {
		return published;
	}
	public void setPublished(Date published) {
		this.published = published;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public boolean isFollowed() {
		return followed;
	}
	public void setFollowed(boolean followed) {
		this.followed = followed;
	}
	
} // end class
