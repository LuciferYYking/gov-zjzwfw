package org.warless.xender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.warless.xender.service.XenderService;

/**
 * XenderController
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/11
 */
@RestController
@RequestMapping("/api")
public class XenderController {

    @Autowired
    private XenderService xenderService;

    @GetMapping("/upload")
    public String upload(String name) {
        return xenderService.upload(name);
    }

    @GetMapping("/download")
    public String download(String name) {
        return xenderService.download(name);
    }

}
