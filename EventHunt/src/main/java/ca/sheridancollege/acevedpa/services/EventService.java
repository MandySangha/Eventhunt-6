package ca.sheridancollege.acevedpa.services;

import ca.sheridancollege.acevedpa.domain.Categories;
import ca.sheridancollege.acevedpa.domain.Event;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EventService {

    
    Optional<Event> findById(Long id);
    List<Event> findAll();
    Event save(Event event);

    
    List<Event> filterEvents(
        Categories category,
        Date fromDate,
        Date toDate,
        String fromTime,
        String toTime,
        Double minPrice,
        Double maxPrice
    );
}
