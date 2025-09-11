package ca.sheridancollege.acevedpa.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.acevedpa.domain.Event;
import ca.sheridancollege.acevedpa.domain.Ticket;
import ca.sheridancollege.acevedpa.domain.User;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	
	//find all tickets of a specific event
	List<Ticket> findByEvent(Event event);
	
	//find all tickets of a specific event by name
	List<Ticket> findByEvent_EventName(String eventName);
	
	//find all tickets that belong to a specific user
	List<Ticket> findByUser(User user);
	
	//find tickets that have not expired (still valid)
	List<Ticket> findByEvent_DateAfter(Date currentDate);
	
	//find tickets that have expired
	List<Ticket> findByEvent_DateBefore(Date currentDate);
	
}
