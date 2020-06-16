package waw.anotherworld.demon.common.http;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpCallService {


    public HttpResultVO httpCall (String url, Object param) {

        HttpCallVO httpCallVO = new HttpCallVO();
        HttpResultVO httpResultVO = new HttpResultVO();

        String reqUri = url;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(reqUri, String.class);
        httpResultVO.setResultData(result);
        return httpResultVO;
    }
}
