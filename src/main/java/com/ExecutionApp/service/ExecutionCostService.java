package com.ExecutionApp.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.ExecutionApp.domain.ExecutionCost;
import com.ExecutionApp.dto.ExecutionCostDTO;
import com.ExecutionApp.dto.ExecutionCostSearchDTO;
import com.ExecutionApp.dto.ExecutionCostPageDTO;
import com.ExecutionApp.dto.ExecutionCostConvertCriteriaDTO;
import com.ExecutionApp.service.GenericService;
import com.ExecutionApp.dto.common.RequestDTO;
import com.ExecutionApp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ExecutionCostService extends GenericService<ExecutionCost, Integer> {

	List<ExecutionCost> findAll();

	ResultDTO addExecutionCost(ExecutionCostDTO executionCostDTO, RequestDTO requestDTO);

	ResultDTO updateExecutionCost(ExecutionCostDTO executionCostDTO, RequestDTO requestDTO);

    Page<ExecutionCost> getAllExecutionCosts(Pageable pageable);

    Page<ExecutionCost> getAllExecutionCosts(Specification<ExecutionCost> spec, Pageable pageable);

	ResponseEntity<ExecutionCostPageDTO> getExecutionCosts(ExecutionCostSearchDTO executionCostSearchDTO);
	
	List<ExecutionCostDTO> convertExecutionCostsToExecutionCostDTOs(List<ExecutionCost> executionCosts, ExecutionCostConvertCriteriaDTO convertCriteria);

	ExecutionCostDTO getExecutionCostDTOById(Integer executionCostId);



	
}
