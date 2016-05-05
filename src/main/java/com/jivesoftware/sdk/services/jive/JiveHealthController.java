package com.jivesoftware.sdk.services.jive;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jivesoftware.sdk.services.data.health.HealthMessage;
import com.jivesoftware.sdk.services.data.health.HealthResource;
import com.jivesoftware.sdk.services.data.health.HealthStatus;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/jive/health")
public class JiveHealthController {

	@ApiOperation( 
	    value = "TODO: Value", 
	    notes = "TODO: Notes", 
	    response = HealthStatus.class 
	)
	@ApiResponses( {
	    @ApiResponse( code = 200, message = "Person with such e-mail doesn't exists" )    
	} )
	@RequestMapping(
		method={RequestMethod.GET,RequestMethod.POST},value="/ping"
	)
	public ResponseEntity<HealthStatus> healthPing() {
			
		/*** EXAMPLE CONSTRUCTION ***/
		HealthStatus status = new HealthStatus();
        status.setLastUpdate(new Date());
        status.setStatus("ok");
        
        HealthMessage statusMessage = new HealthMessage();
        statusMessage.setDetail("sample detail");
        statusMessage.setFix("sample fix");
        statusMessage.setLevel("info");
        statusMessage.setSummary("sample summary");

        status.addMessage(statusMessage);

        HealthResource resource = new HealthResource();
        resource.setLastUpdate(new Date());
        resource.setName("resource name");
        resource.setStatus("fault");
        resource.setUrl("http://www.google.com");

        HealthMessage resourceMessage = new HealthMessage();
        resourceMessage.setDetail("resource detail");
        resourceMessage.setFix("resource fix");
        resourceMessage.setLevel("debug");
        resourceMessage.setSummary("resource summary");

        resource.addMessage(resourceMessage);

        status.addResource(resource);
        
        HttpStatus httpStatus = HttpStatus.OK;
        
		return new ResponseEntity<HealthStatus>(status,httpStatus);
	} // end healthPing
	
} // end class
