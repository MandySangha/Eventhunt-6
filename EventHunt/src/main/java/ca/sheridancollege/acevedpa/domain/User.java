package ca.sheridancollege.acevedpa.domain;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

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
@Table(name = "_user") 
public class User {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long userID;
	
	@NonNull
	@Column(unique = true)  // Ensure email is unique
	private String username;

	@NonNull
	@Column(unique = true)  // Ensure email is unique
	private String userEmail;

	@NonNull
	private String userPassword;
	
	@Enumerated(EnumType.STRING)
	private Roles role;
	
	private Date registrationDate;

	// Assuming a user can have multiple tickets
	@OneToMany(mappedBy = "user")
	private List<Ticket> tickets;

}
