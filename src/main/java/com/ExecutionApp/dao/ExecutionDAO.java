package com.ExecutionApp.dao;

import java.util.List;
import java.util.Date;

import com.ExecutionApp.dao.GenericDAO;
import com.ExecutionApp.domain.Execution;




import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface ExecutionDAO extends GenericDAO<Execution, Integer> {
  
	List<Execution> findAll();
	


}

