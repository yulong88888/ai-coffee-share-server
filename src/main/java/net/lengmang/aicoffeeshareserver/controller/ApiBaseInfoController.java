package net.lengmang.aicoffeeshareserver.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.lengmang.aicoffeeshareserver.bean.ReturnData;
import net.lengmang.aicoffeeshareserver.sql.bean.BaseInfo;
import net.lengmang.aicoffeeshareserver.sql.repository.BaseInfoRepository;
import net.lengmang.aicoffeeshareserver.utils.BaseInfoManager;
import net.lengmang.aicoffeeshareserver.utils.FileUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping("/api/baseInfo")
public class ApiBaseInfoController {

    @Autowired
    private BaseInfoRepository baseInfoRepository;

    /**
     * 基础信息修改
     */
    @ResponseBody
    @PostMapping("/update")
    public String setBaseInfo(@RequestParam(value = "minPrice", defaultValue = "0") Integer minPrice,
                              @RequestParam(value = "deliveryPrice", defaultValue = "0") Integer deliveryPrice) {
        JsonObject jsonObject = new JsonObject();
        try {
            BaseInfo baseInfo = baseInfoRepository.findAll().get(0);
            baseInfo.setId(1);
            baseInfo.setMinPrice(minPrice);
            baseInfo.setDeliveryPrice(deliveryPrice);
            baseInfoRepository.save(baseInfo);
            BaseInfoManager.clearBaseInfo();
            jsonObject.addProperty("msg", "修改成功");
            return new ReturnData(0, jsonObject).toString();
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.addProperty("msg", "亲，服务器炸了哦");
            return new ReturnData(1, jsonObject).toString();
        }
    }

    /**
     * 上传图片
     */
    @ResponseBody
    @PostMapping("/uploadImg")
    public String uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        JsonObject jsonObject = new JsonObject();
        try {
            String contentType = file.getContentType();
            System.out.println("getContentType-->" + contentType);
            if (!(contentType.equals("image/png") || contentType.equals("image/jpeg"))) {
                jsonObject.addProperty("msg", "亲，你确定传的是图片？");
                return new ReturnData(1, jsonObject).toString();
            }
            String fileName = file.getOriginalFilename();
            String fileFormat = fileName.substring(fileName.lastIndexOf("."));
            String fileNewName = UUID.randomUUID().toString().replaceAll("-", "") + fileFormat;
            System.out.println("fileName-->" + fileNewName);
            //保存文件
            FileUploader.uploadFile(file.getBytes(), fileNewName);
            //编辑静态资源图片获取链接
            String imgLink = "http://" + request.getServerName() + "/ai-coffee-share-static-resources/" + fileNewName;
            //修改数据库文件
            BaseInfo baseInfo = baseInfoRepository.findAll().get(0);
            JsonArray jsonArray = new JsonParser().parse(baseInfo.getImgDatas()).getAsJsonArray();
            jsonArray.add(imgLink);
            baseInfo.setImgDatas(jsonArray);
            baseInfoRepository.save(baseInfo);
            //刷新缓存
            BaseInfoManager.clearBaseInfo();
            jsonObject.addProperty("msg", "上传成功");
            return new ReturnData(0, jsonObject).toString();
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.addProperty("msg", "亲，服务器炸了哦");
            return new ReturnData(1, jsonObject).toString();
        }
    }


    /**
     * 删除图片
     */
    @ResponseBody
    @PostMapping("/deleteImg")
    public String deleteImg(@RequestParam(value = "index", defaultValue = "-1") Integer index) {
        JsonObject jsonObject = new JsonObject();
        try {
            BaseInfo baseInfo = baseInfoRepository.findAll().get(0);
            JsonArray jsonArray = new JsonParser().parse(baseInfo.getImgDatas()).getAsJsonArray();
            jsonArray.remove(index);
            baseInfo.setImgDatas(jsonArray);
            baseInfoRepository.save(baseInfo);
            BaseInfoManager.clearBaseInfo();
            jsonObject.addProperty("msg", "删除成功");
            return new ReturnData(0, jsonObject).toString();
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.addProperty("msg", "亲，服务器炸了哦");
            return new ReturnData(1, jsonObject).toString();
        }
    }

    /**
     * 获取基础信息
     */
    @ResponseBody
    @GetMapping("/get")
    public String getProduct() {
        BaseInfo baseInfo = baseInfoRepository.findAll().get(0);
        JsonObject jsonObject = new JsonParser().parse(new Gson().toJson(baseInfo)).getAsJsonObject();
        jsonObject.remove("imgDatas");
        jsonObject.add("imgDatas", new JsonParser().parse(baseInfo.getImgDatas()).getAsJsonArray());
        return new ReturnData(0, jsonObject).toString();
    }
}
