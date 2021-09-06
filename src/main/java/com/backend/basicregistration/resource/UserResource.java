package com.backend.basicregistration.resource;

import com.backend.basicregistration.dto.UserDTO;
import com.backend.basicregistration.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;

    @PostMapping
    public UserDTO create(@Validated @RequestBody UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @PostMapping("/{id}")
    public UserDTO update(@Validated UserDTO userDTO) {
        return userService.update(userDTO);
    }

    @GetMapping
    public Page<UserDTO> findAll(final Pageable pageable) {
        return userService.findAll(pageable.next());
    }

    @DeleteMapping("/{id}")
    public void delete(Long userId) {
        userService.delete(userId);
    }

    @PostMapping("/upload-photo")
    public String uploadPhoto(@RequestParam(required = false) MultipartFile multipartFile) {
        String returnValue = "";
        try {
            userService.savePhoto(multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Error saving photo", e);
            returnValue = "error";
        }
        return returnValue;
    }
}
