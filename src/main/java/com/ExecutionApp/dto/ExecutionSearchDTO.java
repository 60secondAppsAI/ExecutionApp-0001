package com.ExecutionApp.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ExecutionSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer executionId;
	
	private Integer orderId;
	
	private Integer instrumentId;
	
	private Double quantity;
	
	private Double price;
	
	private Date executedAt;
	
}
