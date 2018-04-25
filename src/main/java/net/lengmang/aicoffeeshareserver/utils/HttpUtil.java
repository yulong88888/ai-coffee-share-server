package net.lengmang.aicoffeeshareserver.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by GYL88 on 2017/9/9.
 */
public class HttpUtil {

    private HttpUtil() {
    }

    public static String doGet(String link) {
        String result = "";
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(link).build();
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
