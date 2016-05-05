package com.jivesoftware.sdk.services.data;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.jivesoftware.sdk.dao.entity.JiveInstance;
import com.jivesoftware.sdk.services.filters.JiveSignedFetchValidationFilter;

@Component
@Scope(value="request", proxyMode =ScopedProxyMode.TARGET_CLASS)
public class JiveInstanceDetails {

	private @Autowired HttpServletRequest request;
	
	public JiveInstance getJiveInstance() {
		if (request.getAttribute(JiveSignedFetchValidationFilter.ATTR_JIVE_INSTANCE) != null) {
			return (JiveInstance)request.getAttribute(JiveSignedFetchValidationFilter.ATTR_JIVE_INSTANCE);
		} // end if
		return null;
	} // end jiveInstance
	
} // end class
