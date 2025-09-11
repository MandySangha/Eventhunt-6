package ca.sheridancollege.acevedpa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import ca.sheridancollege.acevedpa.domain.User;

@Service
public interface UserService extends UserDetailsService {

    Optional<User> findById(Long id);

    List<User> findAll();

    User save(User user);

    List<User> findByUserEmail(String userEmail);
}
