package net.lengmang.aicoffeeshareserver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Test2Controller {

    @Value("${appID}")
    private String appID;
    @Value("${appSecret}")
    private String appSecret;

    @GetMapping("/test2")
    public String test(HttpServletRequest request) {
        System.out.println(request.getRequestURL());
        return appID + "\n" + appSecret;
    }
}
