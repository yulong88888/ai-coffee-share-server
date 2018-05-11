package net.lengmang.aicoffeeshareserver.sql.repository;

import net.lengmang.aicoffeeshareserver.sql.bean.Account;
import net.lengmang.aicoffeeshareserver.sql.bean.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    public Account readByOpenId(String id);
}
