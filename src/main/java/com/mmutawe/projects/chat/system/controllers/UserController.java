package com.mmutawe.projects.chat.system.controllers;

import com.mmutawe.projects.chat.system.entities.User;
import com.mmutawe.projects.chat.system.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/register/{username}")
    public ResponseEntity<Void> register(@PathVariable String username){
        log.info("Registering user with username: {}", username);

        boolean isExists = userRepository.existsByUsername(username);
        if (isExists){
            log.error("user {} already exist, try a different username!", username);
            return ResponseEntity.badRequest().build();
        }

        User user = new User();
        user.setUsername(username);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public Set<String> fetchAll(){
        return userRepository.findAll().stream()
                .map(User::getUsername)
                .collect(Collectors.toSet());
    }


}
