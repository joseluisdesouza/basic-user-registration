package com.backend.basicregistration.dto;

import com.backend.basicregistration.entity.User;
import lombok.*;

import java.time.LocalDate;

@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private LocalDate birthday;
    private String photo;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.birthday = user.getBirthday();
        this.photo = user.getPhoto();
    }
}
