package com.backend.basicregistration.service;

import com.backend.basicregistration.dto.UserDTO;
import com.backend.basicregistration.entity.User;
import com.backend.basicregistration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDTO create(UserDTO dto) {
        return new UserDTO(userRepository.save(convertorDtoToEntity(dto)));
    }

    public Page<UserDTO> findAll(final Optional<Long> id,
                                 final Optional<String> name,
                                 final Pageable pageable) {
        return userRepository.findAll(id, name, pageable).map(UserDTO::new);
    }

    public UserDTO update(Long id, UserDTO userDTO) {
        var user = getUserById(id);
        user.setName(userDTO.getName());
        user.setBirthday(userDTO.getBirthday());
        return new UserDTO(userRepository.save(user));
    }

    public void delete(Long userId) {
        var user = getUserById(userId);
        var archive = Paths.get(File.separator + "photos" + File.separator + user.getPhoto());
        try {
            Files.delete(archive);
        } catch (IOException e) {
            log.error("Error delete photo", e);
        }
        userRepository.deleteById(user.getId());
    }

    public String createFileName(Long id, MultipartFile file) {
        var user = getUserById(id);
        var originalName = file.getOriginalFilename();
        var extension = FilenameUtils.getExtension(originalName);
        String fileName;
        fileName = validateFileNameFixedOrRandomUUID(user, extension);
        try {
            savePhoto(file, fileName);
            userRepository.save(user);
        } catch (IOException e) {
            log.error("Error saving photo", e);
        }
        return fileName;
    }

    public void savePhoto(MultipartFile file, String fileName) throws IOException {
        var archive = Paths.get(File.separator + "photos" + File.separator + fileName);
        byte[] bytes = file.getBytes();
        Files.write(archive, bytes);
    }

    private String validateFileNameFixedOrRandomUUID(User user, String extension) {
        String fileName;
        if (StringUtils.hasText(user.getPhoto())) {
            fileName = user.getPhoto();
        } else {
            fileName = UUID.randomUUID().toString() + "." + extension;
            user.withPhoto(fileName);
        }
        return fileName;
    }

    private User getUserById(final Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    private User convertorDtoToEntity(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .birthday(userDTO.getBirthday()).build();
    }
}
