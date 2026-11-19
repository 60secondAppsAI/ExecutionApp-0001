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
import com.ExecutionApp.dao.ExecutionVenueDAO;
import com.ExecutionApp.domain.ExecutionVenue;
import com.ExecutionApp.dto.ExecutionVenueDTO;
import com.ExecutionApp.dto.ExecutionVenueSearchDTO;
import com.ExecutionApp.dto.ExecutionVenuePageDTO;
import com.ExecutionApp.dto.ExecutionVenueConvertCriteriaDTO;
import com.ExecutionApp.dto.common.RequestDTO;
import com.ExecutionApp.dto.common.ResultDTO;
import com.ExecutionApp.service.ExecutionVenueService;
import com.ExecutionApp.util.ControllerUtils;


@Service
public class ExecutionVenueServiceImpl extends GenericServiceImpl<ExecutionVenue, Integer> implements ExecutionVenueService {

    private final static Logger logger = LoggerFactory.getLogger(ExecutionVenueServiceImpl.class);

	@Autowired
	ExecutionVenueDAO executionVenueDao;

	

	@Override
	public GenericDAO<ExecutionVenue, Integer> getDAO() {
		return (GenericDAO<ExecutionVenue, Integer>) executionVenueDao;
	}
	
	public List<ExecutionVenue> findAll () {
		List<ExecutionVenue> executionVenues = executionVenueDao.findAll();
		
		return executionVenues;	
		
	}

	public ResultDTO addExecutionVenue(ExecutionVenueDTO executionVenueDTO, RequestDTO requestDTO) {

		ExecutionVenue executionVenue = new ExecutionVenue();

		executionVenue.setExecutionVenueId(executionVenueDTO.getExecutionVenueId());

		executionVenue.setExecutionId(executionVenueDTO.getExecutionId());

		executionVenue.setExchangeId(executionVenueDTO.getExchangeId());

		executionVenue.setVenueCode(executionVenueDTO.getVenueCode());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		executionVenue = executionVenueDao.save(executionVenue);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<ExecutionVenue> getAllExecutionVenues(Pageable pageable) {
		return executionVenueDao.findAll(pageable);
	}

	public Page<ExecutionVenue> getAllExecutionVenues(Specification<ExecutionVenue> spec, Pageable pageable) {
		return executionVenueDao.findAll(spec, pageable);
	}

	public ResponseEntity<ExecutionVenuePageDTO> getExecutionVenues(ExecutionVenueSearchDTO executionVenueSearchDTO) {
	
			Integer executionVenueId = executionVenueSearchDTO.getExecutionVenueId(); 
 			Integer executionId = executionVenueSearchDTO.getExecutionId(); 
 			Integer exchangeId = executionVenueSearchDTO.getExchangeId(); 
 			String venueCode = executionVenueSearchDTO.getVenueCode(); 
 			String sortBy = executionVenueSearchDTO.getSortBy();
			String sortOrder = executionVenueSearchDTO.getSortOrder();
			String searchQuery = executionVenueSearchDTO.getSearchQuery();
			Integer page = executionVenueSearchDTO.getPage();
			Integer size = executionVenueSearchDTO.getSize();

	        Specification<ExecutionVenue> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, executionVenueId, "executionVenueId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, executionId, "executionId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, exchangeId, "exchangeId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, venueCode, "venueCode"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("venueCode")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<ExecutionVenue> executionVenues = this.getAllExecutionVenues(spec, pageable);
		
		//System.out.println(String.valueOf(executionVenues.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(executionVenues.getTotalPages()));
		
		List<ExecutionVenue> executionVenuesList = executionVenues.getContent();
		
		ExecutionVenueConvertCriteriaDTO convertCriteria = new ExecutionVenueConvertCriteriaDTO();
		List<ExecutionVenueDTO> executionVenueDTOs = this.convertExecutionVenuesToExecutionVenueDTOs(executionVenuesList,convertCriteria);
		
		ExecutionVenuePageDTO executionVenuePageDTO = new ExecutionVenuePageDTO();
		executionVenuePageDTO.setExecutionVenues(executionVenueDTOs);
		executionVenuePageDTO.setTotalElements(executionVenues.getTotalElements());
		return ResponseEntity.ok(executionVenuePageDTO);
	}

	public List<ExecutionVenueDTO> convertExecutionVenuesToExecutionVenueDTOs(List<ExecutionVenue> executionVenues, ExecutionVenueConvertCriteriaDTO convertCriteria) {
		
		List<ExecutionVenueDTO> executionVenueDTOs = new ArrayList<ExecutionVenueDTO>();
		
		for (ExecutionVenue executionVenue : executionVenues) {
			executionVenueDTOs.add(convertExecutionVenueToExecutionVenueDTO(executionVenue,convertCriteria));
		}
		
		return executionVenueDTOs;

	}
	
	public ExecutionVenueDTO convertExecutionVenueToExecutionVenueDTO(ExecutionVenue executionVenue, ExecutionVenueConvertCriteriaDTO convertCriteria) {
		
		ExecutionVenueDTO executionVenueDTO = new ExecutionVenueDTO();

		executionVenueDTO.setExecutionVenueId(executionVenue.getExecutionVenueId());

		executionVenueDTO.setExecutionId(executionVenue.getExecutionId());

		executionVenueDTO.setExchangeId(executionVenue.getExchangeId());

		executionVenueDTO.setVenueCode(executionVenue.getVenueCode());
		
		return executionVenueDTO;
	}

	public ResultDTO updateExecutionVenue(ExecutionVenueDTO executionVenueDTO, RequestDTO requestDTO) {
		
		ExecutionVenue executionVenue = executionVenueDao.getById(executionVenueDTO.getExecutionVenueId());
		
		executionVenue.setExecutionVenueId(ControllerUtils.setValue(executionVenue.getExecutionVenueId(), executionVenueDTO.getExecutionVenueId()));
		
		executionVenue.setExecutionId(ControllerUtils.setValue(executionVenue.getExecutionId(), executionVenueDTO.getExecutionId()));
		
		executionVenue.setExchangeId(ControllerUtils.setValue(executionVenue.getExchangeId(), executionVenueDTO.getExchangeId()));
		
		executionVenue.setVenueCode(ControllerUtils.setValue(executionVenue.getVenueCode(), executionVenueDTO.getVenueCode()));

        executionVenue = executionVenueDao.save(executionVenue);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ExecutionVenueDTO getExecutionVenueDTOById(Integer executionVenueId) {
	
		ExecutionVenue executionVenue = executionVenueDao.getById(executionVenueId);
		
		ExecutionVenueConvertCriteriaDTO convertCriteria = new ExecutionVenueConvertCriteriaDTO();
		return(this.convertExecutionVenueToExecutionVenueDTO(executionVenue,convertCriteria));
	}

}
