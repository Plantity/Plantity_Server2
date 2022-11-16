package com.plantity.server.dto.res.plantlog;

import com.plantity.server.constants.SuccessCode;
import com.plantity.server.domain.plantlog.DateMyPlantLogResponseDto;
import com.plantity.server.domain.plantlog.MyPlantLogResponseDto;
import com.plantity.server.dto.BaseResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@Getter
@NoArgsConstructor
public class DatePlantLogResponse extends BaseResponse {

    private DateMyPlantLogResponseDto data;

    private DatePlantLogResponse(Boolean success, String msg, DateMyPlantLogResponseDto data) {
        super(success, msg);
        this.data = data;
    }

    public static ResponseEntity<DatePlantLogResponse> newResponse(SuccessCode code, DateMyPlantLogResponseDto data) {
        DatePlantLogResponse response = new DatePlantLogResponse(true, code.getMsg(), data);
        return new ResponseEntity(response, code.getStatus());
    }
}
