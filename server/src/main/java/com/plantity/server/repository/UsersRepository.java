package com.plantity.server.repository;

import com.plantity.server.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {
    Optional<Users> findBySocialTypeAndUserId(String socialType, String userId);
    //token, type 둘다 충족하는 회원을 조회하는 메소드 추가

    Optional<Users> findByUserId(String userId);

//    boolean existsByNickname(String nickname);
//
//    Optional<Users> findByNickname(String nickname);
//
//    boolean existsBySocialId(String socialId);

}
