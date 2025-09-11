package ca.sheridancollege.acevedpa.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.acevedpa.domain.Roles;
import ca.sheridancollege.acevedpa.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
    // Find user by username (single user)
    Optional<User> findByUsername(String username);

    // Find user by email (single user), using Optional for better null handling
   List  <User> findByUserEmail(String userEmail);

    // Find all users with a specific role
    List<User> findByRole(Roles role);

    // Find all users who registered before specified date
    List<User> findByRegistrationDateBefore(Date date);

    // Find all users who registered after specified date
    List<User> findByRegistrationDateAfter(Date date);

    // Optional method to find user by email case-insensitively
    Optional<User> findByUserEmailIgnoreCase(String userEmail);
}
