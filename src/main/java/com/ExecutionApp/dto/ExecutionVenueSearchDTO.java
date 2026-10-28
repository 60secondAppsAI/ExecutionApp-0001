package com.ExecutionApp.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ExecutionVenueSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer executionVenueId;
	
	private Integer executionId;
	
	private Integer exchangeId;
	
	private String venueCode;
	
}
