package com.ExecutionApp.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class ExecutionDTO {

	private Integer executionId;

	private Integer orderId;

	private Integer instrumentId;

	private Double quantity;

	private Double price;

	private Date executedAt;






}
