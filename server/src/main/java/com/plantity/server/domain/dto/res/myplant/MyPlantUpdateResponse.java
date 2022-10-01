package com.plantity.server.domain.dto.res.myplant;

import com.plantity.server.constants.SuccessCode;
import com.plantity.server.domain.dto.BaseResponse;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class MyPlantUpdateResponse extends BaseResponse {

    private MyPlantUpdateResponse(Boolean success, String msg) {
        super(success,msg);
    }

    public static MyPlantUpdateResponse of(Boolean success, String msg) {
        return new MyPlantUpdateResponse(success, msg);
    }

    public static ResponseEntity<MyPlantUpdateResponse> newResponse(SuccessCode code) {
        return new ResponseEntity(MyPlantUpdateResponse.of(true, code.getMsg()), code.getStatus());
    }
}
