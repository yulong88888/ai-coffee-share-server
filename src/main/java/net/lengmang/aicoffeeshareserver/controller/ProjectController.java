package net.lengmang.aicoffeeshareserver.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.lengmang.aicoffeeshareserver.bean.ReturnData;
import net.lengmang.aicoffeeshareserver.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.UUID;

@Controller
public class ProjectController {

    @Value("${appID}")
    private String appId;
    @Value("${appSecret}")
    private String appSecret;

    /**
     * 获取用户基本信息
     * 需要前端POST code过来
     */
    @ResponseBody
    @PostMapping("/getBaseInfo")
    public String weChatLogin(HttpServletRequest request) {
        String code = request.getParameter("code");
        //获取access_token
        String getAccessTokenByCodeLink = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" + appId + "&" +
                "secret=" + appSecret + "&" +
                "code=" + code + "&" +
                "grant_type=authorization_code";
        String accessTokenByCodeLinkResult = HttpUtil.doGet(getAccessTokenByCodeLink);
        JsonObject getAccessTokenByCodeJson = new JsonParser().parse(accessTokenByCodeLinkResult).getAsJsonObject();
        String refresh_token = getAccessTokenByCodeJson.get("refresh_token").toString().replaceAll("\"", "");
        System.out.println("====================================================================");
        System.out.println(getAccessTokenByCodeJson);
        System.out.println("====================================================================");
        //获取refresh_token
        String getRefreshTokenByRefreshTokenLink = "https://api.weixin.qq.com/sns/oauth2/refresh_token?" +
                "appid=" + appId + "&" +
                "grant_type=refresh_token&" +
                "refresh_token=" + refresh_token;
        String refreshTokenByRefreshTokenResult = HttpUtil.doGet(getRefreshTokenByRefreshTokenLink);
        JsonObject refreshTokenByRefreshTokenJson = new JsonParser().parse(refreshTokenByRefreshTokenResult).getAsJsonObject();
        String access_token = refreshTokenByRefreshTokenJson.get("access_token").toString().replaceAll("\"", "");
        String openid = refreshTokenByRefreshTokenJson.get("openid").toString().replaceAll("\"", "");
        //获取用户信息
        String userInfoLink = "https://api.weixin.qq.com/sns/userinfo?" +
                "access_token=" + access_token + "&" +
                "openid=" + openid;
        String userInfoLinkResult = HttpUtil.doGet(userInfoLink);
        JsonObject userInfoLinkJson = new JsonParser().parse(userInfoLinkResult).getAsJsonObject();
        System.out.println("====================================================================");
        System.out.println(userInfoLinkJson);
        System.out.println("====================================================================");
        return new ReturnData(0, userInfoLinkJson).toString();
    }

    /**
     * 重定向路径
     */
    @GetMapping("/*")
    public String redirectPath(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("wechat登录被使用了");
            Enumeration<String> enumeration = request.getParameterNames();
            String uuid = "";
            //如果没有code和state，则需要去微信认证登录
            if (!enumeration.hasMoreElements()) {
                uuid = UUID.randomUUID().toString();
                request.getSession().setAttribute(uuid, uuid);
                String url = request.getRequestURL().toString();
                String link = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                        "appid=" + appId + "&" +
                        "redirect_uri=" + URLEncoder.encode(url, "utf-8") + "&" +
                        "response_type=code&" +
                        "scope=snsapi_userinfo&" +
                        "state=" + uuid + "&" +
                        "#wechat_redirect";
                response.sendRedirect(link);
                return "";
            } else {
                String state = request.getParameter("state");
                if (request.getSession().getAttribute(state) != null) {
                    if (request.getSession().getAttribute(state).equals(state)) {
                        request.getSession().removeAttribute(state);
                        return "index";
                    }
                }
                uuid = UUID.randomUUID().toString();
                request.getSession().setAttribute(uuid, uuid);
                String url = request.getRequestURL().toString();
                String link = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                        "appid=" + appId + "&" +
                        "redirect_uri=" + URLEncoder.encode(url, "utf-8") + "&" +
                        "response_type=code&" +
                        "scope=snsapi_userinfo&" +
                        "state=" + uuid + "&" +
                        "#wechat_redirect";
                response.sendRedirect(link);
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }
}
