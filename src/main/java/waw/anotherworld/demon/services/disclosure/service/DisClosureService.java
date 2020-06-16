package waw.anotherworld.demon.services.disclosure.service;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import waw.anotherworld.demon.common.constants.ServiceConstant;
import waw.anotherworld.demon.common.http.HttpCallService;
import waw.anotherworld.demon.common.http.HttpResultVO;
import waw.anotherworld.demon.common.util.UtilDate;
import waw.anotherworld.demon.services.disclosure.model.DisClosureResultVO;
import waw.anotherworld.demon.services.disclosure.model.DisClosureVO;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
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

    public String getDailyDisClosureInfo() {

        // https://opendart.fss.or.kr/api/list.json?crtfc_key=xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx&bgn_de=20200117&end_de=20200117&pblntf_ty=B&corp_cls=Y&page_no=1&page_count=10
        HttpResultVO resultVO = new HttpResultVO();
        DisClosureResultVO disClosureResultVO = new DisClosureResultVO();
        DisClosureVO disClosureVO = new DisClosureVO();

        String today = UtilDate.getCurrent("yyyyMMdd");
        String corpCls = ServiceConstant.CategoryType.KOSDAQ.getCode();
        String pblntfTy = ServiceConstant.ReportType.MAJOR_POINT_NOTICE.getCode();

        String sendUrl = disClosureUrl + "?" + "crtfc_key=" + key + "&" + "bgn_de=" + today + "&" + "end_de=" + today + "&" + "pblntf_ty=" + pblntfTy + "&" + "corp_cls=" + corpCls + "&" + "page_no=1&page_count=10";
        resultVO = httpCallService.httpCall(sendUrl, null);

        try {
            String returnData = resultVO.getResultData();
            Gson gson = new Gson();
            disClosureResultVO = gson.fromJson(returnData.toString(), DisClosureResultVO.class);

            // 결과처리 성공
            if ("000".equalsIgnoreCase(disClosureResultVO.getStatus())) {
                infoValueChecking(disClosureResultVO);
            }

        } catch (Exception e ) {


        }

        return resultVO.getResultData();

    }

    public void infoValueChecking(DisClosureResultVO disClosureResultVO) {

    }

}
