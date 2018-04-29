package net.lengmang.aicoffeeshareserver.controller;

import com.google.gson.JsonObject;
import net.lengmang.aicoffeeshareserver.bean.ReturnData;
import net.lengmang.aicoffeeshareserver.sql.bean.Product;
import net.lengmang.aicoffeeshareserver.sql.repository.ProductRepository;
import net.lengmang.aicoffeeshareserver.utils.ProductsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiProductsController {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Api路径
     */
    @ResponseBody
    @PostMapping(value = "/api/product/add")
    public String addProduct(@RequestBody Product product) {
        JsonObject jsonObject = new JsonObject();
        try {
            //获取产品数据
            List<Product> products = ProductsManager.getProducts();
            //对象中的equal方法方便给list的contains使用，没有比较ID
            //判断新产品数据是否有重复
            if (products.contains(product)) {
                jsonObject.addProperty("msg", "已经添加过的产品");
                return new ReturnData(1, jsonObject).toString();
            }
            productRepository.save(product);
            jsonObject.addProperty("msg", "添加成功");
            ProductsManager.clearProducts();
            return new ReturnData(0, jsonObject).toString();
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.addProperty("msg", "服务器出错");
            return new ReturnData(1, jsonObject).toString();
        }
    }
}
