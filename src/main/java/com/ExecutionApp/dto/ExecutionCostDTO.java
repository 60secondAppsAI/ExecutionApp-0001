package com.ExecutionApp.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class ExecutionCostDTO {

	private Integer executionCostId;

	private Integer executionId;

	private Double commission;

	private Double fees;

	private Double slippage;






}
