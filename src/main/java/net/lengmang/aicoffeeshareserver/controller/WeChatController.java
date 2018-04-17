package net.lengmang.aicoffeeshareserver.controller;

import net.lengmang.aicoffeeshareserver.utils.SignUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WeChatController {

    @ResponseBody
    @RequestMapping(value = "/WeChat", method = RequestMethod.GET)
    public String checkSignature(HttpServletRequest request) {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        }
        return "0.0";
    }

    @ResponseBody
    @RequestMapping(value = "/WeChat", method = RequestMethod.POST)
    public String WeChatData(@RequestBody String xml) {

        return "0.0";
    }
}
