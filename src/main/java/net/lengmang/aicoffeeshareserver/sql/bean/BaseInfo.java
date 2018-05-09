package net.lengmang.aicoffeeshareserver.sql.bean;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * 此类是商户基础数据
 * <p>
 * 包含数据
 * ========最少消费========
 * ========配送价格========
 * ========轮播图数据（JSON数据字符串）========
 * ========广告弹窗========
 */
@Entity
public class BaseInfo {

    @Id
    private Integer id;
    private Integer minPrice;
    private Integer deliveryPrice;
    @Lob
    private String imgDatas;
    private String adPic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(Integer deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getImgDatas() {
        return imgDatas;
    }

    public void setImgDatas(JsonArray imgDatas) {
        this.imgDatas = imgDatas.toString();
    }

    public String getAdPic() {
        return adPic;
    }

    public void setAdPic(String adPic) {
        this.adPic = adPic;
    }
}
