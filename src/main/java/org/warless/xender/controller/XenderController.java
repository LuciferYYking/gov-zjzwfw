package org.warless.xender.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * XenderController
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/11
 */
@RestController
public class XenderController {


    @GetMapping("/sender")
    public String hello() {
        return "Hello Xender!";
    }

}
