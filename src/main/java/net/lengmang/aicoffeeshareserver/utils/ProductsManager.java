package net.lengmang.aicoffeeshareserver.utils;

import net.lengmang.aicoffeeshareserver.bean.ReturnProduct;
import net.lengmang.aicoffeeshareserver.sql.bean.Product;
import net.lengmang.aicoffeeshareserver.sql.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 这个类缓存所有的产品对象到！！！运行内存中！！！
 * 缓解每一次查询对数据库IO的压力
 * 注意：管理员从后台修改完任何产品信息，都要清空一次这个内存对象，并不会清空数据库数据
 */
@Component
public class ProductsManager {

    private static ProductsManager productsManager = null;

    @Autowired
    private ProductRepository productRepository;

    private List<Product> products;

    private List<ReturnProduct> betterProducts = new ArrayList<>();

    @PostConstruct
    private void init() {
        productsManager = this;
        productsManager.productRepository = productRepository;
    }


    private ProductsManager() {
    }

    /**
     * 获取数据库的产品信息
     */
    private List<Product> get() {
        if (products == null || products.size() == 0) {
            products = this.productRepository.findAll();
        }
        return products;
    }

    /**
     * 清空数据库的产品信息
     */
    private void clear() {
        if (products != null) {
            products.clear();
        }
        if (betterProducts != null) {
            betterProducts.clear();
        }
    }

    /**
     * 获取好排序的数据
     */
    private List<ReturnProduct> getBetter() {
        if (betterProducts.size() > 0) {
            return betterProducts;
        }
        //从内存读取
        List<Product> products = get();
        // 获取产品分组数据
        List<String> resultItems = new ArrayList<>();
        for (Product productItem : products) {
            String temp = productItem.getItem();
            if (!resultItems.contains(temp)) { //判断分组名称是否重复
                resultItems.add(temp);
            }
        }
        // 遍历item分组，通过分组获取产品
        for (int i = 0; i < resultItems.size(); i++) {
            ReturnProduct returnProduct = new ReturnProduct();
            returnProduct.setName(resultItems.get(i));
            returnProduct.setProducts(productRepository.readByItem(resultItems.get(i))); //通过分组获取产品
            betterProducts.add(returnProduct);
        }
        return betterProducts;
    }

    public static List<Product> getProducts() {
        if (productsManager == null) {
            productsManager = new ProductsManager();
        }
        return productsManager.get();
    }

    public static void clearProducts() {
        if (productsManager == null) {
            productsManager = new ProductsManager();
        }
        productsManager.clear();
    }

    public static List<ReturnProduct> getBetterProducts() {
        if (productsManager == null) {
            productsManager = new ProductsManager();
        }
        return productsManager.getBetter();
    }
}
