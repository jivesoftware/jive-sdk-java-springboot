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

import com.jivesoftware.sdk.dao.TileInstanceDAO;
import com.jivesoftware.sdk.dao.entity.TileInstance;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/jive/tile")
public class JiveTileController {
	private static final Logger logger = Logger.getLogger(JiveTileController.class.getName());

	@Autowired
	private TileInstanceDAO tileInstanceDAO;	
		
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
	public ResponseEntity<HttpStatus> register(@RequestBody TileInstance tileInstance) {
		saveInstance(tileInstance);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
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
		public ResponseEntity<HttpStatus> unregister(@RequestBody TileInstance tileInstance) {
			removeInstance(tileInstance);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} // end unregister
	
	private void saveInstance(TileInstance tileInstance) {
		if (tileInstance != null) {
			logger.log(Level.FINE,"Saving Tile Instance["+tileInstance.getGlobalTileInstanceId()+"] for Tenant["+tileInstance.getTenantId()+"]...");
			tileInstanceDAO.save(tileInstance);			
		} // end if
	} // end saveInstance
	
	private void removeInstance(TileInstance tileInstance) {
		if (tileInstance != null) {
			logger.log(Level.FINE,"Deleting Tile Instance["+tileInstance.getGlobalTileInstanceId()+"] for Tenant["+tileInstance.getTenantId()+"]...");
			tileInstanceDAO.delete(tileInstance);			
		} // end if
	} // end removeInstance
	
} // end class
