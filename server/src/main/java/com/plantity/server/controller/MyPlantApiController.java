package com.plantity.server.controller;

import static com.plantity.server.config.BaseResponseStatus.*;
import static com.plantity.server.constants.SuccessCode.*;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.plantity.server.config.BaseException;
import com.plantity.server.config.BaseResponse2;
import com.plantity.server.constants.ExceptionCode;
import com.plantity.server.domain.myPlant.MyPlant;
import com.plantity.server.domain.myPlant.MyPlantRequestDto;
import com.plantity.server.domain.myPlant.MyPlantResponseDto;
import com.plantity.server.domain.myPlant.MyPlantSaveRequestDto;
import com.plantity.server.domain.plant.detail.PlantDetail;
import com.plantity.server.domain.plantlog.DateMyPlantLogResponseDto;
import com.plantity.server.domain.plantlog.MyPlantLogRequestDto;
import com.plantity.server.domain.plantlog.MyPlantLogResponseDto;
import com.plantity.server.domain.users.Users;
import com.plantity.server.dto.req.DateMyPlantLogRequestDto;
import com.plantity.server.dto.res.myplant.MyPlantResponse;
import com.plantity.server.dto.res.myplant.MyPlantSaveResponse;
import com.plantity.server.dto.res.myplant.MyPlantUpdateResponse;
import com.plantity.server.dto.res.plantlog.DatePlantLogResponse;
import com.plantity.server.dto.res.plantlog.PlantLogResponse;
import com.plantity.server.exception.CustomException;
import com.plantity.server.repository.MyPlantRepository;
import com.plantity.server.repository.PlantDetailRepository;
import com.plantity.server.repository.PlantLogRepository;
import com.plantity.server.repository.UsersRepository;
import com.plantity.server.service.MyPlantService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;


@RequiredArgsConstructor
@RestController
@RequestMapping("/myplant")
public class MyPlantApiController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AmazonS3 amazonS3;

    private final MyPlantService myPlantService;
    private final PlantLogRepository plantLogRepository;
    private final MyPlantRepository myPlantRepository;
    private final UsersRepository usersRepository;
    private final PlantDetailRepository plantDetailRepository;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;

    @PostMapping("/save/{userId}")
    public BaseResponse2<MyPlantSaveResponse> save(
            @RequestParam String cntntsNo,
            @RequestPart(value="image", required=false)  MultipartFile multipartFile,
            @RequestPart(value = "plantName") String plantName,
            @RequestPart(value = "plantNickName") String plantNickName,
            @RequestPart(value = "plantAdaptTime") String plantAdaptTime,
            @PathVariable Long userId) throws IOException {

        // cntntsNo 유효성 검사
        if(!plantDetailRepository.existsByCntntsNo(cntntsNo)){
            return new BaseResponse2<>(POST_CNTNTSNO_INVALID);
        }

        try{
            // cntntsNo로 해당 식물의 관수 설명도 함께 myPlant에 저장
            PlantDetail plantDetail = new PlantDetail(plantDetailRepository.findByCntntsNo(cntntsNo)); // 식물 찾기
            String watercycleSprngCodeNm= plantDetail.getWatercycleSprngCodeNm(); // 관수설명 찾기


            String oriFileName = multipartFile.getOriginalFilename();
            String fileName = oriFileName;
            String filePath = "postImg/" + fileName;
            Long fileSize = multipartFile.getSize();
            String contentType = multipartFile.getContentType();

            // S3 이미지 삽입
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(fileSize);
            objectMetadata.setContentType(contentType);
            amazonS3.putObject(bucket, filePath, multipartFile.getInputStream(), objectMetadata);

            Users users1  = new Users(usersRepository.findByUserId(userId));

            MyPlant myPlant = new MyPlant(plantName,plantNickName, plantAdaptTime,amazonS3.getUrl(bucket, filePath).toString(), watercycleSprngCodeNm, users1);

            myPlantRepository.save(myPlant);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BaseResponse2<>(POST_SUCCESS);
    }

    @PutMapping("/sun/{userId}/{myPlantId}")
    public ResponseEntity<MyPlantUpdateResponse> putSun(@PathVariable Long userId, @PathVariable Long myPlantId) {
        myPlantService.updateSun(userId, myPlantId);
        return MyPlantUpdateResponse.newResponse(UPDATE_SUNPLANTLOG_SUCCESS);
    }

    @PutMapping("/repot/{userId}/{myPlantId}")
    public BaseResponse2<String> putRepot(@PathVariable Long userId, @PathVariable Long myPlantId){
        myPlantService.updateRepot(userId, myPlantId);
        return new BaseResponse2("success");
    }

    @PutMapping("/water/{userId}/{myPlantId}")
    public ResponseEntity<MyPlantUpdateResponse> putWater(@PathVariable Long userId, @PathVariable Long myPlantId) {
        myPlantService.updateWater(userId, myPlantId);
        return MyPlantUpdateResponse.newResponse(UPDATE_WATERPLANTLOG_SUCCESS);
    }

    @PutMapping("look/{userId}/{myPlantId}")
    public ResponseEntity<MyPlantUpdateResponse> putLook(@PathVariable Long userId, @PathVariable Long myPlantId) {
        myPlantService.updateLook(userId, myPlantId);
        return MyPlantUpdateResponse.newResponse(UPDATE_LOOKPLANTLOG_SUCCESS);
    }

    // myPlant 상세 정보 조회
    @GetMapping("/plant/{userId}/{myPlantId}")
    public ResponseEntity<MyPlantResponse> myPlantInfo(@PathVariable Long userId, @PathVariable Long myPlantId) {

        MyPlantRequestDto myPlantRequestDto = MyPlantRequestDto.of(userId, myPlantId);

        MyPlantResponseDto myPlantResponseDto = myPlantService.myPlantInfo(myPlantRequestDto);

        return MyPlantResponse.newResponse(MYPLANT_INFO_SUCCESS, myPlantResponseDto);
    }

    // 식물 로그 상세 조회
    /*
    @GetMapping("/plantLog/{userId}/{myPlantId}/{plantLogId}")
    public ResponseEntity<PlantLogResponse> plantLogInfo(@PathVariable Long userId, @PathVariable Long myPlantId, @PathVariable Long plantLogId) {

        MyPlantLogRequestDto myPlantLogRequestDto = MyPlantLogRequestDto.of(userId, myPlantId, plantLogId);

        MyPlantLogResponseDto myPlantLogResponseDto = myPlantService.plantLogInfo(myPlantLogRequestDto);

        return PlantLogResponse.newResponse(PLANTLOG_INFO_SUCCESS, myPlantLogResponseDto);
    }
     */

    // 각 날짜별 식물 로그 조회
    @PostMapping("/plantLog")
    public ResponseEntity<DatePlantLogResponse> datePlantLogInfo(@RequestBody DateMyPlantLogRequestDto requestDto) {

        if(requestDto.getLogDate() == null) {
            throw new CustomException(ExceptionCode.NO_REQUIRED_PARAMETER);
        }
        DateMyPlantLogResponseDto dateMyPlantLogResponseDto = myPlantService.datePlantLog(requestDto);
        return DatePlantLogResponse.newResponse(PLANTLOG_INFO_SUCCESS, dateMyPlantLogResponseDto);
    }
}
