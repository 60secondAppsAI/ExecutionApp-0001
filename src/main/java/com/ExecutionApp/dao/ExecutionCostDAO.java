package com.ExecutionApp.dao;

import java.util.List;
import java.util.Date;

import com.ExecutionApp.dao.GenericDAO;
import com.ExecutionApp.domain.ExecutionCost;




import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface ExecutionCostDAO extends GenericDAO<ExecutionCost, Integer> {
  
	List<ExecutionCost> findAll();
	


}

