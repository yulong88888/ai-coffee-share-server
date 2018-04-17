package net.lengmang.aicoffeeshareserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class WeChatController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/testRequest")
    public String testByRequest(HttpServletRequest request) {
        request.setAttribute("data", "testData Request");
        return "test";
    }

    @GetMapping("/testModel")
    public String testByModel(Model model) {
        model.addAttribute("data", "testData Model");
        return "test";
    }

    @GetMapping("/testMap")
    public String testByMap(Map<String, String> map) {
        map.put("data", "testData Map");
        return "test";
    }
}
