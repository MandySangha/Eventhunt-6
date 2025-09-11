package ca.sheridancollege.acevedpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.acevedpa.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
