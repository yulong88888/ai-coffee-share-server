package net.lengmang.aicoffeeshareserver.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.lengmang.aicoffeeshareserver.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Enumeration;

@Controller
public class ProjectController {

    @Value("${appID}")
    private String appId;
    @Value("${appSecret}")
    private String appSecret;

    /**
     * 配置通用路径
     */
    @GetMapping("/*")
    public String weChatLogin(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            Enumeration<String> enumeration = request.getParameterNames();
            if (enumeration.hasMoreElements()) {
                String state = request.getParameter("state");
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
                model.addAttribute("userInfo", userInfoLinkJson.toString());
                return "main";
            } else {
                String link = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                        "appid=" + appId + "&" +
                        "redirect_uri=" + URLEncoder.encode(request.getRequestURL().toString(), "utf-8") + "&" +
                        "response_type=code&" +
                        "scope=snsapi_userinfo&" +
                        "#wechat_redirect";
                response.sendRedirect(link);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Api路径
     */
    @GetMapping("/api/**")
    public String api() {
        System.out.println("api被使用了");
        return "test";
    }
}
