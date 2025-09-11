package ca.sheridancollege.acevedpa.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Ticket {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long ticketID;
	
	private Date ticketPurchaseDate;
	//TODO - double check findings with team
	//private Integer ticketQuantity;
	//private BigDecimal ticketTotalPrice;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Event event; //event associated with ticket
}
