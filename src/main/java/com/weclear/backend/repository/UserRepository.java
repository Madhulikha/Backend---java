package com.weclear.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.weclear.backend.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhno(String phno);

    

}
