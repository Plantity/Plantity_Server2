package com.plantity.server.repository;

import com.plantity.server.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByUserId(Long id);
    Boolean existsByUserId(Long userId);
}
