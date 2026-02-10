package com.example.vaadinapp.service;

import com.example.vaadinapp.model.User;
import com.example.vaadinapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void initializeSampleData() {
        if (userRepository.count() == 0) {
            User user1 = new User("john.doe", "John Doe", LocalDate.of(1990, 5, 15),
                    new HashSet<>(Arrays.asList("USER", "EDITOR")));
            User user2 = new User("jane.smith", "Jane Smith", LocalDate.of(1985, 8, 22),
                    new HashSet<>(Arrays.asList("ADMIN", "USER")));
            User user3 = new User("bob.wilson", "Bob Wilson", LocalDate.of(1995, 3, 10),
                    new HashSet<>(Arrays.asList("USER")));
            User user4 = new User("alice.johnson", "Alice Johnson", LocalDate.of(1988, 12, 5),
                    new HashSet<>(Arrays.asList("MANAGER", "USER")));
            User user5 = new User("charlie.brown", "Charlie Brown", LocalDate.of(1992, 7, 30),
                    new HashSet<>(Arrays.asList("USER", "VIEWER")));

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
            userRepository.save(user4);
            userRepository.save(user5);
        }
    }
}
