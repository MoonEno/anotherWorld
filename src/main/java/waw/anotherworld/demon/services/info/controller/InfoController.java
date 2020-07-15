package waw.anotherworld.demon.services.info.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import waw.anotherworld.demon.common.util.ExcelFileUpload;
import waw.anotherworld.demon.services.info.service.InfoService;
import waw.anotherworld.demon.services.news.service.NewsService;

@RestController
@RequestMapping("/info")
public class InfoController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    InfoService infoService;

}
