package ca.sheridancollege.acevedpa.domain;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Address {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long addressID;

	@NonNull
	private String addressLine1;
	private String addressLine2;
	@NonNull
	private String city;
	@NonNull
	private String province;
	@NonNull
	private String country;
	@NonNull
	private String postalCode;
}
