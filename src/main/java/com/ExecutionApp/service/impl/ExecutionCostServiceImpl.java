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
import com.ExecutionApp.dao.ExecutionCostDAO;
import com.ExecutionApp.domain.ExecutionCost;
import com.ExecutionApp.dto.ExecutionCostDTO;
import com.ExecutionApp.dto.ExecutionCostSearchDTO;
import com.ExecutionApp.dto.ExecutionCostPageDTO;
import com.ExecutionApp.dto.ExecutionCostConvertCriteriaDTO;
import com.ExecutionApp.dto.common.RequestDTO;
import com.ExecutionApp.dto.common.ResultDTO;
import com.ExecutionApp.service.ExecutionCostService;
import com.ExecutionApp.util.ControllerUtils;


@Service
public class ExecutionCostServiceImpl extends GenericServiceImpl<ExecutionCost, Integer> implements ExecutionCostService {

    private final static Logger logger = LoggerFactory.getLogger(ExecutionCostServiceImpl.class);

	@Autowired
	ExecutionCostDAO executionCostDao;

	

	@Override
	public GenericDAO<ExecutionCost, Integer> getDAO() {
		return (GenericDAO<ExecutionCost, Integer>) executionCostDao;
	}
	
	public List<ExecutionCost> findAll () {
		List<ExecutionCost> executionCosts = executionCostDao.findAll();
		
		return executionCosts;	
		
	}

	public ResultDTO addExecutionCost(ExecutionCostDTO executionCostDTO, RequestDTO requestDTO) {

		ExecutionCost executionCost = new ExecutionCost();

		executionCost.setExecutionCostId(executionCostDTO.getExecutionCostId());

		executionCost.setExecutionId(executionCostDTO.getExecutionId());

		executionCost.setCommission(executionCostDTO.getCommission());

		executionCost.setFees(executionCostDTO.getFees());

		executionCost.setSlippage(executionCostDTO.getSlippage());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		executionCost = executionCostDao.save(executionCost);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<ExecutionCost> getAllExecutionCosts(Pageable pageable) {
		return executionCostDao.findAll(pageable);
	}

	public Page<ExecutionCost> getAllExecutionCosts(Specification<ExecutionCost> spec, Pageable pageable) {
		return executionCostDao.findAll(spec, pageable);
	}

	public ResponseEntity<ExecutionCostPageDTO> getExecutionCosts(ExecutionCostSearchDTO executionCostSearchDTO) {
	
			Integer executionCostId = executionCostSearchDTO.getExecutionCostId(); 
 			Integer executionId = executionCostSearchDTO.getExecutionId(); 
    			String sortBy = executionCostSearchDTO.getSortBy();
			String sortOrder = executionCostSearchDTO.getSortOrder();
			String searchQuery = executionCostSearchDTO.getSearchQuery();
			Integer page = executionCostSearchDTO.getPage();
			Integer size = executionCostSearchDTO.getSize();

	        Specification<ExecutionCost> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, executionCostId, "executionCostId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, executionId, "executionId"); 
			
			
			
			

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

		Page<ExecutionCost> executionCosts = this.getAllExecutionCosts(spec, pageable);
		
		//System.out.println(String.valueOf(executionCosts.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(executionCosts.getTotalPages()));
		
		List<ExecutionCost> executionCostsList = executionCosts.getContent();
		
		ExecutionCostConvertCriteriaDTO convertCriteria = new ExecutionCostConvertCriteriaDTO();
		List<ExecutionCostDTO> executionCostDTOs = this.convertExecutionCostsToExecutionCostDTOs(executionCostsList,convertCriteria);
		
		ExecutionCostPageDTO executionCostPageDTO = new ExecutionCostPageDTO();
		executionCostPageDTO.setExecutionCosts(executionCostDTOs);
		executionCostPageDTO.setTotalElements(executionCosts.getTotalElements());
		return ResponseEntity.ok(executionCostPageDTO);
	}

	public List<ExecutionCostDTO> convertExecutionCostsToExecutionCostDTOs(List<ExecutionCost> executionCosts, ExecutionCostConvertCriteriaDTO convertCriteria) {
		
		List<ExecutionCostDTO> executionCostDTOs = new ArrayList<ExecutionCostDTO>();
		
		for (ExecutionCost executionCost : executionCosts) {
			executionCostDTOs.add(convertExecutionCostToExecutionCostDTO(executionCost,convertCriteria));
		}
		
		return executionCostDTOs;

	}
	
	public ExecutionCostDTO convertExecutionCostToExecutionCostDTO(ExecutionCost executionCost, ExecutionCostConvertCriteriaDTO convertCriteria) {
		
		ExecutionCostDTO executionCostDTO = new ExecutionCostDTO();

		executionCostDTO.setExecutionCostId(executionCost.getExecutionCostId());

		executionCostDTO.setExecutionId(executionCost.getExecutionId());

		executionCostDTO.setCommission(executionCost.getCommission());

		executionCostDTO.setFees(executionCost.getFees());

		executionCostDTO.setSlippage(executionCost.getSlippage());
		
		return executionCostDTO;
	}

	public ResultDTO updateExecutionCost(ExecutionCostDTO executionCostDTO, RequestDTO requestDTO) {
		
		ExecutionCost executionCost = executionCostDao.getById(executionCostDTO.getExecutionCostId());
		
		executionCost.setExecutionCostId(ControllerUtils.setValue(executionCost.getExecutionCostId(), executionCostDTO.getExecutionCostId()));
		
		executionCost.setExecutionId(ControllerUtils.setValue(executionCost.getExecutionId(), executionCostDTO.getExecutionId()));
		
		executionCost.setCommission(ControllerUtils.setValue(executionCost.getCommission(), executionCostDTO.getCommission()));
		
		executionCost.setFees(ControllerUtils.setValue(executionCost.getFees(), executionCostDTO.getFees()));
		
		executionCost.setSlippage(ControllerUtils.setValue(executionCost.getSlippage(), executionCostDTO.getSlippage()));

        executionCost = executionCostDao.save(executionCost);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ExecutionCostDTO getExecutionCostDTOById(Integer executionCostId) {
	
		ExecutionCost executionCost = executionCostDao.getById(executionCostId);
		
		ExecutionCostConvertCriteriaDTO convertCriteria = new ExecutionCostConvertCriteriaDTO();
		return(this.convertExecutionCostToExecutionCostDTO(executionCost,convertCriteria));
	}

}
