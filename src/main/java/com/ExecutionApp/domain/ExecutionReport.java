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
@Table(name="execution_reports")
@Getter @Setter @NoArgsConstructor
public class ExecutionReport {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="execution_report_id")
	private Integer executionReportId;
    
  	@Column(name="execution_id")
	private Integer executionId;
    
  	@Column(name="report_type")
	private String reportType;
    
  	@Column(name="status")
	private String status;
    
  	@Column(name="received_at")
	private Date receivedAt;
    

}
