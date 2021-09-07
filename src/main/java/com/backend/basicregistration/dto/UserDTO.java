package com.backend.basicregistration.dto;

import com.backend.basicregistration.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Positive
    private Long id;
    @NotNull @NotEmpty
    private String name;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthday;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.birthday = user.getBirthday();
    }
}
