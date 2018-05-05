package net.lengmang.aicoffeeshareserver.controller;

import com.google.gson.JsonObject;
import net.lengmang.aicoffeeshareserver.bean.ReturnData;
import net.lengmang.aicoffeeshareserver.sql.bean.Product;
import net.lengmang.aicoffeeshareserver.sql.repository.ProductRepository;
import net.lengmang.aicoffeeshareserver.utils.FileUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
public class ApiFileUploadController {

//    @Autowired
//    private ProductRepository productRepository;
//
//    @ResponseBody
//    @PostMapping("/api/uploadFile")
//    public String uploadFile(@RequestParam("file") MultipartFile file) {
//        JsonObject jsonObject = new JsonObject();
//        try {
//            String contentType = file.getContentType();
//            if (!(contentType.equals("image/png") || contentType.equals("image/jpeg"))) {
//                jsonObject.addProperty("msg", "亲，你确定传的是图片？");
//                return new ReturnData(1, jsonObject).toString();
//            }
//            //String fileName = file.getOriginalFilename();
//            String fileName = UUID.randomUUID().toString().replaceAll("-", "");
//            System.out.println("fileName-->" + fileName);
//            System.out.println("getContentType-->" + contentType);
//
//            FileUploader.uploadFile(file.getBytes(), fileName);
//            jsonObject.addProperty("msg", "上传成功");
//            return new ReturnData(0, jsonObject).toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//            jsonObject.addProperty("msg", "亲，服务器炸了哦");
//            return new ReturnData(1, jsonObject).toString();
//        }
//    }
//
//    @ResponseBody
//    @PostMapping("/api/upload")
//    public String uploadFile2(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
//        JsonObject jsonObject = new JsonObject();
//        try {
//            String type = request.getParameter("type");
//            String uploadId = request.getParameter("uploadId");
//            String fileName = file.getOriginalFilename();
//            fileName = uploadId + fileName.substring(fileName.lastIndexOf("."));
//            if (type.equals("image")) {
//                fileName = "image_" + fileName;
//                FileUploader.uploadFile(file.getBytes(), fileName);
//                Product productImage = productRepository.readByNameId(uploadId);
//                productImage.setImage(request.getServerName() + "/ai-coffee-share-static-resources");
//                productRepository.save(productImage);
//                jsonObject.addProperty("msg", "添加成功");
//            } else if (type.equals("icon")) {
//                fileName = "icon_" + fileName;
//                FileUploader.uploadFile(file.getBytes(), fileName);
//                Product productIcon = productRepository.readByNameId(uploadId);
//                productIcon.setImage(request.getServerName() + "/ai-coffee-share-static-resources");
//                productRepository.save(productIcon);
//                jsonObject.addProperty("msg", "添加成功");
//            } else if (type.equals("swipe")) {
//                jsonObject.addProperty("msg", "添加成功");
//            }
//            return new ReturnData(0, jsonObject).toString();
//        } catch (Exception e) {
//            jsonObject.addProperty("msg", "添加失败");
//            return new ReturnData(1, jsonObject).toString();
//        }
//    }
}
