package com.jivesoftware.sdk.services.filters;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotAuthorizedException;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import com.jivesoftware.sdk.dao.JiveInstanceRepository;
import com.jivesoftware.sdk.dao.entity.JiveInstance;
import com.jivesoftware.sdk.util.JiveSDKUtils;

import jersey.repackaged.com.google.common.collect.Maps;

public class JiveSignedFetchValidationFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(JiveSignedFetchValidationFilter.class);
	
	public static final String ATTR_JIVE_INSTANCE = "jiveInstance";
	
    private static final String PARAM_ALGORITHM = "algorithm";
    private static final String PARAM_CLIENT_ID = "client_id";
    private static final String PARAM_JIVE_URL = "jive_url";
    private static final String PARAM_TENANT_ID = "tenant_id";
    private static final String PARAM_TIMESTAMP = "timestamp";
    private static final String PARAM_SIGNATURE = "signature";

    private static final String JIVE_EXTN = "JiveEXTN ";

    private static final String QUERY_PARAM_SIGNATURE = "&" + PARAM_SIGNATURE + "=";

	private JiveInstanceRepository jiveInstanceDAO;
	
	public JiveSignedFetchValidationFilter(JiveInstanceRepository jiveInstanceDAO) {
		this.jiveInstanceDAO = jiveInstanceDAO;
	} // end constructor
	
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	logger.debug("JiveSignedFetchValidationFilter processing...");
		try {
			authenticate((HttpServletRequest)request);
			chain.doFilter(request, response);
		} catch (BadRequestException bre) {
			((HttpServletResponse)response).sendError(HttpStatus.BAD_REQUEST.value(),bre.getMessage());
		} catch (NotAuthorizedException nwa) {
			((HttpServletResponse)response).sendError(HttpStatus.UNAUTHORIZED.value(),nwa.getMessage());
		} // end try/catch
    } // end doFilter
    
    
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.debug("JiveSignedFetchValidationFilter initializing...");
    } // end init


    @Override
    public void destroy() {
    	logger.debug("JiveSignedFetchValidationFilter destroying...");
    } // end destroy
        


    public void authenticate(HttpServletRequest request) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorization == null) {
            logger.info("Jive authorization isn't defined: {}", authorization);
            throw new BadRequestException("Missing Jive Authorization Header");
        } // end if
        
        if (!authorization.startsWith(JIVE_EXTN) || !authorization.contains(QUERY_PARAM_SIGNATURE)) {
            logger.info("Jive authorization isn't properly formatted: {}", authorization);
            throw new BadRequestException("Jive Authorization Header is improperly formatted");
        } // end if

        Map<String, String> paramMap = getParamsFromAuthz(authorization);
        String signature = paramMap.remove(PARAM_SIGNATURE);
        String algorithm = paramMap.get(PARAM_ALGORITHM);
        String clientId = paramMap.get(PARAM_CLIENT_ID);
        String jiveUrl = paramMap.get(PARAM_JIVE_URL);
        String tenantId = paramMap.get(PARAM_TENANT_ID);
        String timeStampStr = paramMap.get(PARAM_TIMESTAMP);

        if (!JiveSDKUtils.isAllExist(algorithm, clientId, jiveUrl, tenantId, timeStampStr)) {
            logger.error("Jive authorization is partial: {}", paramMap);
            throw new BadRequestException("Jive Authorization Header is incomplete");
        } // end if

        long timeStamp = Long.parseLong(timeStampStr);
        long millisPassed = System.currentTimeMillis() - timeStamp;
        if (millisPassed < 0 || millisPassed > TimeUnit.MINUTES.toMillis(5)) {
            logger.error("Jive authorization is rejected since it's {}ms old (max. allowed is 5 minutes): {}", millisPassed, paramMap);
            throw new NotAuthorizedException("Jive Authorization Header is expired");
        } // end if

       JiveInstance jiveInstance = getJiveInstanceByTenantId(tenantId);
       
        if (jiveInstance == null) {
            logger.error("Jive authorization failed due to invalid tenant ID: {}", tenantId);
            throw new NotAuthorizedException("Unable to validate Jive Authorization Header : x001");
        } // end if

        String expectedClientId = jiveInstance.getClientId();
        if (!clientId.equals(expectedClientId)) {
            logger.error("Jive authorization failed due to missing Client ID: Actual [{}], Expected [{}]", clientId, expectedClientId);
            throw new NotAuthorizedException("Unable to validate Jive Authorization Header : x002");
        } // end if

        String clientSecret = jiveInstance.getClientSecret();
        String paramStrWithoutSignature = authorization.substring(JIVE_EXTN.length(), authorization.indexOf(QUERY_PARAM_SIGNATURE));

        try {
            String expectedSignature = sign(paramStrWithoutSignature, clientSecret, algorithm);
            if (expectedSignature.equals(signature)) {
                request.setAttribute(JiveSignedFetchValidationFilter.ATTR_JIVE_INSTANCE,jiveInstance);
            } else {
                logger.error("Jive authorization failed due to tampered signature! Original authz: {}", authorization);
                throw new NotAuthorizedException("Unable to validate Jive Authorization Header : x003");
            } // end if
        } catch (Exception e) {
            logger.error("Failed validating Jive auth. scheme {}", e.getMessage(), e);
            throw new NotAuthorizedException("Unable to validate Jive Authorization Header : x004");
        } // end try/catch

    } // end authenticate

     private String sign(String str, String clientSecret, String algorithm) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
         byte[] secret = Base64.decodeBase64(clientSecret);
         SecretKeySpec secretKeySpec = new SecretKeySpec(secret, algorithm);
         Mac mac = Mac.getInstance(algorithm);
         mac.init(secretKeySpec);
         mac.update(str.getBytes("UTF-8"));
         return Base64.encodeBase64String(mac.doFinal()).replaceAll("\\s+", "");
     } // end sign

    private Map<String, String> getParamsFromAuthz(String authHeader) {
        if (!authHeader.startsWith(JIVE_EXTN)) {
            return Maps.newHashMap();
        } // end if

        authHeader = authHeader.substring(JIVE_EXTN.length());
        String[] params = authHeader.split("[?|&]");
        Map<String, String> paramMap = Maps.newHashMap();
        for (String param : params) {
            String[] tokens = param.split("=");
            if (tokens.length != 2) {
                return Maps.newHashMap();
            } // end if

            paramMap.put(JiveSDKUtils.decodeUrl(tokens[0]), JiveSDKUtils.decodeUrl(tokens[1]));
        } // end for param

        return paramMap;
    } // end getParamsFromAuthz
    
    private JiveInstance getJiveInstanceByTenantId(String tenantId) {
    	logger.debug("Getting Instance by TenantId[{}]....", tenantId);
    	JiveInstance result = jiveInstanceDAO.findByTenantId(tenantId);
    	if (result != null) {
    		return result;
    	} else {
    		 logger.info("No JiveInstance found for tenantId[{}]", tenantId);
    	} // end if
    	return null;
    } // end getJiveInstanceByTenantID
   
} // end class
