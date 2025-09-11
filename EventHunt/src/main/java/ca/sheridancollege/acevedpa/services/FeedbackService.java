package ca.sheridancollege.acevedpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ca.sheridancollege.acevedpa.domain.Feedback;

@Service
public interface FeedbackService {

	//must haves for all services
	public Optional<Feedback> findById(Long id);
	public List<Feedback> findAll();
	public Feedback save(Feedback feedback);
}
