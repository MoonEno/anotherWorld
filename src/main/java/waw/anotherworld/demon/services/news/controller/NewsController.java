package waw.anotherworld.demon.services.news.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import waw.anotherworld.demon.common.util.ExcelFileUpload;
import waw.anotherworld.demon.services.news.service.NewsService;

@RestController
@RequestMapping("/news")
public class NewsController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    NewsService newsService;

    @RequestMapping("/finup/gamezoa")
    public String getFinupGameZoaInfo() {
        logger.info("============== .... LISTEN DART API SENDING .... ===========");
        return newsService.getFinupGameZoaInfo();
    }

}
