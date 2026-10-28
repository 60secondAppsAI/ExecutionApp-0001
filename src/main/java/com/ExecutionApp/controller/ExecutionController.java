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

import com.ExecutionApp.domain.Execution;
import com.ExecutionApp.dto.ExecutionDTO;
import com.ExecutionApp.dto.ExecutionSearchDTO;
import com.ExecutionApp.dto.ExecutionPageDTO;
import com.ExecutionApp.service.ExecutionService;
import com.ExecutionApp.dto.common.RequestDTO;
import com.ExecutionApp.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/execution")
@RestController
public class ExecutionController {

	private final static Logger logger = LoggerFactory.getLogger(ExecutionController.class);

	@Autowired
	ExecutionService executionService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Execution> getAll() {

		List<Execution> executions = executionService.findAll();
		
		return executions;	
	}

	@GetMapping(value = "/{executionId}")
	@ResponseBody
	public ExecutionDTO getExecution(@PathVariable Integer executionId) {
		
		return (executionService.getExecutionDTOById(executionId));
	}

 	@RequestMapping(value = "/addExecution", method = RequestMethod.POST)
	public ResponseEntity<?> addExecution(@RequestBody ExecutionDTO executionDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = executionService.addExecution(executionDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/executions")
	public ResponseEntity<ExecutionPageDTO> getExecutions(ExecutionSearchDTO executionSearchDTO) {
 
		return executionService.getExecutions(executionSearchDTO);
	}	

	@RequestMapping(value = "/updateExecution", method = RequestMethod.POST)
	public ResponseEntity<?> updateExecution(@RequestBody ExecutionDTO executionDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = executionService.updateExecution(executionDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
