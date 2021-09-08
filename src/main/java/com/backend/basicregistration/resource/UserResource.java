package com.backend.basicregistration.resource;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.basicregistration.dto.UserDTO;
import com.backend.basicregistration.service.UserService;

import lombok.RequiredArgsConstructor;


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
                                 @RequestParam(defaultValue = "0") Integer page,
                                 @RequestParam(defaultValue = "10") Integer linesPerPage,
                                 @RequestParam(defaultValue = "id") String orderBy,
                                 @RequestParam(defaultValue = "ASC") Sort.Direction direction) {
        return userService.findAll(id, name, PageRequest.of(page, linesPerPage, Sort.by(direction, orderBy)));
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
