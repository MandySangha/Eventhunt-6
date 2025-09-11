package ca.sheridancollege.acevedpa.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.acevedpa.domain.Categories;
import ca.sheridancollege.acevedpa.domain.Event;
import ca.sheridancollege.acevedpa.domain.Organizer;

public interface EventRepository extends JpaRepository<Event, Long> {

	//find events by organizer
	List<Event> findByOrganizer(Organizer organizer);
	
	//find events by organizer name
	List<Event> findByOrganizer_OrgName(String orgName);
	
	//find events that are available (upcoming events)
	List<Event> findByDateAfter(Date date);

	//find events that have passed
	List<Event> findByDateBefore(Date date);
	
	//find event by category
	List<Event> findByCategory(Categories category);
	
	//find event by ticket price (price range)
	List<Event> findByTicketPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
	
	//find event by location (city, province, country)
	List<Event> findByEventAddress_City(String city);
	List<Event> findByEventAddress_Province(String province);
	List<Event> findByEventAddress_Country(String country);
	
	//find event by name (ignore case since it could be user input)
	List<Event> findByEventNameContainingIgnoreCase(String eventName);
}
