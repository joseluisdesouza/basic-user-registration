package com.backend.basicregistration.creator;

import com.backend.basicregistration.dto.UserDTO;
import com.backend.basicregistration.entity.User;

import java.time.LocalDate;

public class UserCreator {

    public static User createUser() {
        return User.builder()
                .id(1L)
                .name("José")
                .birthday(LocalDate.now())
                .photo("image.png")
                .build();
    }

    public static User createUserToSaved() {
        return User.builder()
                .id(createUser().getId())
                .name(createUser().getName())
                .birthday(LocalDate.now())
                .photo(createUser().getPhoto())
                .build();
    }

    public static UserDTO createUserDTO() {
        return UserDTO.builder()
                .id(1L)
                .name("José")
                .birthday(LocalDate.now())
                .build();
    }
}
