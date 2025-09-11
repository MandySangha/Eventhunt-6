package ca.sheridancollege.acevedpa.domain;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
public class Feedback {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long feedbackID;
	
	@NonNull
	private Long rating; //between 1 and 5
	private String comment;
	private Date feedbackDate;
	
	@OneToOne
	private Event event; //event associated with feedback
	@OneToOne
	private User user;//user associated with feedback
}
