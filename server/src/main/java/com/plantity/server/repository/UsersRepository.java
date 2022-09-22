package com.plantity.server.repository;

import com.plantity.server.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

//    public Users findByUserId(String userId);
    boolean existsByUserId(String userId); //email
    boolean existsBySocial(String social); //email
    boolean existsByNickName(String nickname);
//    boolean existsBySocialId(String socialId);

    Optional<Users> findBySocial(String social);

    Optional<Users> findById(String id);
    Optional<Users> findByUserId(String userId);

}
