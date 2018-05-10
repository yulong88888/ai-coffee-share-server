package net.lengmang.aicoffeeshareserver.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.lengmang.aicoffeeshareserver.bean.ReturnData;
import net.lengmang.aicoffeeshareserver.sql.bean.Product;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.util.List;

@RestController
@RequestMapping("/api/shopCart")
public class ApiShopCartController {

    /**
     * 将购买数据产品到Session中
     */
    @ResponseBody
    @PostMapping("/set")
    public String set(HttpServletRequest request, @RequestBody String buyProducts) {
        JsonObject jsonObject = new JsonObject();
        try {
            buyProducts = URLDecoder.decode(buyProducts, "utf-8");
            if (buyProducts.endsWith("=")) {
                buyProducts = buyProducts.substring(0, buyProducts.length() - 1);
            }
            JsonArray array = new JsonParser().parse(buyProducts).getAsJsonArray();
            HttpSession session = request.getSession();
            session.setAttribute("shopCart", array);
            jsonObject.addProperty("msg", "数据保存到服务器成功");
            return new ReturnData(0, jsonObject).toString();
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.addProperty("msg", "亲，服务器炸了哦");
            return new ReturnData(1, jsonObject).toString();
        }
    }

    /**
     * 读取购买数据产品到Session中
     */
    @ResponseBody
    @PostMapping("/get")
    public String get(HttpServletRequest request) {
        JsonObject jsonObject = new JsonObject();
        try {
            HttpSession session = request.getSession();
            JsonArray array = (JsonArray) session.getAttribute("shopCart");
            return new ReturnData(0, array).toString();
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.addProperty("msg", "亲，服务器炸了哦");
            return new ReturnData(1, jsonObject).toString();
        }
    }
}
