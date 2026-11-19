package com.ExecutionApp.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.ExecutionApp.domain.ExecutionVenue;
import com.ExecutionApp.dto.ExecutionVenueDTO;
import com.ExecutionApp.dto.ExecutionVenueSearchDTO;
import com.ExecutionApp.dto.ExecutionVenuePageDTO;
import com.ExecutionApp.dto.ExecutionVenueConvertCriteriaDTO;
import com.ExecutionApp.service.GenericService;
import com.ExecutionApp.dto.common.RequestDTO;
import com.ExecutionApp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ExecutionVenueService extends GenericService<ExecutionVenue, Integer> {

	List<ExecutionVenue> findAll();

	ResultDTO addExecutionVenue(ExecutionVenueDTO executionVenueDTO, RequestDTO requestDTO);

	ResultDTO updateExecutionVenue(ExecutionVenueDTO executionVenueDTO, RequestDTO requestDTO);

    Page<ExecutionVenue> getAllExecutionVenues(Pageable pageable);

    Page<ExecutionVenue> getAllExecutionVenues(Specification<ExecutionVenue> spec, Pageable pageable);

	ResponseEntity<ExecutionVenuePageDTO> getExecutionVenues(ExecutionVenueSearchDTO executionVenueSearchDTO);
	
	List<ExecutionVenueDTO> convertExecutionVenuesToExecutionVenueDTOs(List<ExecutionVenue> executionVenues, ExecutionVenueConvertCriteriaDTO convertCriteria);

	ExecutionVenueDTO getExecutionVenueDTOById(Integer executionVenueId);



	
}
