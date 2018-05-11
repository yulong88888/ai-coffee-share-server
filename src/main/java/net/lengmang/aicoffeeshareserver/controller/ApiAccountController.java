package net.lengmang.aicoffeeshareserver.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.lengmang.aicoffeeshareserver.bean.ReturnData;
import net.lengmang.aicoffeeshareserver.sql.bean.Account;
import net.lengmang.aicoffeeshareserver.sql.bean.AccountInfo;
import net.lengmang.aicoffeeshareserver.sql.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
public class ApiAccountController {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * 删除用户信息
     */
    @ResponseBody
    @PostMapping("/delete")
    public String delete(HttpServletRequest request, @RequestBody Map json) {
        JsonObject jsonObject = new JsonObject();
        try {
            String openId = request.getSession().getAttribute("openId").toString();
            System.out.println(openId);
            System.out.println(json);
            Account account = accountRepository.readByOpenId(openId);
            JsonArray jsonArray = new JsonParser().parse(account.getAccountInfo()).getAsJsonArray();
            jsonArray.remove((Integer) json.get("index"));
            account.setAccountInfo(jsonArray);
            accountRepository.save(account);
            jsonObject.addProperty("msg", "数据删除成功");
            return new ReturnData(0, jsonObject).toString();
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.addProperty("msg", "亲，服务器炸了哦");
            return new ReturnData(1, jsonObject).toString();
        }
    }

    /**
     * 修改用户信息
     */
    @ResponseBody
    @PostMapping("/update")
    public String update(HttpServletRequest request, @RequestBody Map json) {
        JsonObject jsonObject = new JsonObject();
        try {
            String openId = request.getSession().getAttribute("openId").toString();
            System.out.println(openId);
            System.out.println(json.toString());
            Account account = accountRepository.readByOpenId(openId);
            JsonArray jsonArray = new JsonParser().parse(account.getAccountInfo()).getAsJsonArray();
            jsonArray.set((Integer) json.get("index"), new JsonParser().parse(json.get("accountInfo").toString()).getAsJsonObject());
            account.setAccountInfo(jsonArray);
            accountRepository.save(account);
            jsonObject.addProperty("msg", "数据更新成功");
            return new ReturnData(0, jsonObject).toString();
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.addProperty("msg", "亲，服务器炸了哦");
            return new ReturnData(1, jsonObject).toString();
        }
    }

    /**
     * 添加用户地址
     */
    @ResponseBody
    @PostMapping("/set")
    public String setAddress(HttpServletRequest request, @RequestBody Map json) {
        JsonObject jsonObject = new JsonObject();
        try {
            String openId = request.getSession().getAttribute("openId").toString();
            System.out.println(openId);
            System.out.println(json.toString());
            Account account = accountRepository.readByOpenId(openId);
            JsonArray jsonArray = new JsonParser().parse(account.getAccountInfo()).getAsJsonArray();
            jsonArray.add(new JsonParser().parse(json.get("accountInfo").toString()).getAsJsonObject());
            account.setAccountInfo(jsonArray);
            accountRepository.save(account);
            jsonObject.addProperty("msg", "数据设置成功");
            return new ReturnData(0, jsonObject).toString();
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.addProperty("msg", "亲，服务器炸了哦");
            return new ReturnData(1, jsonObject).toString();
        }
    }

    /**
     * 获取用户地址
     */
    @ResponseBody
    @PostMapping("/getAccountInfo")
    public String getAccountInfo(HttpServletRequest request) {
        try {
            String openId = request.getSession().getAttribute("openId").toString();
            Account account = accountRepository.readByOpenId(openId);
            return new ReturnData(0, new JsonParser().parse(account.getAccountInfo()).getAsJsonArray()).toString();
        } catch (Exception e) {
            e.printStackTrace();
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("msg", "亲，服务器炸了哦");
            return new ReturnData(1, jsonObject).toString();
        }
    }
}
