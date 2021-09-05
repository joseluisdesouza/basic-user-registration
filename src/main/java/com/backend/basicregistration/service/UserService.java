package com.backend.basicregistration.service;

import com.backend.basicregistration.dto.UserDTO;
import com.backend.basicregistration.entity.User;
import com.backend.basicregistration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
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

    public List<UserDTO> findAll() {
        var list = userRepository.findAll();
        return list.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public User findById(final Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
    }

    public UserDTO update(UserDTO dto) {
        User user = new User();
        convertorDtoToEntity(dto, user);
        userRepository.save(findById(dto.getId())
                .withName(dto.getName())
                .withBirthday(dto.getBirthday())
                .withPhoto(dto.getPhoto()));
        return new UserDTO(userRepository.save(user));
    }

    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    private void convertorDtoToEntity(UserDTO dto, User user) {
        user.withId(dto.getId())
                .withName(dto.getName())
                .withBirthday(dto.getBirthday())
                .withPhoto(dto.getPhoto());
    }
}
