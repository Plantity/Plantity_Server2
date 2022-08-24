package com.plantity.server.dto.res.myplant;

import com.plantity.server.constants.SuccessCode;
import com.plantity.server.dto.BaseResponse;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class PlantLogUpdateResponse extends BaseResponse {

    private PlantLogUpdateResponse(Boolean success, String msg) {
        super(success, msg);
    }

    public static PlantLogUpdateResponse of(Boolean success, String msg) {
        return new PlantLogUpdateResponse(success, msg);
    }

    public static ResponseEntity<PlantLogUpdateResponse> newResponse(SuccessCode code) {
        return new ResponseEntity(PlantLogUpdateResponse.of(true, code.getMsg()), code.getStatus());
    }
}
