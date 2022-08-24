package com.plantity.server.controller;

import com.plantity.server.domain.plant.detail.PlantDetail;
import com.plantity.server.repository.PlantDetailRepository;
import com.plantity.server.repository.PlantLogRepository;
import com.plantity.server.service.PlantService;
import lombok.RequiredArgsConstructor;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

            for(Element element : item){
                String cntntsNo = element.getChildText("cntntsNo");
                String cntntsSj = element.getChildText("cntntsSj");

                StringBuilder sb2 = new StringBuilder("http://api.nongsaro.go.kr/service/garden/gardenDtl?");

                sb2.append("apiKey=" + apiKey);
                sb2.append("&cntntsNo=" + cntntsNo);

                URL url2 = new URL(sb2.toString());
                HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();

                connection2.setRequestProperty("Content-Type", "application/xml");;
                connection2.setRequestMethod("GET");
                connection2.connect();

                SAXBuilder builder2 = new SAXBuilder();
                Document document2 = builder2.build(connection2.getInputStream());

                Element root2 = document2.getRootElement();
                Element body2 = root2.getChild("body");
                List<Element> item2 = body2.getChildren("item");

                PlantDetail[] ar = new PlantDetail[item2.size()];
                int l = 0;
                for(Element element2: item2){
                    String plntbneNm = element2.getChildText("plntbneNm");
                    String plntzrNm = element2.getChildText("plntzrNm");
                    String adviseInfo = element2.getChildText("adviseInfo");
                    String orgplceInfo = element2.getChildText("orgplceInfo");
                    String lighttdemanddoCodeNm = element2.getChildText("lighttdemanddoCodeNm");
                    String ignSeasonCodeNm = element2.getChildText("ignSeasonCodeNm");
                    String flclrCodeNm = element2.getChildText("flclrCodeNm");
                    String watercycleSprngCodeNm = element2.getChildText("watercycleSprngCodeNm");
                    String managelevelCode = element2.getChildText("managelevelCode");

                    PlantDetail vo = new PlantDetail(cntntsNo, cntntsSj, plntbneNm, plntzrNm, adviseInfo,
                            orgplceInfo, lighttdemanddoCodeNm, ignSeasonCodeNm, flclrCodeNm, watercycleSprngCodeNm, managelevelCode);
                    plantDetailRepository.save(vo);

                    ar[l++] = vo;
                }

            }
        }

        return "success";
    }

    // 식물 목록 조회 api
    @GetMapping("/plant")
    public Page<PlantDetail> getPlantDetailList(@RequestParam int page, @RequestParam int size){
        PageRequest pageRequest = PageRequest.of(page,size);
        return plantDetailRepository.findAll(pageRequest);
    }

}
