package com.unosq.taskflow.repositories;

import com.unosq.taskflow.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
// we ask JPA: "manage the user entity, and its primary key is Long "
public interface UserRepository extends JpaRepository<User, Long> {

    // if you create a method with the same name
    // JPA write the SQL from behind to search for the email
    Optional<User> findByEmail(String email);
}