package com.plantity.server.domain.users;

import com.plantity.server.domain.myPlant.MyPlantResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserDto {

    private UsersResponseDto responseDto;
    private List<MyPlantResponseDto> myPlantResponseDtos;

    public UserDto(UsersResponseDto responseDto, List<MyPlantResponseDto> myPlantResponseDtos){
        this.responseDto = responseDto;
        this.myPlantResponseDtos = myPlantResponseDtos;
    }

    public UserDto from (UsersResponseDto responseDto, List<MyPlantResponseDto> myPlantResponseDtos){
        return new UserDto(responseDto, myPlantResponseDtos);
    }
}


