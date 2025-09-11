package ca.sheridancollege.acevedpa.controllers;

import ca.sheridancollege.acevedpa.domain.Categories;
import ca.sheridancollege.acevedpa.domain.Event;
import ca.sheridancollege.acevedpa.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:4200") 
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    
    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.findAll();
    }

    
    @GetMapping("/{id}")
    public Event getEventDetails(@PathVariable Long id) {
        return eventService.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
    }

    
    @GetMapping("/filter")
    public List<Event> filterEvents(
            @RequestParam(required = false) Categories category,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date toDate,
            @RequestParam(required = false) String fromTime,
            @RequestParam(required = false) String toTime,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice
    ) {
        return eventService.filterEvents(category, fromDate, toDate, fromTime, toTime, minPrice, maxPrice);
    }
}
