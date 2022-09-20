package com.plantity.server.dto.res.plantlog;

import com.plantity.server.constants.SuccessCode;
import com.plantity.server.domain.plantlog.MyPlantLogResponseDto;
import com.plantity.server.dto.BaseResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@Getter
@NoArgsConstructor
public class PlantLogResponse extends BaseResponse {

    private MyPlantLogResponseDto data;

    private PlantLogResponse(Boolean success, String msg, MyPlantLogResponseDto data) {
        super(success, msg);
        this.data = data;
    }

    public static ResponseEntity<PlantLogResponse> newResponse(SuccessCode code, MyPlantLogResponseDto data) {
        PlantLogResponse response = new PlantLogResponse(true, code.getMsg(), data);
        return new ResponseEntity(response, code.getStatus());
    }
}
