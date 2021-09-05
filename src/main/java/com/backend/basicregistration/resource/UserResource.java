package com.backend.basicregistration.resource;

import com.backend.basicregistration.dto.UserDTO;
import com.backend.basicregistration.entity.User;
import com.backend.basicregistration.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;

    @PostMapping
    public UserDTO create(@RequestBody UserDTO user) {
        return userService.create(user);
    }

    @PostMapping("/{id}")
    public UserDTO update(UserDTO user) {
        return userService.update(user);
    }

    @GetMapping
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @DeleteMapping("/{id}")
    public void delete(Long userId) {
        userService.delete(userId);
    }
}
