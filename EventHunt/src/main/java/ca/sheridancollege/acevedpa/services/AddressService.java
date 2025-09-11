package ca.sheridancollege.acevedpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ca.sheridancollege.acevedpa.domain.Address;

@Service
public interface AddressService {

	//must haves for all services
	public Optional<Address> findById(Long id);
	public List<Address> findAll();
	public Address save(Address address);
}
