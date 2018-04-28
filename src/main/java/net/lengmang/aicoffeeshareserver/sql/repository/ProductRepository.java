package net.lengmang.aicoffeeshareserver.sql.repository;

import net.lengmang.aicoffeeshareserver.sql.bean.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    public List<Product> readByItem(String item);
}
