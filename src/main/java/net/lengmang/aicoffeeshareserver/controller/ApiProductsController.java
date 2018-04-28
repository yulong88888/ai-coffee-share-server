package net.lengmang.aicoffeeshareserver.controller;

import com.google.gson.JsonObject;
import net.lengmang.aicoffeeshareserver.bean.ReturnData;
import net.lengmang.aicoffeeshareserver.sql.bean.Product;
import net.lengmang.aicoffeeshareserver.sql.repository.ProductRepository;
import net.lengmang.aicoffeeshareserver.utils.ProductsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ApiProductsController {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Api路径
     */
    @ResponseBody
    @PostMapping("/api/product/add")
    public String addProduct(@RequestParam Product product) {
        JsonObject jsonObject = new JsonObject();
        try {
            //获取产品数据
            List<Product> products = ProductsManager.getProducts();
            //判断新产品数据是否有重复
            if (products.contains(product)) {
                jsonObject.addProperty("msg", "已经添加过的产品");
                return new ReturnData(1, jsonObject).toString();
            }
            productRepository.save(product);
            jsonObject.addProperty("msg", "添加成功");
            return new ReturnData(0, jsonObject).toString();
        } catch (Exception e) {
            jsonObject.addProperty("msg", "服务器出错");
            return new ReturnData(1, jsonObject).toString();
        }
    }
}
