package com.ExecutionApp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseExecutionReportDTO {

	private Integer ownerId;

	private Integer executionReportId;

	private Integer executionReportStatus;
	
	private Integer nextOrPrevious;
}

