package com.plantity.server.repository;

import com.plantity.server.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UsersRepository extends JpaRepository<Users, Long> {
    //List<Object> findUserById(Long userId);
}
