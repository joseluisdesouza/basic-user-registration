package com.backend.basicregistration.dto;

import com.backend.basicregistration.entity.User;
import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotNull @Positive
    private Long id;
    @NotNull @NotEmpty
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
