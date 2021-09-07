package com.backend.basicregistration.service;

import com.backend.basicregistration.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static com.backend.basicregistration.creator.UserCreator.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ExtendWith(SpringExtension.class)
public class UserTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void insertShouldReturnUserCreated() {
        when(userRepository.save(any())).thenReturn(createUserToSaved());
        assertEquals(createUserDTO(), userService.create(createUserDTO()));
    }

    @Test
    void findAllPagedShouldReturnPage() {

    }

    @Test
    void errorfindAllEmptyPagesShouldReturnPage() {

    }

    @Test
    void insertShouldReturnUserUpdate() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(createUser()));
        when(userRepository.save(any())).thenReturn(createUserToSaved());
        assertEquals(createUserDTO(), userService.update(createUserDTO().getId(), createUserDTO()));
    }

    @Test
    void errorShouldReturnUserUpdate() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        when(userRepository.save(any())).thenReturn(any());
        assertEquals(NOT_FOUND, assertThrows(ResponseStatusException.class, () -> userService.update(1L, createUserDTO())).getStatus());
    }

    @Test
    void removeShouldReturnUserDelete() {
        when(userRepository.findById(any())).thenReturn(Optional.of(createUser()));
        doNothing().when(userRepository).delete(any());
        userService.delete(1L);
    }

    @Test
    void errorRemoveShouldReturnUserDelete() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        doNothing().when(userRepository).delete(any());
        doThrow(EntityNotFoundException.class).when(userRepository).delete(any());
    }
}
