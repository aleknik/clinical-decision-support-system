package com.aleknik.cdss.cdssservice.service;

import com.aleknik.cdss.cdssservice.controller.exception.NotFoundException;
import com.aleknik.cdss.cdssservice.model.User;
import com.aleknik.cdss.cdssservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public void delete(long id) {
        final User user = findById(id);
        userRepository.delete(user);
    }

    public User update(User updatedUser, long id) {
        final User user = findById(id);

        if (!user.getFirstName().equals(updatedUser.getFirstName())) {
            user.setFirstName(updatedUser.getFirstName());
        }
        if (!user.getLastName().equals(updatedUser.getLastName())) {
            user.setLastName(updatedUser.getLastName());
        }

        return userRepository.save(user);
    }
}
