package waw.anotherworld.demon.services.disclosure.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import waw.anotherworld.demon.services.disclosure.service.DisClosureService;

@Controller
@RequestMapping("/disclosure")
public class DisClosureController {

    @Autowired
    DisClosureService disClosureService;
    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/testApi")
//    @Scheduled(cron = "0/30 * 8-17 * * *")
    public String getDailyDisClosure() {
        logger.info("============== .... LISTEN DART API SENDING .... ===========");
        return disClosureService.getDailyDisClosureInfo();
    }

    @PostMapping("/testApi2")
    public String getFreeIncreaseDetail(String stockCode) {
        logger.info("============== .... GET FREE INCREASE DETAIL INFO  .... ===========");
        return disClosureService.getFreeIncreaseDetail("20200709000140");
    }
}
