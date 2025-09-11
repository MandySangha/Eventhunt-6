package ca.sheridancollege.acevedpa.services;

import ca.sheridancollege.acevedpa.domain.User;
import ca.sheridancollege.acevedpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<ca.sheridancollege.acevedpa.domain.User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<ca.sheridancollege.acevedpa.domain.User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public ca.sheridancollege.acevedpa.domain.User save(ca.sheridancollege.acevedpa.domain.User user) {
        return userRepository.save(user);
    }

    @Override
    public List<ca.sheridancollege.acevedpa.domain.User> findByUserEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ca.sheridancollege.acevedpa.domain.User user = userRepository.findByUserEmail(email)
            .stream()
            .findFirst()
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUserEmail())
                .password(user.getUserPassword())  
                .roles(user.getRole().name())      
                .build();
    }

}
