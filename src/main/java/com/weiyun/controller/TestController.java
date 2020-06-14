package com.weiyun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Starix
 * @date 2020-06-14 23:35
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "sucess";
    }

}
