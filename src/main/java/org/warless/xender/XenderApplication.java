package org.warless.xender;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * XenderApplication
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/8
 */
@MapperScan("org.warless.xender.mapper")
@SpringBootApplication
public class XenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(XenderApplication.class, args);
    }

}
