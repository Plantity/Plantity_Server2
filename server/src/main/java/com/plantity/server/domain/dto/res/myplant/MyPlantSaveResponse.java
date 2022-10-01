package com.plantity.server.domain.dto.res.myplant;

import com.plantity.server.constants.SuccessCode;
import com.plantity.server.domain.dto.BaseResponse;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class MyPlantSaveResponse extends BaseResponse {

    private MyPlantSaveResponse(Boolean success, String msg) {
        super(success, msg);
    }

    public static MyPlantSaveResponse of(Boolean success, String msg) {
        return new MyPlantSaveResponse(success, msg);
    }

    public static ResponseEntity<MyPlantSaveResponse> newResponse(SuccessCode code) {
        return  new ResponseEntity(MyPlantSaveResponse.of(true, code.getMsg()), code.getStatus());
    }
}
