package net.lengmang.aicoffeeshareserver.controller;

import com.google.gson.JsonObject;
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
public class ApiFileUploadController {

    @Autowired
    private ProductRepository productRepository;

    @ResponseBody
    @PostMapping("/api/upload")
    public String uploadFile2(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        JsonObject jsonObject = new JsonObject();
        try {
            String type = request.getParameter("type");
            String uploadId = request.getParameter("uploadId");
            String fileName = file.getOriginalFilename();
            fileName = uploadId + fileName.substring(fileName.lastIndexOf("."));
            if (type.equals("image")) {
                fileName = "image_" + fileName;
                FileUploader.uploadFile(file.getBytes(), fileName);
                Product productImage = productRepository.readByNameId(uploadId);
                productImage.setImageLink(request.getServerName() + "/ai-coffee-share-static-resources");
                productRepository.save(productImage);
                jsonObject.addProperty("msg", "添加成功");
            } else if (type.equals("icon")) {
                fileName = "icon_" + fileName;
                FileUploader.uploadFile(file.getBytes(), fileName);
                Product productIcon = productRepository.readByNameId(uploadId);
                productIcon.setImageLink(request.getServerName() + "/ai-coffee-share-static-resources");
                productRepository.save(productIcon);
                jsonObject.addProperty("msg", "添加成功");
            } else if (type.equals("swipe")) {
                jsonObject.addProperty("msg", "添加成功");
            }
            return new ReturnData(0, jsonObject).toString();
        } catch (Exception e) {
            jsonObject.addProperty("msg", "添加失败");
            return new ReturnData(1, jsonObject).toString();
        }
    }
}
