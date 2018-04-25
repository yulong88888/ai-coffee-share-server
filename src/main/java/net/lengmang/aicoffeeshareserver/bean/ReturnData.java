package net.lengmang.aicoffeeshareserver.bean;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ReturnData {

    private int code;
    private JsonObject recdata;

    public ReturnData(int code, JsonObject jsonObject) {
        this.code = code;
        this.recdata = jsonObject;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
