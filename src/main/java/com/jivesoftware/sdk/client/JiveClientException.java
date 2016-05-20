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
 * Created by rrutan on 2/9/14.
 */
public class JiveClientException extends Exception {

    protected Object data;
    protected Object context;
    protected Class dataClass;

    public JiveClientException() {
    }

    public JiveClientException(String message) {
        super(message);
    }

    public JiveClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public JiveClientException(Throwable cause) {
        super(cause);
    }

    public JiveClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public static JiveClientException buildException(String message, Throwable throwable, Object context, Object data, Class dataClass) {
        JiveClientException exception = new JiveClientException(message,throwable);
        exception.setContext(context);
        exception.setData(data);
        exception.setDataClass(dataClass);
        return exception;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getContext() {
        return context;
    }

    public void setContext(Object context) {
        this.context = context;
    }

    public Class getDataClass() {
        return dataClass;
    }

    public void setDataClass(Class dataClass) {
        this.dataClass = dataClass;
    }
} // end class
