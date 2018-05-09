package net.lengmang.aicoffeeshareserver.sql.bean;

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
    private String total;
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

    public void setAccountInfo(String accountInfo) {
        this.accountInfo = accountInfo;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
