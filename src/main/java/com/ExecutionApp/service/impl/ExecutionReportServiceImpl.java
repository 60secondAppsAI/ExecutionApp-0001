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
import com.ExecutionApp.dao.ExecutionReportDAO;
import com.ExecutionApp.domain.ExecutionReport;
import com.ExecutionApp.dto.ExecutionReportDTO;
import com.ExecutionApp.dto.ExecutionReportSearchDTO;
import com.ExecutionApp.dto.ExecutionReportPageDTO;
import com.ExecutionApp.dto.ExecutionReportConvertCriteriaDTO;
import com.ExecutionApp.dto.common.RequestDTO;
import com.ExecutionApp.dto.common.ResultDTO;
import com.ExecutionApp.service.ExecutionReportService;
import com.ExecutionApp.util.ControllerUtils;


@Service
public class ExecutionReportServiceImpl extends GenericServiceImpl<ExecutionReport, Integer> implements ExecutionReportService {

    private final static Logger logger = LoggerFactory.getLogger(ExecutionReportServiceImpl.class);

	@Autowired
	ExecutionReportDAO executionReportDao;

	

	@Override
	public GenericDAO<ExecutionReport, Integer> getDAO() {
		return (GenericDAO<ExecutionReport, Integer>) executionReportDao;
	}
	
	public List<ExecutionReport> findAll () {
		List<ExecutionReport> executionReports = executionReportDao.findAll();
		
		return executionReports;	
		
	}

	public ResultDTO addExecutionReport(ExecutionReportDTO executionReportDTO, RequestDTO requestDTO) {

		ExecutionReport executionReport = new ExecutionReport();

		executionReport.setExecutionReportId(executionReportDTO.getExecutionReportId());

		executionReport.setExecutionId(executionReportDTO.getExecutionId());

		executionReport.setReportType(executionReportDTO.getReportType());

		executionReport.setStatus(executionReportDTO.getStatus());

		executionReport.setReceivedAt(executionReportDTO.getReceivedAt());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		executionReport = executionReportDao.save(executionReport);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<ExecutionReport> getAllExecutionReports(Pageable pageable) {
		return executionReportDao.findAll(pageable);
	}

	public Page<ExecutionReport> getAllExecutionReports(Specification<ExecutionReport> spec, Pageable pageable) {
		return executionReportDao.findAll(spec, pageable);
	}

	public ResponseEntity<ExecutionReportPageDTO> getExecutionReports(ExecutionReportSearchDTO executionReportSearchDTO) {
	
			Integer executionReportId = executionReportSearchDTO.getExecutionReportId(); 
 			Integer executionId = executionReportSearchDTO.getExecutionId(); 
 			String reportType = executionReportSearchDTO.getReportType(); 
 			String status = executionReportSearchDTO.getStatus(); 
 			Date receivedAt = executionReportSearchDTO.getReceivedAt();
 			String sortBy = executionReportSearchDTO.getSortBy();
			String sortOrder = executionReportSearchDTO.getSortOrder();
			String searchQuery = executionReportSearchDTO.getSearchQuery();
			Integer page = executionReportSearchDTO.getPage();
			Integer size = executionReportSearchDTO.getSize();

	        Specification<ExecutionReport> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, executionReportId, "executionReportId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, executionId, "executionId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, reportType, "reportType"); 
			
			spec = ControllerUtils.andIfNecessary(spec, status, "status"); 
			
			spec = ControllerUtils.andIfNecessary(spec, receivedAt, "receivedAt");
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("reportType")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("status")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<ExecutionReport> executionReports = this.getAllExecutionReports(spec, pageable);
		
		//System.out.println(String.valueOf(executionReports.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(executionReports.getTotalPages()));
		
		List<ExecutionReport> executionReportsList = executionReports.getContent();
		
		ExecutionReportConvertCriteriaDTO convertCriteria = new ExecutionReportConvertCriteriaDTO();
		List<ExecutionReportDTO> executionReportDTOs = this.convertExecutionReportsToExecutionReportDTOs(executionReportsList,convertCriteria);
		
		ExecutionReportPageDTO executionReportPageDTO = new ExecutionReportPageDTO();
		executionReportPageDTO.setExecutionReports(executionReportDTOs);
		executionReportPageDTO.setTotalElements(executionReports.getTotalElements());
		return ResponseEntity.ok(executionReportPageDTO);
	}

	public List<ExecutionReportDTO> convertExecutionReportsToExecutionReportDTOs(List<ExecutionReport> executionReports, ExecutionReportConvertCriteriaDTO convertCriteria) {
		
		List<ExecutionReportDTO> executionReportDTOs = new ArrayList<ExecutionReportDTO>();
		
		for (ExecutionReport executionReport : executionReports) {
			executionReportDTOs.add(convertExecutionReportToExecutionReportDTO(executionReport,convertCriteria));
		}
		
		return executionReportDTOs;

	}
	
	public ExecutionReportDTO convertExecutionReportToExecutionReportDTO(ExecutionReport executionReport, ExecutionReportConvertCriteriaDTO convertCriteria) {
		
		ExecutionReportDTO executionReportDTO = new ExecutionReportDTO();

		executionReportDTO.setExecutionReportId(executionReport.getExecutionReportId());

		executionReportDTO.setExecutionId(executionReport.getExecutionId());

		executionReportDTO.setReportType(executionReport.getReportType());

		executionReportDTO.setStatus(executionReport.getStatus());

		executionReportDTO.setReceivedAt(executionReport.getReceivedAt());
		
		return executionReportDTO;
	}

	public ResultDTO updateExecutionReport(ExecutionReportDTO executionReportDTO, RequestDTO requestDTO) {
		
		ExecutionReport executionReport = executionReportDao.getById(executionReportDTO.getExecutionReportId());
		
		executionReport.setExecutionReportId(ControllerUtils.setValue(executionReport.getExecutionReportId(), executionReportDTO.getExecutionReportId()));
		
		executionReport.setExecutionId(ControllerUtils.setValue(executionReport.getExecutionId(), executionReportDTO.getExecutionId()));
		
		executionReport.setReportType(ControllerUtils.setValue(executionReport.getReportType(), executionReportDTO.getReportType()));
		
		executionReport.setStatus(ControllerUtils.setValue(executionReport.getStatus(), executionReportDTO.getStatus()));
		
		executionReport.setReceivedAt(ControllerUtils.setValue(executionReport.getReceivedAt(), executionReportDTO.getReceivedAt()));

        executionReport = executionReportDao.save(executionReport);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ExecutionReportDTO getExecutionReportDTOById(Integer executionReportId) {
	
		ExecutionReport executionReport = executionReportDao.getById(executionReportId);
		
		ExecutionReportConvertCriteriaDTO convertCriteria = new ExecutionReportConvertCriteriaDTO();
		return(this.convertExecutionReportToExecutionReportDTO(executionReport,convertCriteria));
	}

}
