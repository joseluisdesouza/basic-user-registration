package com.backend.basicregistration.resource;

import com.backend.basicregistration.dto.UserDTO;
import com.backend.basicregistration.entity.User;
import com.backend.basicregistration.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;

    @PostMapping
    public UserDTO create(@Validated @RequestBody UserDTO user) {
        return userService.create(user);
    }

    @PostMapping("/{id}")
    public UserDTO update(@Validated UserDTO user) {
        return userService.update(user);
    }

    @GetMapping
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(Long userId) {
        userService.delete(userId);
    }
}
