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
@Table(name="execution_venues")
@Getter @Setter @NoArgsConstructor
public class ExecutionVenue {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="execution_venue_id")
	private Integer executionVenueId;
    
  	@Column(name="execution_id")
	private Integer executionId;
    
  	@Column(name="exchange_id")
	private Integer exchangeId;
    
  	@Column(name="venue_code")
	private String venueCode;
    

}
