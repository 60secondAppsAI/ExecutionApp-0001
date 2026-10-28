package com.ExecutionApp.dao;

import java.util.List;
import java.util.Date;

import com.ExecutionApp.dao.GenericDAO;
import com.ExecutionApp.domain.ExecutionReport;




import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface ExecutionReportDAO extends GenericDAO<ExecutionReport, Integer> {
  
	List<ExecutionReport> findAll();
	


}

