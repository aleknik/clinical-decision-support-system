package com.aleknik.cdss.cdssservice.service;

import com.aleknik.cdss.cdssservice.controller.exception.ForbiddenException;
import com.aleknik.cdss.cdssservice.controller.exception.NotFoundException;
import com.aleknik.cdss.cdssservice.model.User;
import com.aleknik.cdss.cdssservice.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    /**
     * Gets a user currently logged in.
     * If no user is logged in, throws {@link ForbiddenException}.
     *
     * @return user currently logged in
     */
    public User findCurrentUser() {
        final UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new ForbiddenException("User is not authenticated!");
        }

        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return findByUsername(userDetails.getUsername());
    }
}
