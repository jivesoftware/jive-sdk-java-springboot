package com.jivesoftware.sdk.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/_ah")
public class AppEngineLifecycleController {

	@RequestMapping("/start")
	public ResponseEntity<String> start() {
		return new ResponseEntity<String>("OK",HttpStatus.OK);
	} // end start
	
	@RequestMapping("/health")
	public ResponseEntity<String> health() {
		return new ResponseEntity<String>("OK",HttpStatus.OK);
	} // end health
	
} // end class
