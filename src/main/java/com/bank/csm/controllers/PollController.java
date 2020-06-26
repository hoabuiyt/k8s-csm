package com.bank.csm.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.csm.constants.BuildConstants;
import com.bank.csm.constants.URLConstants;


/**
 * The controller provides end points for providing polling
 * capability to the application.
 *
 * @author kumar-sand
 */
@RestController
@RequestMapping(URLConstants.RESOURCE_COMMON + URLConstants.VERSION)
public class PollController {
	
	Logger logger = Logger.getLogger(PollController.class.getName());
	
	
	/**
	 * Poll end point.
	 *
	 * @return It return string
	 */
	@GetMapping(URLConstants.RESOURCE_COMMON_POLL)
	public String poll() {
		logger.info("Poll executed");
		return "yes";
	}
	
	@GetMapping(URLConstants.RESOURCE_COMMON_BUILDINFO)
	public ResponseEntity<Map<String, String>> getBuildInfo() {
		logger.info("Build Info - Start");
		Map<String, String> tmp = new HashMap<String, String>();
		tmp.put("version", BuildConstants.VERSION);
		tmp.put("build", BuildConstants.BUILD);
		logger.info("Build Info - End");
		return new ResponseEntity<>(tmp, HttpStatus.OK);
	}
	
}
