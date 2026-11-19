package com.ExecutionApp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseExecutionDTO {

	private Integer ownerId;

	private Integer executionId;

	private Integer executionStatus;
	
	private Integer nextOrPrevious;
}

