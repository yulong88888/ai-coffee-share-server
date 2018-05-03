package net.lengmang.aicoffeeshareserver.bean;

import net.lengmang.aicoffeeshareserver.sql.bean.Product;

import java.util.List;

/**
 * Created by JavaWorld on 2018-04-30.
 */
public class ReturnProduct {

    private String name;
    private List<Product> product;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return product;
    }

    public void setProducts(List<Product> products) {
        this.product = products;
    }

//    @Override
//    public String toString() {
//        return "ReturnProduct{" +
//                "name='" + name + '\'' +
//                ", products=" + products +
//                '}';
//    }
}
