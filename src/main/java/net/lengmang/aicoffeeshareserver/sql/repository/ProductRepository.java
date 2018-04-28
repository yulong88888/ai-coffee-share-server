package net.lengmang.aicoffeeshareserver.sql.repository;

import net.lengmang.aicoffeeshareserver.sql.bean.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
