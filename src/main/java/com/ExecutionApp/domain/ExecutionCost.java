package com.ExecutionApp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;

@Entity
@Table(name="execution_costs")
@Getter @Setter @NoArgsConstructor
public class ExecutionCost {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="execution_cost_id")
	private Integer executionCostId;
    
  	@Column(name="execution_id")
	private Integer executionId;
    
  	@Column(name="commission")
	private Double commission;
    
  	@Column(name="fees")
	private Double fees;
    
  	@Column(name="slippage")
	private Double slippage;
    

}
