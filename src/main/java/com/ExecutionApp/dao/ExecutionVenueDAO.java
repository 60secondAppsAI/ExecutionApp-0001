package com.ExecutionApp.dao;

import java.util.List;
import java.util.Date;

import com.ExecutionApp.dao.GenericDAO;
import com.ExecutionApp.domain.ExecutionVenue;




import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface ExecutionVenueDAO extends GenericDAO<ExecutionVenue, Integer> {
  
	List<ExecutionVenue> findAll();
	


}

