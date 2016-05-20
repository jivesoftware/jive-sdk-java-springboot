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

package com.jivesoftware.sdk.client.filter;

import org.glassfish.jersey.message.internal.ReaderWriter;
import org.glassfish.jersey.server.ContainerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by rrutan on 2/7/14.
 */

@Provider
public class StripAllowIllegalResourceCallFilter implements ClientResponseFilter {
    private static final Logger log = LoggerFactory.getLogger(StripAllowIllegalResourceCallFilter.class);

    private static final String ALLOW_ILLEGAL_RESOURCE_CALL_PREFIX = "throw 'allowIllegalResourceCall is false.';\n";

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {

        stripResponse(responseContext);

    } // end getJsonBody



    private void stripResponse(ClientResponseContext responseContext) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = responseContext.getEntityStream();

        final StringBuilder b = new StringBuilder();
        try {
            if (in.available() > 0) {
                ReaderWriter.writeTo(in, out);
                StringBuffer sbuf = new StringBuffer(new String(out.toByteArray()));
                if (sbuf.indexOf(ALLOW_ILLEGAL_RESOURCE_CALL_PREFIX) == 0) {
                    if (log.isDebugEnabled()) { log.debug("Stripping "+ALLOW_ILLEGAL_RESOURCE_CALL_PREFIX); }
                    responseContext.setEntityStream(new ByteArrayInputStream(sbuf.substring(ALLOW_ILLEGAL_RESOURCE_CALL_PREFIX.length()).getBytes()));
                } else {
                    responseContext.setEntityStream(new ByteArrayInputStream(out.toByteArray()));
                } // end if
            } // end if
        } catch (IOException ex) {
            throw new ContainerException(ex);
        } // end try/catch

    } // end logResponse

} // end class