package com.ExecutionApp.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class ExecutionReportDTO {

	private Integer executionReportId;

	private Integer executionId;

	private String reportType;

	private String status;

	private Date receivedAt;






}
