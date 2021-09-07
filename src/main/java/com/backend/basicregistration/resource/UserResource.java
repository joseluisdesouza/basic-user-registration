package com.backend.basicregistration.resource;

import com.backend.basicregistration.dto.UserDTO;
import com.backend.basicregistration.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


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

    @PutMapping("/{id}")
    public UserDTO update(@PathVariable Long id, @Validated @RequestBody UserDTO userDTO) {
        return userService.update(id, userDTO);
    }

    @GetMapping
    public Page<UserDTO> findAll(@RequestParam(required = false) Optional<Long> id,
                                 @RequestParam(required = false) Optional<String> name,
                                 @RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
                                 @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
                                 @RequestParam(value = "direction", defaultValue = "DESC") String direction) {
        return userService.findAll(id, name, PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PatchMapping("/upload-photo/{id}")
    public String uploadPhoto(@PathVariable Long id, @RequestParam MultipartFile file) {
        return userService.createFileName(id, file);
    }
}
