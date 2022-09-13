package com.plantity.server.dto.res.users;

import com.plantity.server.constants.SuccessCode;
import com.plantity.server.domain.users.UsersResponseDto;
import com.plantity.server.dto.BaseResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@Getter
@NoArgsConstructor
public class UserResponse extends BaseResponse {

    private UsersResponseDto data;

    private UserResponse(Boolean success, String msg, UsersResponseDto data) {
        super(success, msg);
        this.data = data;
    }

    public static ResponseEntity<UserResponse> newResponse(SuccessCode code, UsersResponseDto data) {
        UserResponse response = new UserResponse(true, code.getMsg(), data);
        return new ResponseEntity(response, code.getStatus());
    }
}
