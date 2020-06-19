package waw.anotherworld.demon.services.disclosure.service;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import waw.anotherworld.demon.common.constants.ServiceConstant;
import waw.anotherworld.demon.common.http.HttpCallService;
import waw.anotherworld.demon.common.http.HttpResultVO;
import waw.anotherworld.demon.common.mail.MailService;
import waw.anotherworld.demon.common.util.UtilDate;
import waw.anotherworld.demon.services.disclosure.model.DisClosureResultVO;
import waw.anotherworld.demon.services.disclosure.model.DisClosureVO;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;
import java.util.prefs.PreferencesFactory;

@Service
public class DisClosureService {

    @Value("${dart.disClosureUrl}")
    String disClosureUrl;

    @Value("${dart.accessKey}")
    String key;

    @Autowired
    HttpCallService httpCallService;

    @Autowired
    MailService mailService;

    public String getDailyDisClosureInfo() {

        // https://opendart.fss.or.kr/api/list.json?crtfc_key=xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx&bgn_de=20200117&end_de=20200117&pblntf_ty=B&corp_cls=Y&page_no=1&page_count=10
        HttpResultVO resultVO = new HttpResultVO();
        DisClosureResultVO disClosureResultVO = new DisClosureResultVO();

        String today = UtilDate.getCurrent("yyyyMMdd");
//        String corpCls = ServiceConstant.CategoryType.KOSDAQ.getCode();
        String pblntfTy = ServiceConstant.ReportType.MAJOR_POINT_NOTICE.getCode();

        String sendUrl = disClosureUrl + "?" + "crtfc_key=" + key + "&" + "bgn_de=" + today + "&" + "end_de=" + today + "&" + "pblntf_ty=" + pblntfTy + "&page_no=1&page_count=100";
        resultVO = httpCallService.httpCall(sendUrl, null);

        try {
            String returnData = resultVO.getResultData();
            String data = "";
            Gson gson = new Gson();
            disClosureResultVO = gson.fromJson(returnData.toString(), DisClosureResultVO.class);

            // 결과처리 성공
            if ("000".equalsIgnoreCase(disClosureResultVO.getStatus())) {
                if (disClosureResultVO.getList().size() > 0 ) {
                    for (DisClosureVO disClosureVO : disClosureResultVO.getList()) {

                        if ("K".equalsIgnoreCase(disClosureVO.getCorpCls()) || "Y".equalsIgnoreCase(disClosureVO.getCorpCls())) {
                            String report = disClosureVO.getReportName();
                            if (report.contains("무상")) {
                                if ("".equalsIgnoreCase(data)) {
                                    data = "회사이름: " + disClosureVO.getCorpName() + "\n";
                                    data += "내용: " + disClosureVO.getReportName() + "\n";
                                } else {
                                    data += "\n";
                                    data += "회사이름: " + disClosureVO.getCorpName() + "\n";
                                    data += "내용: " + disClosureVO.getReportName() + "\n";
                                }
                            }
                        }

                    }
                    if (!"".equalsIgnoreCase(data)) {
                        mailService.sendEmail(data);
                    }
                }
                resultVO.setResultCode("200");
                resultVO.setResultMessge("성공");
            }
        } catch (Exception e ) {
            e.printStackTrace();
            resultVO.setResultCode("999");
            resultVO.setResultMessge("실패");
        }

        return resultVO.getResultCode() + " : " + resultVO.getResultMessge();

    }

    public void infoValueChecking(DisClosureResultVO disClosureResultVO) {

    }

}
