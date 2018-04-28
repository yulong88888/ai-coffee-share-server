package net.lengmang.aicoffeeshareserver.utils;

import net.lengmang.aicoffeeshareserver.sql.bean.Product;
import net.lengmang.aicoffeeshareserver.sql.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 这个类缓存所有的产品对象到！！！运行内存中！！！
 * 缓解每一次查询对数据库IO的压力
 * 注意：管理员从后台修改完任何产品信息，都要清空一次这个内存对象，并不会清空数据库数据
 */
public class ProductsManager {

    private static ProductsManager productsManager = null;

    @Autowired
    private ProductRepository productRepository;

    private List<Product> products;

    public ProductsManager() {
    }

    /**
     * 获取数据库的产品信息
     */
    private List<Product> get() {
        if (products == null) {
            products = productRepository.findAll();
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
}
