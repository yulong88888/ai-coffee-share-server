package net.lengmang.aicoffeeshareserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AiCoffeeShareServerApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void UUID() {
        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
    }
}
