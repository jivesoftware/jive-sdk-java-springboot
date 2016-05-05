package com.jivesoftware.sdk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jivesoftware.sdk.services.data.JiveInstanceDetails;
import com.jivesoftware.sdk.services.data.JiveTrustedUserDetails;

@RestController
@RequestMapping("/api/example")
public class ExampleSignedFetchController {

	/******************************************************************************************
	 * THIS SIMPLIFIES THE USER CONTEXT PASSED OVER VIA THE SIGNED FETCH HEADERS
	 ******************************************************************************************/
	@Autowired
	private JiveTrustedUserDetails userDetails;
	
	/******************************************************************************************
	 * THIS IS INJECTED FOR ALL SIGNED FETCH REQUESTS WHEN THEY ARE VALID
	 * IT HAS A REFERENCE TO THE FULL JIVE INSTANCE DETAILS
	 ******************************************************************************************/
	@Autowired
	private JiveInstanceDetails jiveInstanceDetails;
	
	@RequestMapping(
		method={RequestMethod.POST},value="/test"
	)
	public ResponseEntity<String> test(@RequestBody String body) {
		StringBuilder output = new StringBuilder("Request Details:").append("\n");
		output.append("\t").append("User: ");
		if (userDetails != null) {
			output.append(userDetails.getUserID()).append("\n");
		} else {
			output.append("<NONE>").append("\n");
		} // end if		
		output.append("\t").append("Instance: ");
		if (jiveInstanceDetails.getJiveInstance() != null) {
			output.append(jiveInstanceDetails.getJiveInstance().getTenantId()).append("\n");
		} else {
			output.append("<NONE>").append("\n");
		} // end if		
		output.append("\t").append("Body: ");
		if (body != null) {
			output.append(body).append("\n");
		} else {
			output.append("<NONE>").append("\n");
		} // end if
		return new ResponseEntity<String>(output.toString(),HttpStatus.OK);
	} // end test
	
} // end class
