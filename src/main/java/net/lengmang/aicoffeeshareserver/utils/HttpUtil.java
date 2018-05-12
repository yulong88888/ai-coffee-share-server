package net.lengmang.aicoffeeshareserver.utils;

import com.google.gson.JsonObject;
import okhttp3.*;

/**
 * Created by GYL88 on 2017/9/9.
 */
public class HttpUtil {

    private HttpUtil() {
    }

    public static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");

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

    public static String doPost(String link, JsonObject json) {
        String result = "";
        try {
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = RequestBody.create(JSON, json.toString());
            Request request = new Request.Builder().url(link).post(requestBody).build();
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
