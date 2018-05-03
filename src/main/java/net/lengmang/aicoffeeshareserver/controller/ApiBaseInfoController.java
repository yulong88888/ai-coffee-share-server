package net.lengmang.aicoffeeshareserver.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.lengmang.aicoffeeshareserver.bean.ReturnData;
import net.lengmang.aicoffeeshareserver.sql.bean.BaseInfo;
import net.lengmang.aicoffeeshareserver.sql.repository.BaseInfoRepository;
import net.lengmang.aicoffeeshareserver.utils.BaseInfoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiBaseInfoController {

    @Autowired
    private BaseInfoRepository baseInfoRepository;

    /**
     * 基础信息修改
     */
    @ResponseBody
    @GetMapping(value = "/api/baseInfo/set")
    public String setBaseInfo() {
        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setId(1);
        baseInfo.setMinPrice(2000);
        baseInfo.setDeliveryPrice(100);
        JsonArray jsonArray = new JsonArray();
        jsonArray.add("www.baidu.com");
        jsonArray.add("www.4dclass.com");
        jsonArray.add("www.lengmang.com");
        baseInfo.setImgDatas(jsonArray);
        baseInfoRepository.save(baseInfo);
        BaseInfoManager.clearBaseInfo();
        return "OK";
    }

    @ResponseBody
    @GetMapping(value = "/api/baseInfo/get")
    public String getProduct() {
        BaseInfo baseInfo = baseInfoRepository.findAll().get(0);
        JsonObject jsonObject = new JsonParser().parse(new Gson().toJson(baseInfo)).getAsJsonObject();
        jsonObject.remove("imgDatas");
        jsonObject.add("imgDatas", new JsonParser().parse(baseInfo.getImgDatas()).getAsJsonArray());
        return new ReturnData(0, jsonObject).toString();
    }
}
