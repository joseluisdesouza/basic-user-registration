package com.backend.basicregistration.service;

import com.backend.basicregistration.dto.UserDTO;
import com.backend.basicregistration.entity.User;
import com.backend.basicregistration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDTO create(UserDTO dto) {
        User user = new User();
        convertorDtoToEntity(dto, user);
        return new UserDTO(userRepository.save(user));
    }

    public Page<UserDTO> findAll(final Pageable pageable) {
        var pageUser = userRepository.findAll(pageable);
        return (Page<UserDTO>) pageUser.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public UserDTO update(UserDTO userDTO) {
        User user = new User();
        convertorDtoToEntity(userDTO, user);
        userRepository.save(getUserById(userDTO.getId())
                .withName(userDTO.getName())
                .withBirthday(userDTO.getBirthday())
                .withPhoto(userDTO.getPhoto()));
        return new UserDTO(userRepository.save(user));
    }

    public void delete(Long userId) {
        var user = getUserById(userId);
        userRepository.deleteById(user.getId());
    }

    public void savePhoto(MultipartFile multipartFile) throws IOException {
        String folder = "/photos/";
        byte[] bytes = multipartFile.getBytes();
        var path = Paths.get(folder + multipartFile.getOriginalFilename());
        Files.write(path, bytes);
    }

    private User getUserById(final Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
    }

    private void convertorDtoToEntity(UserDTO userDTO, User user) {
        user.withId(userDTO.getId())
                .withName(userDTO.getName())
                .withBirthday(userDTO.getBirthday())
                .withPhoto(userDTO.getPhoto());
    }
}
