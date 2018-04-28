package net.lengmang.aicoffeeshareserver.controller;

import net.lengmang.aicoffeeshareserver.sql.bean.Product;
import net.lengmang.aicoffeeshareserver.sql.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestSqlController {

    @Autowired
    private ProductRepository productRepository;

    /**
     * 查询所有产品信息
     *
     * @return
     */
    @GetMapping(value = "/getProducts")
    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    /**
     * 存储一个对象
     */
    @GetMapping(value = "/setProduct")
    public Product setProductList() {
        Product product = new Product();
        product.setName("test");
        product.setItem("item");
        product.setPrice(66);
        product.setDescription("description");
        product.setInfo("info");
        product.setIconLink("iconLink");
        product.setImageLink("imageLink");
        return productRepository.save(product);
    }

    /**
     * 有条件查找
     */
    @GetMapping(value = "/getProduct/{id}")
    public Product findOne(@PathVariable("id") Integer id) {
        return productRepository.findById(id).get();
    }

    /**
     * 更新数据
     */
    @GetMapping(value = "/updateProduct/{id}/{name}")
    public Product updateProduct(@PathVariable("id") Integer id, @PathVariable("name") String name) {
        Product product = productRepository.findById(id).get();
        product.setId(id);
        product.setName(name);
        return productRepository.save(product);
    }

    /**
     * 删除数据
     */
    @GetMapping(value = "/deleteProduct/{id}")
    public void deleteProduct(@PathVariable("id") Integer id) {
        productRepository.deleteById(id);
    }
}
