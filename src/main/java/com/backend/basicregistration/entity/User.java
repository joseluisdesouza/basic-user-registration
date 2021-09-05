package com.backend.basicregistration.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@With
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private LocalDate birthday;
    private String photo;
}
