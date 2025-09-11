package ca.sheridancollege.acevedpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ca.sheridancollege.acevedpa.domain.Organizer;

@Service
public interface OrganizerService {
	
	//must haves for all services
	public Optional<Organizer> findById(Long id);
	public List<Organizer> findAll();
	public Organizer save(Organizer organizer);
}
