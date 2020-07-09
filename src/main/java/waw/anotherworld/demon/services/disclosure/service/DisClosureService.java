package waw.anotherworld.demon.services.disclosure.service;


import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
import java.util.List;

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

    @Autowired @SuppressWarnings("ALL")
    DisClosureDao disClosureDao;

    public String getDailyDisClosureInfo() {

        // https://opendart.fss.or.kr/api/list.json?crtfc_key=xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx&bgn_de=20200117&end_de=20200117&pblntf_ty=B&corp_cls=Y&page_no=1&page_count=10
        // String returnData = "{\"status\":\"000\",\"message\":\"정상\",\"page_no\":1,\"page_count\":10,\"total_count\":10,\"total_page\":1,\"list\":[{\"corp_code\":\"00493431\",\"corp_name\":\"자안\",\"stock_code\":\"221610\",\"corp_cls\":\"K\",\"report_nm\":\"주요사항보고서(전환사채권발행결정)\",\"rcept_no\":\"20200616000225\",\"flr_nm\":\"자안\",\"rcept_dt\":\"20200616\",\"rm\":\"\"},{\"corp_code\":\"00556907\",\"corp_name\":\"에이스테크\",\"stock_code\":\"088800\",\"corp_cls\":\"K\",\"report_nm\":\"주요사항보고서(유상증자결정)\",\"rcept_no\":\"20200616000219\",\"flr_nm\":\"에이스테크\",\"rcept_dt\":\"20200616\",\"rm\":\"\"},{\"corp_code\":\"00632793\",\"corp_name\":\"전진바이오팜\",\"stock_code\":\"110020\",\"corp_cls\":\"K\",\"report_nm\":\"주요사항보고서(전환사채권발행결정)\",\"rcept_no\":\"20200616000212\",\"flr_nm\":\"전진바이오팜\",\"rcept_dt\":\"20200616\",\"rm\":\"\"},{\"corp_code\":\"00136165\",\"corp_name\":\"스페코\",\"stock_code\":\"013810\",\"corp_cls\":\"K\",\"report_nm\":\"주요사항보고서(자기주식처분결정)\",\"rcept_no\":\"20200616000192\",\"flr_nm\":\"스페코\",\"rcept_dt\":\"20200616\",\"rm\":\"\"},{\"corp_code\":\"00657002\",\"corp_name\":\"에이디테크놀로지\",\"stock_code\":\"200710\",\"corp_cls\":\"K\",\"report_nm\":\"주요사항보고서(자기주식취득신탁계약해지결정)\",\"rcept_no\":\"20200616000180\",\"flr_nm\":\"에이디테크놀로지\",\"rcept_dt\":\"20200616\",\"rm\":\"\"},{\"corp_code\":\"00130383\",\"corp_name\":\"서부T&D\",\"stock_code\":\"006730\",\"corp_cls\":\"K\",\"report_nm\":\"주요사항보고서(유형자산양도결정)\",\"rcept_no\":\"20200616000174\",\"flr_nm\":\"서부T&D\",\"rcept_dt\":\"20200616\",\"rm\":\"\"},{\"corp_code\":\"00629212\",\"corp_name\":\"딜리\",\"stock_code\":\"131180\",\"corp_cls\":\"K\",\"report_nm\":\"주요사항보고서(자기주식취득신탁계약해지결정)\",\"rcept_no\":\"20200616000106\",\"flr_nm\":\"딜리\",\"rcept_dt\":\"20200616\",\"rm\":\"\"},{\"corp_code\":\"00939942\",\"corp_name\":\"포시에스\",\"stock_code\":\"189690\",\"corp_cls\":\"K\",\"report_nm\":\"[연장결정]주요사항보고서(자기주식취득신탁계약체결결정)\",\"rcept_no\":\"20200616000052\",\"flr_nm\":\"포시에스\",\"rcept_dt\":\"20200616\",\"rm\":\"\"},{\"corp_code\":\"00939942\",\"corp_name\":\"포시에스\",\"stock_code\":\"189690\",\"corp_cls\":\"K\",\"report_nm\":\"주요사항보고서(무상증자결정)\",\"rcept_no\":\"20200616000031\",\"flr_nm\":\"포시에스\",\"rcept_dt\":\"20200616\",\"rm\":\"\"},{\"corp_code\":\"00116426\",\"corp_name\":\"코센\",\"stock_code\":\"009730\",\"corp_cls\":\"K\",\"report_nm\":\"[기재정정]주요사항보고서(타법인주식및출자증권양수결정)\",\"rcept_no\":\"20200615000335\",\"flr_nm\":\"코센\",\"rcept_dt\":\"20200616\",\"rm\":\"\"}]}";
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
            String kScore = "";
            String kPer = "";
            String dScore = "";
            String dPer = "";
            Gson gson = new Gson();
            disClosureResultVO = gson.fromJson(returnData.toString(), DisClosureResultVO.class);

            // 결과처리 성공
            if ("000".equalsIgnoreCase(disClosureResultVO.getStatus())) {

                if (disClosureResultVO.getList().size() > 0 ) {
                    for (DisClosureVO disClosureVO : disClosureResultVO.getList()) {
                        if ("K".equalsIgnoreCase(disClosureVO.getCorpCls()) || "Y".equalsIgnoreCase(disClosureVO.getCorpCls())) {
                            String report = disClosureVO.getReportName();

                            if (report.contains("무상")) {

                                String check = disClosureDao.selectAlreadySendInfoCheck();

                                if (!check.contains(disClosureVO.getReportNo())){

                                    disClosureDao.insertDisclosureInfo(disClosureVO);

                                    try {
                                        String kospiUrl = "https://finance.naver.com/sise/sise_index.nhn?code=KOSPI";
                                        Document kospi = Jsoup.connect(kospiUrl).post();
                                        kScore = kospi.getElementById("now_value").text();
                                        kPer = kospi.getElementById("change_value_and_rate").text();
                                        String dowUrl = "https://finance.naver.com/world/sise.nhn?symbol=DJI@DJI";
                                        Document dow = Jsoup.connect(dowUrl).post();
                                        dScore = dow.getElementsByClass("no_today").text();
                                        dPer = dow.getElementsByClass("no_exday").text();

                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                    if ("".equalsIgnoreCase(data)) {
                                        data = "[금일 시장상황]" + "\n";
                                        data += "코스피지수(현재): " + kScore + " ( " + kPer + ") \n";
                                        data += "다우지수(마감): " + dScore + " [ " + dPer + " ] \n";
                                        data += "==============================================\n";
                                        data += "회사이름: " + disClosureVO.getCorpName() + "\n";
                                        data += "내용: " + disClosureVO.getReportName() + "\n";
                                    } else {
                                        data += "\n";
                                        data += "회사이름: " + disClosureVO.getCorpName() + "\n";
                                        data += "내용: " + disClosureVO.getReportName() + "\n";
                                    }
                                }
                            }
                        }
                    }

                    // 데이터가 있을 경우, KOSPI, NASTAQ 전날 혹은 현재 시세를 확인해줌
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

    // 무상증자 세부내용 확인
    public String getFreeIncreaseDetail(String stockCode) {

        if (stockCode == "" || stockCode == null) {
            return "StockCode is Null";
        }

        String url = "";
        //      http://dart.fss.or.kr/dsaf001/main.do?rcpNo=20200709000140
        try {
            url = "http://dart.fss.or.kr/dsaf001/main.do?rcpNo=20200709000140";
            Document doc = Jsoup.connect(url).post();

            if (doc != null) {

                String eleStr = doc.getElementsByClass("view_search").html();
                int idx = eleStr.indexOf("openPdfDownload");
                String dcmStrTmp = eleStr.substring(idx, idx+45);
                String dcmIdx = dcmStrTmp.replaceAll("openPdfDownload", "").replaceAll("20200709000140", "").replaceAll("\\(","").replaceAll("\\)", "").replaceAll("\\'", "").replaceAll("\\,", "").replaceAll("\\;", "").replaceAll("\\\" " ,"").replaceAll(" ", "");
                String incUrl = "";
                if (dcmIdx != null && !"".equalsIgnoreCase(dcmIdx)) {
                     incUrl = "/report/viewer.do?rcpNo=" + stockCode + "&dcmNo=" + dcmIdx + "&eleId=0&offset=0&length=0&dtd=dart3.xsd";
                     Document doc2 = Jsoup.connect(incUrl).post();
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

         return url;

    }

}
