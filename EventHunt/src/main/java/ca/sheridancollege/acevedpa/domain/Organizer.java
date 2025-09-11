package ca.sheridancollege.acevedpa.domain;

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
public class Organizer {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long orgID;
	
	@NonNull
	private String orgName;
	@NonNull
	private String orgEmail;
	private String orgPhoneNumber;
	private String orgAddress;
}
