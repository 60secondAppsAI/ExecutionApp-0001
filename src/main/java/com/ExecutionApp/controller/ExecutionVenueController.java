package com.ExecutionApp.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.ZoneId;


import com.ExecutionApp.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.ExecutionApp.domain.ExecutionVenue;
import com.ExecutionApp.dto.ExecutionVenueDTO;
import com.ExecutionApp.dto.ExecutionVenueSearchDTO;
import com.ExecutionApp.dto.ExecutionVenuePageDTO;
import com.ExecutionApp.service.ExecutionVenueService;
import com.ExecutionApp.dto.common.RequestDTO;
import com.ExecutionApp.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/executionVenue")
@RestController
public class ExecutionVenueController {

	private final static Logger logger = LoggerFactory.getLogger(ExecutionVenueController.class);

	@Autowired
	ExecutionVenueService executionVenueService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<ExecutionVenue> getAll() {

		List<ExecutionVenue> executionVenues = executionVenueService.findAll();
		
		return executionVenues;	
	}

	@GetMapping(value = "/{executionVenueId}")
	@ResponseBody
	public ExecutionVenueDTO getExecutionVenue(@PathVariable Integer executionVenueId) {
		
		return (executionVenueService.getExecutionVenueDTOById(executionVenueId));
	}

 	@RequestMapping(value = "/addExecutionVenue", method = RequestMethod.POST)
	public ResponseEntity<?> addExecutionVenue(@RequestBody ExecutionVenueDTO executionVenueDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = executionVenueService.addExecutionVenue(executionVenueDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/executionVenues")
	public ResponseEntity<ExecutionVenuePageDTO> getExecutionVenues(ExecutionVenueSearchDTO executionVenueSearchDTO) {
 
		return executionVenueService.getExecutionVenues(executionVenueSearchDTO);
	}	

	@RequestMapping(value = "/updateExecutionVenue", method = RequestMethod.POST)
	public ResponseEntity<?> updateExecutionVenue(@RequestBody ExecutionVenueDTO executionVenueDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = executionVenueService.updateExecutionVenue(executionVenueDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
