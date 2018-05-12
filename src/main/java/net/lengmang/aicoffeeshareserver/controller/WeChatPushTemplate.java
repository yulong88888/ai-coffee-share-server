package net.lengmang.aicoffeeshareserver.controller;

import com.google.gson.JsonObject;
import net.lengmang.aicoffeeshareserver.utils.AccessTokenManager;
import net.lengmang.aicoffeeshareserver.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class WeChatPushTemplate {

    private static WeChatPushTemplate WeChatPushTemplate = null;

    @Value("${wxTemplateOrderId}")
    private String wxTemplateOrderId;

    @PostConstruct
    private void init() {
        WeChatPushTemplate = this;
        WeChatPushTemplate.wxTemplateOrderId = wxTemplateOrderId;
    }

    private WeChatPushTemplate() {
    }

    //URL
    private String pushTemplateURL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

    public static void pushOrder(String openId, String orderId, double total, String url) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("touser", openId);
        jsonObject.addProperty("template_id", WeChatPushTemplate.wxTemplateOrderId);
        jsonObject.addProperty("url", url);
        JsonObject dataObject = new JsonObject();
        dataObject.add("first", getValueAndColor("恭喜您购买成功！", "#FF8000"));
        dataObject.add("keyword1", getValueAndColor(orderId, "#FF8000"));
        dataObject.add("keyword2", getValueAndColor(total + "", "#FF8000"));
        dataObject.add("keyword3", getValueAndColor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), "#FF8000"));
        dataObject.add("remark", getValueAndColor("欢迎再次购买！", "#FF8000"));
        jsonObject.add("data", dataObject);
        String link = WeChatPushTemplate.pushTemplateURL.replaceAll("ACCESS_TOKEN", AccessTokenManager.getAccessToken().getAccess_token());
        System.out.println("===>>>订单模板推送" + HttpUtil.doPost(link, jsonObject));
    }

    public static JsonObject getValueAndColor(String value, String color) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("value", value);
        jsonObject.addProperty("color", color);
        return jsonObject;
    }
}
