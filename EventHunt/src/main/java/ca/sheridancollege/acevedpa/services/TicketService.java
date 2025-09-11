package ca.sheridancollege.acevedpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ca.sheridancollege.acevedpa.domain.Ticket;

@Service
public interface TicketService {
	
	//must haves for all services
	public Optional<Ticket> findById(Long id);
	public List<Ticket> findAll();
	public Ticket save(Ticket ticket);

}
