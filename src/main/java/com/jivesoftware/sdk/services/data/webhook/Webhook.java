package com.jivesoftware.sdk.services.data.webhook;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Created by rrutan on 8/16/14.
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
public class Webhook implements Serializable {


    public enum ContentEvent {

        document, discussion, file, dm, post, task, url, update, idea

    } // end ContentWebhook

    public enum SystemEvent {
        // SYSTEM WEBHOOKS
        user_account, user_session, user_membership, social_group, webhook

    } // end WebhookType

    public enum Verb {
        // CONTENT VERBS
        created, modified, commented, replied, liked, outcome_set,
        // SYSTEM VERBS
        user_account_created, user_account_deleted, user_account_disabled, user_account_enabled, user_account_invisible, user_account_visible,
        user_profile_modified, user_profile,
        user_type_modified,
        user_session_login, user_session_logout,
        user_membership_added, user_membership_removed,
        social_group_created, social_group_renamed, social_group_deleted,
        webhook_created, webhook_deleted, webhook_enabled, webhook_disabled, webhook_marked_buggy, webhook_marked_not_buggy;

        public String getFullName() { return "jive:"+name(); }
        public boolean equals(String verb) {
            if (verb == null) {
                return false;
            } // end if
            return (verb.equals(name()) || verb.equals(getFullName()));
        } // end equals
    } // end enum

    private String content;

    private String title;

    private WebhookJiveObject jive;

    private String verb;

    private Date updated;

    private WebhookTarget target;

    private WebhookProvider provider;

    private WebhookContentObject object;

    private Date published;

    private String url;

    //*** TODO: NOT DOCUMENTED HERE - https://community.jivesoftware.com/docs/DOC-97202
    private WebhookJiveObject actor;

    //*** TODO: NOT DOCUMENTED HERE - https://community.jivesoftware.com/docs/DOC-97202
    private String webhook;

} // end class
