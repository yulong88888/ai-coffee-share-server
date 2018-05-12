package net.lengmang.aicoffeeshareserver;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import net.lengmang.aicoffeeshareserver.sql.bean.Account;
import net.lengmang.aicoffeeshareserver.sql.bean.Product;
import net.lengmang.aicoffeeshareserver.sql.repository.AccountRepository;
import net.lengmang.aicoffeeshareserver.sql.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AiCoffeeShareServerApplicationTests {

    @Test
    public void contextLoads() {
    }

    //
//    @Test
//    public void UUID() {
//        System.out.println(new Date().getTime() + "-" + UUID.randomUUID().toString().replaceAll("-", ""));
//    }
//
//    @Autowired
//    private AccountRepository accountRepository;
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Test
//    public void test() {
//        Account account = accountRepository.readByOpenId("oEnMJwfEjxOJvEYqWqQ77fi5G1dc");
//        if (account == null) {
//            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
//        } else {
//            System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");
//        }
//        Product oldProduct = productRepository.readByNameId("1525487613802");
//        if (oldProduct == null) {
//            System.out.println("FUCK");
//        } else {
//            System.out.println(oldProduct.getPrice());
//        }
//    }
//
//    @Test
//    public void test2() throws UnsupportedEncodingException {
//        String temp = "%5B%7B%22id%22%3A74%2C%22name%22%3A%22%E6%B5%8B%E8%AF%95%E4%BA%A7%E5%93%81%22%2C%22nameId%22%3A%221525487613802%22%2C%22item%22%3A%22%E6%B5%8B%E8%AF%95%E4%BA%A7%E5%93%81%22%2C%22price%22%3A6.6%2C%22description%22%3A%22%E6%B5%8B%E8%AF%95%E4%BA%A7%E5%93%81%22%2C%22info%22%3A%22%E6%B5%8B%E8%AF%95%E4%BA%A7%E5%93%81%22%2C%22icon%22%3A%22http%3A%2F%2Flocalhost%2Fai-coffee-share-static-resources%2Ficon_1525487613802.jpg%22%2C%22image%22%3A%22http%3A%2F%2Flocalhost%2Fai-coffee-share-static-resources%2Fimage_1525487613802.jpg%22%2C%22count%22%3A2%7D%5D=";
//        temp = URLDecoder.decode(temp, "utf-8");
//        if (temp.endsWith("=")) {
//            temp = temp.substring(0, temp.length() - 1);
//        }
//        System.out.println(temp);
//        //String str = "[{\"id\":74,\"name\":\"测试产品\",\"nameId\":\"1525487613802\",\"item\":\"测试产品\",\"price\":6.6,\"description\":\"测试产品\",\"info\":\"测试产品\",\"icon\":\"http://localhost/ai-coffee-share-static-resources/icon_1525487613802.jpg\",\"image\":\"http://localhost/ai-coffee-share-static-resources/image_1525487613802.jpg\",\"count\":2}]";
//        JsonArray array = new JsonParser().parse(temp).getAsJsonArray();
//        System.out.println(array);
//    }
}
