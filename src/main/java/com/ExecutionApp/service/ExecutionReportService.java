package com.ExecutionApp.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.ExecutionApp.domain.ExecutionReport;
import com.ExecutionApp.dto.ExecutionReportDTO;
import com.ExecutionApp.dto.ExecutionReportSearchDTO;
import com.ExecutionApp.dto.ExecutionReportPageDTO;
import com.ExecutionApp.dto.ExecutionReportConvertCriteriaDTO;
import com.ExecutionApp.service.GenericService;
import com.ExecutionApp.dto.common.RequestDTO;
import com.ExecutionApp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ExecutionReportService extends GenericService<ExecutionReport, Integer> {

	List<ExecutionReport> findAll();

	ResultDTO addExecutionReport(ExecutionReportDTO executionReportDTO, RequestDTO requestDTO);

	ResultDTO updateExecutionReport(ExecutionReportDTO executionReportDTO, RequestDTO requestDTO);

    Page<ExecutionReport> getAllExecutionReports(Pageable pageable);

    Page<ExecutionReport> getAllExecutionReports(Specification<ExecutionReport> spec, Pageable pageable);

	ResponseEntity<ExecutionReportPageDTO> getExecutionReports(ExecutionReportSearchDTO executionReportSearchDTO);
	
	List<ExecutionReportDTO> convertExecutionReportsToExecutionReportDTOs(List<ExecutionReport> executionReports, ExecutionReportConvertCriteriaDTO convertCriteria);

	ExecutionReportDTO getExecutionReportDTOById(Integer executionReportId);



	
}
