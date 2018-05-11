package net.lengmang.aicoffeeshareserver.sql.bean;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.sql.Timestamp;

/**
 * Json字符串
 * {"phone":"1**********","name":"***","address":""}
 * private String accountInfo;
 * <p>
 * Json字符串 array
 * {"productId":"id","productName":"***","productCount":"","productPrice":""}
 * private String orderInfo;
 */

@Entity
public class OrderForm {

    @Id
    @GeneratedValue
    private Integer id;
    private String openId;
    private String orderId;
    private String state;
    //Json字符串
    //{"phone":"1**********","name":"***","address":""}
    @Lob
    private String accountInfo;
    //Json字符串 array
    //{"productId":"id","productName":"***","productCount":"","productPrice":""}
    @Lob
    private String orderInfo;
    private double total;
    private String msg;
    @CreationTimestamp
    private Timestamp time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(JsonObject accountInfo) {
        this.accountInfo = accountInfo.toString();
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(JsonArray orderInfo) {
        this.orderInfo = orderInfo.toString();
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
