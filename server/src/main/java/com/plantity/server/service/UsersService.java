package com.plantity.server.service;

import com.plantity.server.domain.Users;
import com.plantity.server.domain.UsersRequestDto;
import com.plantity.server.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UsersService {
    private final UsersRepository userRepository;

    @Transactional
    public Long updateUser(Long id, UsersRequestDto userRequestDto){
        Users user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다")
        );

        user.updateUser(userRequestDto);
        return user.getUserId();
    }
}
