package net.lengmang.aicoffeeshareserver.bean;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ReturnData {

    private int code;
    private Object recdata;

    public ReturnData(int code, Object object) {
        this.code = code;
        this.recdata = object;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
