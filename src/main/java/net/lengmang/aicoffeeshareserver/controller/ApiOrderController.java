package net.lengmang.aicoffeeshareserver.controller;

import com.google.gson.*;
import net.lengmang.aicoffeeshareserver.bean.ReturnData;
import net.lengmang.aicoffeeshareserver.sql.bean.Account;
import net.lengmang.aicoffeeshareserver.sql.bean.OrderForm;
import net.lengmang.aicoffeeshareserver.sql.repository.AccountRepository;
import net.lengmang.aicoffeeshareserver.sql.repository.OrderFormRepository;
import net.lengmang.aicoffeeshareserver.sql.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class ApiOrderController {

    @Autowired
    private OrderFormRepository orderFormRepository;
    @Autowired
    private ProductRepository productRepository;

//    /**
//     * 修改订单信息
//     */
//    @ResponseBody
//    @PostMapping("/update")
//    public String update(HttpServletRequest request, @RequestBody Map json) {
//        JsonObject jsonObject = new JsonObject();
//        try {
//            String openId = request.getSession().getAttribute("openId").toString();
//            System.out.println(openId);
//            System.out.println(json.toString());
//            Account account = accountRepository.readByOpenId(openId);
//            JsonArray jsonArray = new JsonParser().parse(account.getAccountInfo()).getAsJsonArray();
//            jsonArray.set((Integer) json.get("index"), new JsonParser().parse(json.get("accountInfo").toString()).getAsJsonObject());
//            account.setAccountInfo(jsonArray);
//            accountRepository.save(account);
//            jsonObject.addProperty("msg", "数据更新成功");
//            return new ReturnData(0, jsonObject).toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//            jsonObject.addProperty("msg", "亲，服务器炸了哦");
//            return new ReturnData(1, jsonObject).toString();
//        }
//    }

    /**
     * 添加订单
     */
    @ResponseBody
    @PostMapping("/set")
    public String setOrder(HttpServletRequest request, @RequestBody Map json) {
        JsonObject jsonObject = new JsonObject();
        try {
            String openId = request.getSession().getAttribute("openId").toString();
            System.out.println(openId);
            System.out.println(json.toString());
            JsonObject accountInfoJsonObject = new JsonParser().parse(new Gson().toJson(json.get("accountInfo"))).getAsJsonObject();
            JsonArray orderInfoJsonArray = new JsonParser().parse(new Gson().toJson(json.get("orderInfo"))).getAsJsonArray();
            String msg = json.get("msg").toString();
            if (orderInfoJsonArray.size() == 0) {
                jsonObject.addProperty("msg", "没有购买任何东西");
                return new ReturnData(0, jsonObject).toString();
            }
            OrderForm orderForm = new OrderForm();
            orderForm.setOpenId(openId);
            String orderId = new Date().getTime() + "-" + UUID.randomUUID().toString().replaceAll("-", "");
            orderForm.setOrderId(orderId);
            orderForm.setState("0");
            orderForm.setAccountInfo(accountInfoJsonObject);
            orderForm.setOrderInfo(orderInfoJsonArray);
            double total = 0;
            for (JsonElement jsonEle : orderInfoJsonArray) {
                JsonObject jsonObj = jsonEle.getAsJsonObject();
                System.out.println("===>>>" + jsonObj);
                String nameId = jsonObj.get("nameId").getAsString();
                double price = productRepository.readByNameId(nameId).getPrice();
                int productCount = jsonObj.get("count").getAsInt();
                total += productCount * price;
            }
            total = Double.parseDouble(new DecimalFormat("#.00").format(total));
            orderForm.setTotal(total);
            orderForm.setMsg(msg);
            orderFormRepository.save(orderForm);
            WeChatPushTemplate.pushOrder(openId, orderId, total, request.getServerName() + request.getContextPath());
            jsonObject.addProperty("msg", "下单成功");
            return new ReturnData(0, jsonObject).toString();
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.addProperty("msg", "亲，服务器炸了哦");
            return new ReturnData(1, jsonObject).toString();
        }
    }

    /**
     * 获取某人名下订单
     */
    @ResponseBody
    @PostMapping("/get")
    public String getOrder(HttpServletRequest request) {
        try {
            String openId = request.getSession().getAttribute("openId").toString();
            List<OrderForm> orderForms = orderFormRepository.readByOpenId(openId);
            return new ReturnData(0, orderForms).toString();
        } catch (Exception e) {
            e.printStackTrace();
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("msg", "亲，服务器炸了哦");
            return new ReturnData(1, jsonObject).toString();
        }
    }

    /**
     * 获取全部订单
     */
    @ResponseBody
    @PostMapping("/getAll")
    public String getOrder() {
        try {
            List<OrderForm> orderForms = orderFormRepository.findAll();
            return new ReturnData(0, orderForms).toString();
        } catch (Exception e) {
            e.printStackTrace();
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("msg", "亲，服务器炸了哦");
            return new ReturnData(1, jsonObject).toString();
        }
    }
}
