package ca.sheridancollege.acevedpa.services;

import ca.sheridancollege.acevedpa.domain.Categories;
import ca.sheridancollege.acevedpa.domain.Event;
import ca.sheridancollege.acevedpa.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> findUpcomingEvents() {
        return eventRepository.findByDateAfter(new Date());
    }

    public List<Event> findEventsByCategory(Categories category) {
        return eventRepository.findByCategory(category);
    }

    public List<Event> findEventsByOrganizerName(String orgName) {
        return eventRepository.findByOrganizer_OrgName(orgName);
    }

    public List<Event> findByPriceRange(Double minPrice, Double maxPrice) {
        return eventRepository.findByTicketPriceBetween(
            BigDecimal.valueOf(minPrice), BigDecimal.valueOf(maxPrice)
        );
    }

   
    @Override
    public List<Event> filterEvents(Categories category, Date fromDate, Date toDate,
                                    String fromTime, String toTime,
                                    Double minPrice, Double maxPrice) {
        List<Event> allEvents = eventRepository.findAll();

        return allEvents.stream().filter(event -> {
            boolean matchCategory = (category == null || event.getCategory() == category);
            boolean matchFromDate = (fromDate == null || !event.getDate().before(fromDate));
            boolean matchToDate = (toDate == null || !event.getDate().after(toDate));

            boolean matchFromTime = true;
            boolean matchToTime = true;

            if (fromTime != null && !fromTime.isEmpty()) {
                matchFromTime = event.getTime().toString().compareTo(fromTime) >= 0;
            }

            if (toTime != null && !toTime.isEmpty()) {
                matchToTime = event.getTime().toString().compareTo(toTime) <= 0;
            }

            boolean matchMinPrice = (minPrice == null || event.getTicketPrice().doubleValue() >= minPrice);
            boolean matchMaxPrice = (maxPrice == null || event.getTicketPrice().doubleValue() <= maxPrice);

            return matchCategory && matchFromDate && matchToDate &&
                   matchFromTime && matchToTime && matchMinPrice && matchMaxPrice;
        }).toList();
    }
}
