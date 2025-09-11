package ca.sheridancollege.acevedpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.acevedpa.domain.Event;
import ca.sheridancollege.acevedpa.domain.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
	
	//find feedback by rating (1-5)
	List<Feedback> findByEventAndRating(Event event, Long rating);
	
	//find recent feedbacks (newest first) - of a specific event
	List<Feedback> findByEventOrderByFeedbackDateDesc(Event event);
	
	//find previous feedbacks (oldest first) - of a specific event
	List<Feedback> findByEventOrderByFeedbackDateAsc(Event event);
	
	//finds all feedbacks of an event (no specific order)
	List<Feedback> findByEvent(Event event);
	
	//find feedback for a specific event with a specific rating (newest first)
	List<Feedback> findByEventAndRatingOrderByFeedbackDateDesc(Event event, Long rating);
}
