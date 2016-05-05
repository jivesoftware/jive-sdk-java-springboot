package com.jivesoftware.sdk.services.data.webhook;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class WebhookJiveObject implements Serializable {

    private String productIcon;

    private String iconCss;

    private int replyCount;

    private long objectID;

    private Date collectionUpdated;

    private String collection;

    private String productName;

    private int objectType;

    private List<Map<String,Object>> onBehalfOf;


}
