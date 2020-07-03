package waw.anotherworld.demon.services.corporation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import waw.anotherworld.demon.common.util.ExcelFileUpload;
import waw.anotherworld.demon.services.corporation.service.CorporationService;

@RestController
@RequestMapping("/corp")
public class CorporationController {

    @Autowired
    CorporationService corporationService;

    @RequestMapping("/insertCorpList")
    public void insertCorpList() {
        corporationService.insertCorpList();
    }


}
