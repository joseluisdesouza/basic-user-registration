package com.backend.basicregistration.service;

import com.backend.basicregistration.dto.UserDTO;
import com.backend.basicregistration.entity.User;
import com.backend.basicregistration.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.With;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@With
@Slf4j
@Service
@AllArgsConstructor
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

    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    public UserDTO update(UserDTO user) {
        User user1 = new User();
        convertorDtoToEntity(user, user1);
        userRepository.save(findById(user.getId())
                .withName(user.getName())
                .withBirthday(user.getBirthday())
                .withPhoto(user.getPhoto()));
        return new UserDTO(userRepository.save(user1));
    }

    private void convertorDtoToEntity(UserDTO dto, User user) {
        user.withId(dto.getId())
                .withName(dto.getName())
                .withBirthday(dto.getBirthday())
                .withPhoto(dto.getPhoto());
    }
}
