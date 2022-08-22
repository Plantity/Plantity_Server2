package com.plantity.server.controller;

import com.plantity.server.domain.plant.detail.PlantDetail;
import com.plantity.server.domain.plant.list.PlantList;
import com.plantity.server.domain.plant.list.PlantListResponseDto;
import com.plantity.server.repository.PlantDetailRepository;
import com.plantity.server.repository.PlantListRepository;
import com.plantity.server.repository.PlantLogRepository;
import com.plantity.server.service.PlantService;
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
    private final PlantService plantService;
    private final PlantDetailRepository plantDetailRepository;

    // 실내용 식물 목록 저장
    @ResponseBody
    @GetMapping("/api/plantList")
    public String apiCallPlantList(@RequestParam String apiKey) throws IOException, JDOMException {
        for(int j = 1; j <= 22; j++){
            int pageNo = j;

            StringBuilder sb = new StringBuilder("http://api.nongsaro.go.kr/service/garden/gardenList?");

            sb.append("apiKey=" + apiKey);
            sb.append("&pageNo=" + pageNo);
            sb.append("&numOfRows=" + 10);

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

                PlantListResponseDto vo = new PlantListResponseDto(cntntsNo, cntntsSj);
                PlantList pl = new PlantList(vo);
                plantListRepository.save(pl);
                ar[i++] = vo;
            }
        }

        return "success";
    }


    // 실내 정원용 식물 상세
    @ResponseBody
    @GetMapping("/api/plantList/detail")
    public String apiCallPlantListDetail(@RequestParam String apiKey) throws IOException, JDOMException{

        for(int j = 1; j <= plantListRepository.count(); j++){
            StringBuilder sb = new StringBuilder("http://api.nongsaro.go.kr/service/garden/gardenDtl?");

            String cntntsNo = plantService.getPlantListById(j).getCntntsNo();

            sb.append("apiKey=" + apiKey);
            sb.append("&cntntsNo=" + cntntsNo);

            URL url = new URL(sb.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Content-Type", "application/xml");;
            connection.setRequestMethod("GET");
            connection.connect();

            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(connection.getInputStream());

            Element root = document.getRootElement();
            Element body = root.getChild("body");
            List<Element> item = body.getChildren("item");

            PlantDetail[] ar = new PlantDetail[item.size()];
            int i = 0;
            for(Element element: item){
                String cntntsNo2 = element.getChildText("cntntsNo");
                String plntbneNm = element.getChildText("plntbneNm");
                String plntzrNm = element.getChildText("plntzrNm");
                String adviseInfo = element.getChildText("adviseInfo");
                String orgplceInfo = element.getChildText("orgplceInfo");
                String lighttdemanddoCodeNm = element.getChildText("lighttdemanddoCodeNm");
                String ignSeasonCodeNm = element.getChildText("ignSeasonCodeNm");
                String flclrCodeNm = element.getChildText("flclrCodeNm");
                String watercycleSprngCodeNm = element.getChildText("watercycleSprngCodeNm");
                String managelevelCode = element.getChildText("managelevelCode");

                PlantDetail vo = new PlantDetail(cntntsNo2, plntbneNm, plntzrNm, adviseInfo,
                        orgplceInfo, lighttdemanddoCodeNm, ignSeasonCodeNm, flclrCodeNm, watercycleSprngCodeNm, managelevelCode);
                plantDetailRepository.save(vo);

                ar[i++] = vo;
            }
        }


        return "success";
    }

}
