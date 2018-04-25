package net.lengmang.aicoffeeshareserver.utils;

import com.google.gson.Gson;
import net.lengmang.aicoffeeshareserver.bean.AccessToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by YuLong on 2017/9/8.
 */
public class AccessTokenManager {

    @Value("${appID}")
    private static String appId;
    @Value("${appSecret}")
    private static String appSecret;

    // 获取到的时间
    private static long time;
    private static AccessToken accessToken = new AccessToken();
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    private AccessTokenManager() {
    }

    public static AccessToken getAccessToken() {
        try {
            if (System.currentTimeMillis() - time > accessToken.getExpires_in()) {
                String new_access_token_url = access_token_url.replace("APPID", appId).replace("APPSECRET", appSecret);
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
