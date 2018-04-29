package net.lengmang.aicoffeeshareserver.sql.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String item;
    private double price;
    private String description;
    private String info;
    private String iconLink;
    private String imageLink;

    public Product() {

    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Product) {
            Product product = (Product) obj;
            if (this.name.equals(product.getName()) &&
                    this.item.equals(product.getItem()) &&
                    this.price == (product.getPrice()) &&
                    this.description.equals(product.getDescription()) &&
                    this.info.equals(product.getInfo()) &&
                    this.iconLink.equals(product.getIconLink()) &&
                    this.imageLink.equals(product.getName())) {
                return true;
            }
        }
        return false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getIconLink() {
        return iconLink;
    }

    public void setIconLink(String iconLink) {
        this.iconLink = iconLink;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
