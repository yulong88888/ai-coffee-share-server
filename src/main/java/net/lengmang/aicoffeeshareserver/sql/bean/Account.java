package net.lengmang.aicoffeeshareserver.sql.bean;

import com.google.gson.JsonArray;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.sql.Timestamp;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private Integer id;
    private String openId;
    //Json字符串数组
    //{"phone":"1**********","name":"***","address":""}
    @Lob
    private String accountInfo;
    //购买次数
    private Integer count;
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

    /**
     * Json字符串数组
     * {"phone":"1**********","name":"***","address":""}
     */
    public String getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(JsonArray accountInfoArray) {
        this.accountInfo = accountInfoArray.toString();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
