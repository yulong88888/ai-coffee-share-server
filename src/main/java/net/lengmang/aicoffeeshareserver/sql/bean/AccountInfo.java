package net.lengmang.aicoffeeshareserver.sql.bean;

import com.google.gson.JsonObject;

/**
 * 用于用户数据库数据Array用户读取
 */
public class AccountInfo {

    private String name;
    private String phone;
    private String address;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("phone", phone);
        jsonObject.addProperty("address", address);
        return jsonObject.toString();
    }
}
