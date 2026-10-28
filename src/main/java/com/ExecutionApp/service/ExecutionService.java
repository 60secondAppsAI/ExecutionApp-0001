package com.ExecutionApp.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.ExecutionApp.domain.Execution;
import com.ExecutionApp.dto.ExecutionDTO;
import com.ExecutionApp.dto.ExecutionSearchDTO;
import com.ExecutionApp.dto.ExecutionPageDTO;
import com.ExecutionApp.dto.ExecutionConvertCriteriaDTO;
import com.ExecutionApp.service.GenericService;
import com.ExecutionApp.dto.common.RequestDTO;
import com.ExecutionApp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ExecutionService extends GenericService<Execution, Integer> {

	List<Execution> findAll();

	ResultDTO addExecution(ExecutionDTO executionDTO, RequestDTO requestDTO);

	ResultDTO updateExecution(ExecutionDTO executionDTO, RequestDTO requestDTO);

    Page<Execution> getAllExecutions(Pageable pageable);

    Page<Execution> getAllExecutions(Specification<Execution> spec, Pageable pageable);

	ResponseEntity<ExecutionPageDTO> getExecutions(ExecutionSearchDTO executionSearchDTO);
	
	List<ExecutionDTO> convertExecutionsToExecutionDTOs(List<Execution> executions, ExecutionConvertCriteriaDTO convertCriteria);

	ExecutionDTO getExecutionDTOById(Integer executionId);



	
}
