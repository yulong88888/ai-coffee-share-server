package net.lengmang.aicoffeeshareserver.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.lengmang.aicoffeeshareserver.bean.ReturnData;
import net.lengmang.aicoffeeshareserver.sql.bean.Product;
import net.lengmang.aicoffeeshareserver.sql.repository.ProductRepository;
import net.lengmang.aicoffeeshareserver.utils.FileUploader;
import net.lengmang.aicoffeeshareserver.utils.ProductsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ApiProductController {

    @Autowired
    private ProductRepository productRepository;

    /**
     * 产品增加路径
     */
    @ResponseBody
    @PostMapping(value = "/api/product/set")
    public String setProduct(@RequestParam("file") MultipartFile file, @RequestParam("data") String productJsonStr, HttpServletRequest request) {
        JsonObject jsonObject = new JsonObject();
        try {
            Product product = new Gson().fromJson(productJsonStr, Product.class);
            //获取产品数据
            List<Product> products = ProductsManager.getProducts();
            //对象中的equal方法方便给list的contains使用，没有比较ID
            //判断新产品数据是否有重复
            if (products.contains(product)) {
                jsonObject.addProperty("msg", "已经添加过的产品");
                return new ReturnData(1, jsonObject).toString();
            }
            //开始处理图片数据
            String fileName = file.getOriginalFilename();
            fileName = product.getNameId() + fileName.substring(fileName.lastIndexOf("."));
            FileUploader.uploadFile(file.getBytes(), "image_" + fileName);
            FileUploader.uploadFile(file.getBytes(), "icon_" + fileName);
            String picLink = request.getServerName() + "/ai-coffee-share-static-resources/";
            product.setIcon(picLink + "icon_" + fileName);
            product.setImage(picLink + "image_" + fileName);
            productRepository.save(product);
            jsonObject.addProperty("msg", "添加成功");
            //刷新内存数据
            ProductsManager.clearProducts();
            return new ReturnData(0, jsonObject).toString();
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.addProperty("msg", "服务器出错");
            return new ReturnData(1, jsonObject).toString();
        }
    }


    /**
     * 产品修改路径
     */
    @ResponseBody
    @PostMapping(value = "/api/product/update")
    public String updateProduct(@RequestParam("file") MultipartFile file, @RequestParam("data") String productJsonStr, HttpServletRequest request) {
        JsonObject jsonObject = new JsonObject();
        try {
            Product product = new Gson().fromJson(productJsonStr, Product.class);
            //先根据设备NameID找到设备记录位置
            Product oldProduct = productRepository.readByNameId(product.getNameId());
            //读取icon和image图片的名称并删除他们
            String iconPath = oldProduct.getIcon();
            String needDelIconFile = iconPath.substring(iconPath.lastIndexOf("/"));
            String imagePath = oldProduct.getImage();
            String needDelImageFile = imagePath.substring(iconPath.lastIndexOf("/"));
            FileUploader.deleteFile(needDelIconFile);
            FileUploader.deleteFile(needDelImageFile);
            //开始处理图片数据
            String fileName = file.getOriginalFilename();
            fileName = product.getNameId() + fileName.substring(fileName.lastIndexOf("."));
            FileUploader.uploadFile(file.getBytes(), "image_" + fileName);
            FileUploader.uploadFile(file.getBytes(), "icon_" + fileName);
            String picLink = request.getServerName() + "/ai-coffee-share-static-resources/";
            product.setIcon(picLink + "icon_" + fileName);
            product.setImage(picLink + "image_" + fileName);
            productRepository.save(product);
            jsonObject.addProperty("msg", "修改成功");
            //刷新内存数据
            ProductsManager.clearProducts();
            return new ReturnData(0, jsonObject).toString();
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.addProperty("msg", "服务器出错");
            return new ReturnData(1, jsonObject).toString();
        }
    }

    /**
     * 查询产品修改路径
     */
    @ResponseBody
    @GetMapping(value = "/api/product/get")
    public String getProduct() {
        String temp = new Gson().toJson(ProductsManager.getBetterProducts());
        JsonArray jsonArray = new JsonParser().parse(temp).getAsJsonArray();
        return new ReturnData(0, jsonArray).toString();
    }

    /**
     * 删除产品
     */
    @ResponseBody
    @PostMapping(value = "/api/product/delete")
    public String deleteProduct(@RequestBody Product product) {
        JsonObject jsonObject = new JsonObject();
        Product oldProduct = productRepository.readByNameId(product.getNameId());
        productRepository.deleteById(oldProduct.getId());
        jsonObject.addProperty("msg", "删除成功");
        //刷新内存数据
        ProductsManager.clearProducts();
        return new ReturnData(0, jsonObject).toString();
    }

//    /**
//     * Api路径
//     */
//    @ResponseBody
//    @PostMapping(value = "/api/product/add")
//    public String addProduct1(@RequestBody Product product) {
//        JsonObject jsonObject = new JsonObject();
//        try {
//            //获取产品数据
//            List<Product> products = ProductsManager.getProducts();
//            //对象中的equal方法方便给list的contains使用，没有比较ID
//            //判断新产品数据是否有重复
//            if (products.contains(product)) {
//                jsonObject.addProperty("msg", "已经添加过的产品");
//                return new ReturnData(1, jsonObject).toString();
//            }
//            productRepository.save(product);
//            jsonObject.addProperty("msg", "添加成功");
//            ProductsManager.clearProducts();
//            return new ReturnData(0, jsonObject).toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//            jsonObject.addProperty("msg", "服务器出错");
//            return new ReturnData(1, jsonObject).toString();
//        }
//    }
}
