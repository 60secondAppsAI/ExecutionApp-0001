package com.ExecutionApp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseExecutionCostDTO {

	private Integer ownerId;

	private Integer executionCostId;

	private Integer executionCostStatus;
	
	private Integer nextOrPrevious;
}

