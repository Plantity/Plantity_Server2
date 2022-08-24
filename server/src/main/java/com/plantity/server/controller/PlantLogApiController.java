package com.plantity.server.controller;

import com.plantity.server.domain.plantlog.PlantLogSaveRequestDto;
import com.plantity.server.dto.res.myplant.PlantLogUpdateResponse;
import com.plantity.server.service.PlantLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.plantity.server.constants.SuccessCode.UPDATE_SUNPLANTLOG_SUCCESS;

@RequiredArgsConstructor
@RestController
@RequestMapping("/plantLog")
public class PlantLogApiController {

    private final PlantLogService plantLogService;

    @PutMapping("/save/{id}")
    public ResponseEntity<PlantLogUpdateResponse> sunUpdate(@PathVariable Long id, @RequestBody PlantLogSaveRequestDto requestDto) {

        //예외코드 작성해야함,,

        plantLogService.sunUpdate(id, requestDto);

        return PlantLogUpdateResponse.newResponse(UPDATE_SUNPLANTLOG_SUCCESS);
    }
}
