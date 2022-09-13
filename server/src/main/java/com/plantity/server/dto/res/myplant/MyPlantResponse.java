package com.plantity.server.dto.res.myplant;


import com.plantity.server.constants.SuccessCode;
import com.plantity.server.domain.myPlant.MyPlantResponseDto;
import com.plantity.server.dto.BaseResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@Getter
@NoArgsConstructor
public class MyPlantResponse extends BaseResponse {

    private MyPlantResponseDto data;

    private MyPlantResponse(Boolean success, String msg, MyPlantResponseDto data) {
        super(success, msg);
        this.data = data;
    }

    
}
