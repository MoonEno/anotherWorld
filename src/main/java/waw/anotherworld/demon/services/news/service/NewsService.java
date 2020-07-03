package waw.anotherworld.demon.services.news.service;


import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import waw.anotherworld.demon.common.constants.ServiceConstant;
import waw.anotherworld.demon.common.http.HttpCallService;
import waw.anotherworld.demon.common.http.HttpResultVO;
import waw.anotherworld.demon.common.mail.MailService;
import waw.anotherworld.demon.common.util.UtilDate;
import waw.anotherworld.demon.services.disclosure.mapper.DisClosureDao;
import waw.anotherworld.demon.services.disclosure.model.DisClosureResultVO;
import waw.anotherworld.demon.services.disclosure.model.DisClosureVO;

import java.io.IOException;

@Service
public class NewsService {


    @Autowired
    HttpCallService httpCallService;

    @Autowired
    MailService mailService;

    public String getFinupGameZoaInfo() {

        //  https://stock.finup.co.kr/Package/Package5ViewBoardList.aspx?BTIdx=17
        HttpResultVO resultVO = new HttpResultVO();

        try {
            Gson gson = new Gson();

            String marketStatusList = "https://stock.finup.co.kr/Package/Package5ViewBoardView.aspx?BTIdx=17&PCBIdx=3025&PageNo=1&SearchType=0&SearchText=";
            Document marketList = Jsoup.connect(marketStatusList).post();

            System.out.println(marketList);


            // 결과처리 성공
        } catch (Exception e ) {
            e.printStackTrace();
            resultVO.setResultCode("999");
            resultVO.setResultMessge("실패");
        }

        return resultVO.getResultCode() + " : " + resultVO.getResultMessge();

    }

}
