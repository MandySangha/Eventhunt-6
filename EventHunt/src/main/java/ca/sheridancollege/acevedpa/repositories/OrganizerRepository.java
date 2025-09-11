package ca.sheridancollege.acevedpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.acevedpa.domain.Organizer;

public interface OrganizerRepository extends JpaRepository<Organizer, Long> {

	//find organizer by name
	List<Organizer> findByOrgName(String orgName);
	
	//find organizer by email
	List<Organizer> findByOrgEmail(String orgEmail);
}
