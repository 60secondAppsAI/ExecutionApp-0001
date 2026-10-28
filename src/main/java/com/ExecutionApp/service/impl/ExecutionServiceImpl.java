package com.ExecutionApp.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import com.ExecutionApp.dao.GenericDAO;
import com.ExecutionApp.service.GenericService;
import com.ExecutionApp.service.impl.GenericServiceImpl;
import com.ExecutionApp.dao.ExecutionDAO;
import com.ExecutionApp.domain.Execution;
import com.ExecutionApp.dto.ExecutionDTO;
import com.ExecutionApp.dto.ExecutionSearchDTO;
import com.ExecutionApp.dto.ExecutionPageDTO;
import com.ExecutionApp.dto.ExecutionConvertCriteriaDTO;
import com.ExecutionApp.dto.common.RequestDTO;
import com.ExecutionApp.dto.common.ResultDTO;
import com.ExecutionApp.service.ExecutionService;
import com.ExecutionApp.util.ControllerUtils;


@Service
public class ExecutionServiceImpl extends GenericServiceImpl<Execution, Integer> implements ExecutionService {

    private final static Logger logger = LoggerFactory.getLogger(ExecutionServiceImpl.class);

	@Autowired
	ExecutionDAO executionDao;

	

	@Override
	public GenericDAO<Execution, Integer> getDAO() {
		return (GenericDAO<Execution, Integer>) executionDao;
	}
	
	public List<Execution> findAll () {
		List<Execution> executions = executionDao.findAll();
		
		return executions;	
		
	}

	public ResultDTO addExecution(ExecutionDTO executionDTO, RequestDTO requestDTO) {

		Execution execution = new Execution();

		execution.setExecutionId(executionDTO.getExecutionId());

		execution.setOrderId(executionDTO.getOrderId());

		execution.setInstrumentId(executionDTO.getInstrumentId());

		execution.setQuantity(executionDTO.getQuantity());

		execution.setPrice(executionDTO.getPrice());

		execution.setExecutedAt(executionDTO.getExecutedAt());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		execution = executionDao.save(execution);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Execution> getAllExecutions(Pageable pageable) {
		return executionDao.findAll(pageable);
	}

	public Page<Execution> getAllExecutions(Specification<Execution> spec, Pageable pageable) {
		return executionDao.findAll(spec, pageable);
	}

	public ResponseEntity<ExecutionPageDTO> getExecutions(ExecutionSearchDTO executionSearchDTO) {
	
			Integer executionId = executionSearchDTO.getExecutionId(); 
 			Integer orderId = executionSearchDTO.getOrderId(); 
 			Integer instrumentId = executionSearchDTO.getInstrumentId(); 
   			Date executedAt = executionSearchDTO.getExecutedAt();
 			String sortBy = executionSearchDTO.getSortBy();
			String sortOrder = executionSearchDTO.getSortOrder();
			String searchQuery = executionSearchDTO.getSearchQuery();
			Integer page = executionSearchDTO.getPage();
			Integer size = executionSearchDTO.getSize();

	        Specification<Execution> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, executionId, "executionId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, orderId, "orderId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, instrumentId, "instrumentId"); 
			
			
			
			spec = ControllerUtils.andIfNecessary(spec, executedAt, "executedAt");
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Execution> executions = this.getAllExecutions(spec, pageable);
		
		//System.out.println(String.valueOf(executions.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(executions.getTotalPages()));
		
		List<Execution> executionsList = executions.getContent();
		
		ExecutionConvertCriteriaDTO convertCriteria = new ExecutionConvertCriteriaDTO();
		List<ExecutionDTO> executionDTOs = this.convertExecutionsToExecutionDTOs(executionsList,convertCriteria);
		
		ExecutionPageDTO executionPageDTO = new ExecutionPageDTO();
		executionPageDTO.setExecutions(executionDTOs);
		executionPageDTO.setTotalElements(executions.getTotalElements());
		return ResponseEntity.ok(executionPageDTO);
	}

	public List<ExecutionDTO> convertExecutionsToExecutionDTOs(List<Execution> executions, ExecutionConvertCriteriaDTO convertCriteria) {
		
		List<ExecutionDTO> executionDTOs = new ArrayList<ExecutionDTO>();
		
		for (Execution execution : executions) {
			executionDTOs.add(convertExecutionToExecutionDTO(execution,convertCriteria));
		}
		
		return executionDTOs;

	}
	
	public ExecutionDTO convertExecutionToExecutionDTO(Execution execution, ExecutionConvertCriteriaDTO convertCriteria) {
		
		ExecutionDTO executionDTO = new ExecutionDTO();

		executionDTO.setExecutionId(execution.getExecutionId());

		executionDTO.setOrderId(execution.getOrderId());

		executionDTO.setInstrumentId(execution.getInstrumentId());

		executionDTO.setQuantity(execution.getQuantity());

		executionDTO.setPrice(execution.getPrice());

		executionDTO.setExecutedAt(execution.getExecutedAt());
		
		return executionDTO;
	}

	public ResultDTO updateExecution(ExecutionDTO executionDTO, RequestDTO requestDTO) {
		
		Execution execution = executionDao.getById(executionDTO.getExecutionId());
		
		execution.setExecutionId(ControllerUtils.setValue(execution.getExecutionId(), executionDTO.getExecutionId()));
		
		execution.setOrderId(ControllerUtils.setValue(execution.getOrderId(), executionDTO.getOrderId()));
		
		execution.setInstrumentId(ControllerUtils.setValue(execution.getInstrumentId(), executionDTO.getInstrumentId()));
		
		execution.setQuantity(ControllerUtils.setValue(execution.getQuantity(), executionDTO.getQuantity()));
		
		execution.setPrice(ControllerUtils.setValue(execution.getPrice(), executionDTO.getPrice()));
		
		execution.setExecutedAt(ControllerUtils.setValue(execution.getExecutedAt(), executionDTO.getExecutedAt()));

        execution = executionDao.save(execution);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ExecutionDTO getExecutionDTOById(Integer executionId) {
	
		Execution execution = executionDao.getById(executionId);
		
		ExecutionConvertCriteriaDTO convertCriteria = new ExecutionConvertCriteriaDTO();
		return(this.convertExecutionToExecutionDTO(execution,convertCriteria));
	}

}
