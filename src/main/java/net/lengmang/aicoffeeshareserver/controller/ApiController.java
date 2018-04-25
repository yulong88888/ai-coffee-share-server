package net.lengmang.aicoffeeshareserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApiController {

    /**
     * Api路径
     */
    @GetMapping("/api/**")
    public String api() {
        System.out.println("api被使用了");
        return "test";
    }
}
