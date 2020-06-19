package waw.anotherworld.demon.services.disclosure.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import waw.anotherworld.demon.services.disclosure.service.DisClosureService;

@RestController
@RequestMapping("/disclosure")
public class DisClosureController {

    @Autowired
    DisClosureService disClosureService;
    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/testApi")
    @Scheduled(cron = "0/50 * * * * *")
    public String getDailyDisClosure() {
        logger.info("============== .... LISTEN DART API SENDING .... ===========");
        return disClosureService.getDailyDisClosureInfo();
    }
}
