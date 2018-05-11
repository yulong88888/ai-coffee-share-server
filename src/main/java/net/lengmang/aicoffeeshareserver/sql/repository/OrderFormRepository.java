package net.lengmang.aicoffeeshareserver.sql.repository;

import net.lengmang.aicoffeeshareserver.sql.bean.OrderForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderFormRepository extends JpaRepository<OrderForm, Integer> {

    public List<OrderForm> readByOpenId(String openId);
}
