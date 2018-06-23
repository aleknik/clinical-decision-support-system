package com.aleknik.cdss.cdssservice.controller;

import com.aleknik.cdss.cdssservice.model.Role;
import com.aleknik.cdss.cdssservice.model.User;
import com.aleknik.cdss.cdssservice.model.dto.UserCreateDto;
import com.aleknik.cdss.cdssservice.security.RoleConstants;
import com.aleknik.cdss.cdssservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("api")
public class UserController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/doctors")
    public ResponseEntity createDoctor(@RequestBody @Valid UserCreateDto userCreateDto) {
        User user = new User();
        user.setUsername(userCreateDto.getUsername());
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        user.getRoles().add(new Role(RoleConstants.DOCTOR));

        user = userService.create(user);
        user.setPassword(null);
        return ResponseEntity.created(URI.create(String.format("api/users/%d", user.getId()))).body(user);
    }

    @GetMapping("/doctors")
    public ResponseEntity getDoctors() {
        return ResponseEntity.ok(userService.findByRole(RoleConstants.DOCTOR));
    }
}
