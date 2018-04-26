package net.lengmang.aicoffeeshareserver.controller;

import com.google.gson.JsonObject;
import net.lengmang.aicoffeeshareserver.bean.ReturnData;
import net.lengmang.aicoffeeshareserver.utils.FileUploader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Test2Controller {

    @Value("${appID}")
    private String appID;
    @Value("${appSecret}")
    private String appSecret;

//    @Value("${staticImgPath}")
//    private String staticImgPath;

    @Value("${isDev}")
    private boolean isDev;

//    @ResponseBody
//    @GetMapping("/test2")
//    public String test(HttpServletRequest request) {
//        System.out.println(request.getRequestURL());
//        return staticImgPath;
//    }

    @ResponseBody
    @GetMapping("/test3")
    public String test3() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("appID", appID);
        jsonObject.addProperty("appSecret", appSecret);
        return new ReturnData(0, jsonObject).toString();
    }

    @ResponseBody
    @GetMapping("/test4")
    public String test4() {
        //String path = Class.class.getClass().getResource("/").getPath();
        String path2 = System.getProperty("user.dir");
        return path2;
        //return path + "============" + path2;
    }

    @ResponseBody
    @GetMapping("/test5")
    public boolean test5() {
        return isDev;
    }

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @ResponseBody
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            String contentType = file.getContentType();
            String fileName = file.getOriginalFilename();
            System.out.println("fileName-->" + fileName);
            System.out.println("getContentType-->" + contentType);
            FileUploader.uploadFile(file.getBytes(), fileName, isDev);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "fuck";
        }
    }
}
