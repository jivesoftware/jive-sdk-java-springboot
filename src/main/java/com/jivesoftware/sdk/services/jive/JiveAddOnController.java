package com.jivesoftware.sdk.services.jive;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jivesoftware.sdk.dao.JiveInstanceDAO;
import com.jivesoftware.sdk.dao.entity.JiveInstance;
import com.jivesoftware.sdk.util.JiveSignatureValidator;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/jive/addon")
public class JiveAddOnController {
	private static final Logger logger = Logger.getLogger(JiveAddOnController.class.getName());
	
	@Autowired
	private JiveSignatureValidator validator;
	
	@Autowired
	private JiveInstanceDAO jiveInstanceDAO;	

	@ApiOperation( 
	    value = "TODO: Value", 
	    notes = "TODO: Notes"
	)
	@ApiResponses( {
	    @ApiResponse( code = 204, message = "TODO: success response" )    
	} )
	@RequestMapping(
		method={RequestMethod.POST},value="/register"
	)
	public ResponseEntity<HttpStatus> register(@RequestBody JiveInstance jiveInstance) {
		logger.log(Level.FINE,"/jive/addon/register called...");
		if (validator.isValidSignature(jiveInstance)) {
			saveInstance(jiveInstance);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} // end if
		return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
	} // end register
	
	@ApiOperation( 
	    value = "TODO: Value", 
	    notes = "TODO: Notes"
	)
	@ApiResponses( {
		@ApiResponse( code = 204, message = "TODO: success response" )
	} )
	@RequestMapping(
		method={RequestMethod.POST},value="/unregister"
	)
	public ResponseEntity<HttpStatus> unregister(@RequestBody JiveInstance jiveInstance) {
		logger.log(Level.FINE,"/jive/addon/register called...");
		removeInstance(jiveInstance);
		
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	} // end unregister
	
	private void saveInstance(JiveInstance jiveInstance) {
		if (jiveInstance != null) {
			logger.log(Level.FINE,"Saving Jive Instance["+jiveInstance.getTenantId()+"]...");
			jiveInstanceDAO.save(jiveInstance);			
		} // end if
	} // end saveInstance
	
	private void removeInstance(JiveInstance jiveInstance) {
		if (jiveInstance != null) {
			logger.log(Level.FINE,"Deleting Jive Instance["+jiveInstance.getTenantId()+"]...");
			jiveInstanceDAO.delete(jiveInstance);			
		} // end if
	} // end removeInstance
		
} // end class
