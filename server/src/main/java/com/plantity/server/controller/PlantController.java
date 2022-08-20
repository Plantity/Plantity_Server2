package com.plantity.server.controller;

import com.plantity.server.domain.plant.PlantList;
import com.plantity.server.domain.plant.PlantListResponseDto;
import com.plantity.server.repository.PlantListRepository;
import com.plantity.server.repository.PlantLogRepository;
import lombok.RequiredArgsConstructor;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlantController {
    private final PlantLogRepository plantLogRepository;
    private final PlantListRepository plantListRepository;

    // 실내용 식물 목록 저장
    @ResponseBody
    @GetMapping("/api/plantList")
    public String apiCallPlantList(@RequestParam String apiKey) throws IOException, JDOMException {
        StringBuilder sb = new StringBuilder("http://api.nongsaro.go.kr/service/garden/gardenList?");

        sb.append("apiKey=" + apiKey);

        URL url = new URL(sb.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestProperty("Content-Type", "application/xml");;
        connection.setRequestMethod("GET");
        connection.connect();

        SAXBuilder builder = new SAXBuilder();
        Document document = builder.build(connection.getInputStream());

        Element root = document.getRootElement();
        Element body = root.getChild("body");
        Element items = body.getChild("items");
        List<Element> item = items.getChildren("item");


        PlantListResponseDto[] ar = new PlantListResponseDto[item.size()];

        int i = 0;
        for(Element element : item){
            String cntntsNo = element.getChildText("cntntsNo");
            String cntntsSj = element.getChildText("cntntsSj");
            String rtnFileSeCode = element.getChildText("rtnFileSeCode");
            String rtnFileSn = element.getChildText("rtnFileSn");
            String rtnOrginlFileNm = element.getChildText("rtnOrginlFileNm");
            String rtnStreFileNm = element.getChildText("rtnStreFileNm");
            String rtnFileCours = element.getChildText("rtnFileCours");
            String rtnImageDc = element.getChildText("rtnImageDc");
            String rtnThumbFileNm = element.getChildText("rtnThumbFileNm");
            String rtnImgSeCode = element.getChildText("rtnImgSeCode");

            /*
            PlantListResponseDto vo = new PlantListResponseDto(cntntsNo, cntntsSj, rtnFileSeCode,
                    rtnFileSn, rtnOrginlFileNm, rtnStreFileNm, rtnFileCours, rtnImageDc, rtnThumbFileNm, rtnImgSeCode);
            */
            PlantListResponseDto vo = new PlantListResponseDto(cntntsNo, cntntsSj);
            PlantList pl = new PlantList(vo);

            System.out.println("ar = " + vo.getCntntsNo());

            plantListRepository.save(pl);


            ar[i++] = vo;
        }
        return "success";
    }



}
