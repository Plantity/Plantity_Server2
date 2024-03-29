package com.plantity.server.service;

import com.plantity.server.domain.users.Users;
import com.plantity.server.domain.users.UsersRequestDto;
import com.plantity.server.domain.users.UsersResponseDto;
import com.plantity.server.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UsersService {
    private final UsersRepository userRepository;

    /*
    @Transactional
    public Long updateUser(Long id, UsersRequestDto userRequestDto){
        Users user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다")
        );

        user.updateUser(userRequestDto);
        return user.getUserId();
    }

     */

    public UsersResponseDto userInfo(UsersRequestDto requestDto) {
        Users users = userRepository.findById(requestDto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 없습니다.")
        );
        users.checkRating();

        return UsersResponseDto.from(users);

    }
}
