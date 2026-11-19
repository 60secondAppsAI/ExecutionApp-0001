package com.ExecutionApp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseExecutionVenueDTO {

	private Integer ownerId;

	private Integer executionVenueId;

	private Integer executionVenueStatus;
	
	private Integer nextOrPrevious;
}

