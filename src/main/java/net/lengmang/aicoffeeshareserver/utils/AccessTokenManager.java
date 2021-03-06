package net.lengmang.aicoffeeshareserver.utils;

import com.google.gson.Gson;
import net.lengmang.aicoffeeshareserver.bean.AccessToken;
import net.lengmang.aicoffeeshareserver.controller.WeChatPushTemplate;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by YuLong on 2017/9/8.
 */
@Component
public class AccessTokenManager {

    private static AccessTokenManager accessTokenManager = null;

    @Value("${appID}")
    private String appId;
    @Value("${appSecret}")
    private String appSecret;

    @PostConstruct
    private void init() {
        accessTokenManager = this;
        accessTokenManager.appId = appId;
        accessTokenManager.appSecret = appSecret;
    }

    // 获取到的时间
    private static long time;
    private static AccessToken accessToken = new AccessToken();
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    private AccessTokenManager() {
    }

    public static AccessToken getAccessToken() {
        try {
            if (System.currentTimeMillis() - time > accessToken.getExpires_in()) {
                String new_access_token_url = access_token_url.replace("APPID", accessTokenManager.appId).replace("APPSECRET", accessTokenManager.appSecret);
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(new_access_token_url).build();
                Response response = client.newCall(request).execute();
                String result = response.body().string();
                accessToken = new Gson().fromJson(result, AccessToken.class);
                time = System.currentTimeMillis() / 1000;
            }
        } catch (Exception e) {

        }
        return accessToken;
    }
}
