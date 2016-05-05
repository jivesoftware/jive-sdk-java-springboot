package com.jivesoftware.sdk.util;

import java.util.Map;
import java.util.SortedMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jivesoftware.sdk.dao.entity.JiveInstance;

import jersey.repackaged.com.google.common.collect.Maps;

/**
 * Created by rrutan on 1/30/14.
 */
@Component
public class JiveSignatureValidator {
	private static final Logger log = Logger.getLogger(JiveSignatureValidator.class.getName());

    @Value("${jive.developmentMode}")
    private boolean developmentMode;
    
    @Value("${jive.ignoreSignatureValidation}")
    private boolean ignoreSignatureValidation;
    
    private Client client = null;

    public JiveSignatureValidator() {
        initClient();
    } // end constructor

    private void initClient() {
        client = ClientBuilder.newClient();
        //client.register(...)
    } //end initClient

    public boolean isValidSignature(JiveInstance jiveInstance) {
        if (developmentMode && ignoreSignatureValidation) {
        	log.log(Level.FINE,"ignoreSignatureValidation enabled in application.properties ... returning [true]");
            return true;
        } // end if
        String signature = jiveInstance.getJiveSignature();
        String signatureURL = jiveInstance.getJiveSignatureURL();
        boolean isHttps = signatureURL.toLowerCase().startsWith("https");

        if (signature == null || signatureURL == null) {
            String msg = String.format("Invalid signature [%s] or signature URL [%s]", signature, signatureURL);
            log.log(Level.WARNING,msg);
            return false;
        } // end if

        if (developmentMode && !isHttps) {
        	log.log(Level.WARNING,"Signature URL must be over SSL: " + signatureURL);
            return false;
        } // end if

        String signatureValidation = getSignatureValidation(jiveInstance);
        
        log.log(Level.FINE,"Calling ["+jiveInstance.getJiveSignatureURL()+"]...");
        WebTarget target = client.target(jiveInstance.getJiveSignatureURL());
        AsyncInvoker invocation = target.request(MediaType.APPLICATION_JSON_TYPE)
                                      .header("X-Jive-MAC", jiveInstance.getJiveSignature())
                                      .async();

        Future<String> responseFuture = invocation.post(Entity.entity(signatureValidation,MediaType.TEXT_PLAIN_TYPE),String.class);

        try {
            String response = responseFuture.get();
            log.log(Level.FINE,"Signature Validated ["+response+"]");
            return true;
        } catch (BadRequestException bre) {
            log.log(Level.WARNING,"Error Validating Signature (Bad Request)", bre);
        } catch (InterruptedException ie) {
        	log.log(Level.WARNING,"Error Validating Signature (Interrupted)", ie);
        } catch (ExecutionException ee) {
        	log.log(Level.WARNING,"Error Validating Signature (Exception)", ee);
        } // end try/catch

        return false;
    } // end isValidSignature


    private String getSignatureValidation(JiveInstance jiveInstance) {

        SortedMap<String, String> sortedMap = getJiveSignatureMap(jiveInstance);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key != null && value != null) {
                sb.append(key).append(':').append(value).append("\n");
            } // end if
        } // end for entry
        
        log.log(Level.FINE,"Signature Validation: \n"+sb.toString());
        return sb.toString();

    } // end getSignatureValidation
    
    public SortedMap<String, String> getJiveSignatureMap(JiveInstance jiveInstance) {
        // Encode the client-secret
        String encodedClientSecret = (jiveInstance.getClientSecret() != null) ? DigestUtils.sha256Hex(jiveInstance.getClientSecret()) : jiveInstance.getClientSecret();
        SortedMap<String, String> sortedMap = Maps.newTreeMap();
        sortedMap.put("clientId", jiveInstance.getClientId());
        sortedMap.put("clientSecret", encodedClientSecret);
        sortedMap.put("code", jiveInstance.getCode());
        sortedMap.put("jiveSignatureURL", jiveInstance.getJiveSignatureURL());
        sortedMap.put("jiveUrl", jiveInstance.getJiveUrl());
        sortedMap.put("scope", jiveInstance.getScope());
        sortedMap.put("tenantId", jiveInstance.getTenantId());
        sortedMap.put("timestamp", jiveInstance.getTimestamp());
        return sortedMap;
    } // end getJiveSignatureMap

} // end class
