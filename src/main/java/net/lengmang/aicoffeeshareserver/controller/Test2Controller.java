package net.lengmang.aicoffeeshareserver.controller;

import com.google.gson.JsonObject;
import net.lengmang.aicoffeeshareserver.bean.ReturnData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Test2Controller {

    @Value("${appID}")
    private String appID;
    @Value("${appSecret}")
    private String appSecret;

//    @GetMapping("/test2")
//    public String test(HttpServletRequest request) {
//        System.out.println(request.getRequestURL());
//        return appID + "\n" + appSecret;
//    }

    @ResponseBody
    @GetMapping("/test3")
    public String test3(HttpServletRequest request) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("appID", appID);
        jsonObject.addProperty("appSecret", appSecret);
        return new ReturnData(0, jsonObject).toString();
    }
}
