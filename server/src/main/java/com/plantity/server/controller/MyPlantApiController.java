package com.plantity.server.controller;

import static com.plantity.server.constants.SuccessCode.*;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.plantity.server.config.BaseResponse2;
import com.plantity.server.domain.myPlant.MyPlant;
import com.plantity.server.domain.myPlant.MyPlantSaveRequestDto;
import com.plantity.server.domain.plantlog.PlantLog;
import com.plantity.server.domain.users.Users;
import com.plantity.server.dto.res.myplant.MyPlantSaveResponse;
import com.plantity.server.dto.res.myplant.MyPlantUpdateResponse;
import com.plantity.server.repository.MyPlantRepository;
import com.plantity.server.repository.PlantLogRepository;
import com.plantity.server.repository.UsersRepository;
import com.plantity.server.service.MyPlantService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

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

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;

    @PostMapping("/save")
    public ResponseEntity<MyPlantSaveResponse> save(@RequestPart(value="image", required=false)  MultipartFile multipartFile,@RequestPart(value = "myPlantSaveRequestDto") MyPlantSaveRequestDto myPlantSaveRequestDto) throws IOException {
        try{
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

            /*
            if (myPlantSaveRequestDto.getPlantName() == null | myPlantSaveRequestDto.getPlantType() == null) {
                throw new CustomException(ExceptionCode.NO_REQUIRED_PARAMETER);
            }
             */
            Users users1  = new Users(usersRepository.findByUserId(1L));

            MyPlant myPlant = new MyPlant(amazonS3.getUrl(bucket, filePath).toString(), myPlantSaveRequestDto.getPlantAdaptTime(), myPlantSaveRequestDto.getPlantName(), myPlantSaveRequestDto.getPlantType(),users1);

            //myPlantService.save(myPlantSaveRequestDto);
            myPlantRepository.save(myPlant);
        }catch (Exception exception){
            logger.error("Error!", exception);
        }

        return MyPlantSaveResponse.newResponse(CREATE_MYPLANT_SUCCESS);
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

    @GetMapping("/plant")
    public List<PlantLog> check(){
        return plantLogRepository.findAll();
    }
}
