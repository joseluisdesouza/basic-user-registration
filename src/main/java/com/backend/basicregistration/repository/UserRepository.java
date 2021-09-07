package com.backend.basicregistration.repository;

import com.backend.basicregistration.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("SELECT u.id, u.name, u.birthday, u.photo"+
//            "         FROM User u "+
//            " WHERE u.id = :id or :id is null "+
//            " AND (LOWER(u.name) LIKE LOWER(CONCAT('%',:name,'%')) )"
//    )
//    Page<User> findAll(Optional<Long> id, Optional<String> name, Pageable pageable);
}
