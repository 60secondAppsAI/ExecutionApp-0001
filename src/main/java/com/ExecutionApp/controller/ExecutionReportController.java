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

import com.ExecutionApp.domain.ExecutionReport;
import com.ExecutionApp.dto.ExecutionReportDTO;
import com.ExecutionApp.dto.ExecutionReportSearchDTO;
import com.ExecutionApp.dto.ExecutionReportPageDTO;
import com.ExecutionApp.service.ExecutionReportService;
import com.ExecutionApp.dto.common.RequestDTO;
import com.ExecutionApp.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/executionReport")
@RestController
public class ExecutionReportController {

	private final static Logger logger = LoggerFactory.getLogger(ExecutionReportController.class);

	@Autowired
	ExecutionReportService executionReportService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<ExecutionReport> getAll() {

		List<ExecutionReport> executionReports = executionReportService.findAll();
		
		return executionReports;	
	}

	@GetMapping(value = "/{executionReportId}")
	@ResponseBody
	public ExecutionReportDTO getExecutionReport(@PathVariable Integer executionReportId) {
		
		return (executionReportService.getExecutionReportDTOById(executionReportId));
	}

 	@RequestMapping(value = "/addExecutionReport", method = RequestMethod.POST)
	public ResponseEntity<?> addExecutionReport(@RequestBody ExecutionReportDTO executionReportDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = executionReportService.addExecutionReport(executionReportDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/executionReports")
	public ResponseEntity<ExecutionReportPageDTO> getExecutionReports(ExecutionReportSearchDTO executionReportSearchDTO) {
 
		return executionReportService.getExecutionReports(executionReportSearchDTO);
	}	

	@RequestMapping(value = "/updateExecutionReport", method = RequestMethod.POST)
	public ResponseEntity<?> updateExecutionReport(@RequestBody ExecutionReportDTO executionReportDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = executionReportService.updateExecutionReport(executionReportDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
