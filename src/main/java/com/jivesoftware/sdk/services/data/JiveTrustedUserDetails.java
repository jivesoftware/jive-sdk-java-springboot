package com.jivesoftware.sdk.services.data;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="request", proxyMode =ScopedProxyMode.TARGET_CLASS)
public class JiveTrustedUserDetails {
	
	private static final String X_JIVE_USER_EMAIL = "X-Jive-User-Email";
	private static final String X_JIVE_USER_ID = "X-Jive-User-ID";
	private static final String X_JIVE_USER_EXTERNAL = "X-Jive-User-External";
	
	private @Autowired HttpServletRequest request;
	
	public String getEmail() { return request.getHeader(X_JIVE_USER_EMAIL); }
	public long getUserID() { return Long.valueOf(request.getHeader(X_JIVE_USER_ID)); }
	public boolean isExternal() { return "true".equals(request.getHeader(X_JIVE_USER_EXTERNAL)); }
	@Override
	public String toString() {
		return "JiveTrustedUserDetails [email=" + getEmail() + ", userID="+ getUserID() + ", isExternal="+isExternal() + "]";
	}
	
} // end class