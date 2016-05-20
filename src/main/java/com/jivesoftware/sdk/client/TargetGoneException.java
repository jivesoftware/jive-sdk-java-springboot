package com.jivesoftware.sdk.client;

/**
 * Created with IntelliJ IDEA.
 * User: jussi.heikkola
 * Date: 3/4/14
 * Time: 3:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class TargetGoneException extends JiveClientException {
    public TargetGoneException() {
    }

    public TargetGoneException(String message, Object context) {
        super(message);
        this.context = context;
    }
}
