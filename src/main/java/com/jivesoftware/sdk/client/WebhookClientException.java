/*
 *
 *  * Copyright 2013 Jive Software
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *       http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 */

package com.jivesoftware.sdk.client;

/**
 * Created by rrutan on 8/15/14.
 */
public class WebhookClientException extends Exception {
    public WebhookClientException() {
    }

    public WebhookClientException(String message) {
        super(message);
    }

    public WebhookClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebhookClientException(Throwable cause) {
        super(cause);
    }

    public WebhookClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
