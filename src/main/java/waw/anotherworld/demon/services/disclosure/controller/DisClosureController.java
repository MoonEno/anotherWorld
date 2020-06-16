package waw.anotherworld.demon.services.disclosure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import waw.anotherworld.demon.services.disclosure.service.DisClosureService;

@RestController
@RequestMapping("/disclosure")
public class DisClosureController {

    @Autowired
    DisClosureService disClosureService;


    @RequestMapping("/testApi")
    public String getDailyDisClosure() {
        return disClosureService.getDailyDisClosureInfo();
    }
}
