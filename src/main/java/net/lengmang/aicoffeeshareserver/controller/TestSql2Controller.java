package net.lengmang.aicoffeeshareserver.controller;

import net.lengmang.aicoffeeshareserver.sql.bean.Product;
import net.lengmang.aicoffeeshareserver.sql.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestSql2Controller {

    @Autowired
    private ProductRepository productRepository;

    /**
     * 查询所有产品信息
     *
     * @return
     */
    @GetMapping(value = "/testReadByNameId")
    public Product getProductList() {
        return productRepository.readByNameId("1525073487678");
    }
}
