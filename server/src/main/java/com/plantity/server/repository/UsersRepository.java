package com.plantity.server.repository;

import com.plantity.server.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UsersRepository extends JpaRepository<Users, Long> {
    boolean existsByEmail(String email);
    boolean existsByNickname(String niakname);
    boolean existsBySocialId(String socialId);

    Optional<Users> findByEmail(String email);

    Optional<Users> findById(String id);
    Optional<Users> findBySocialId(String social_id);
}
