package net.lengmang.aicoffeeshareserver;

import net.lengmang.aicoffeeshareserver.utils.UserLoginInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class AiCoffeeShareServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiCoffeeShareServerApplication.class, args);
    }

    @Configuration
    static class WebMvcConfigurer extends WebMvcConfigurerAdapter {
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            super.addInterceptors(registry);
            registry.addInterceptor(new UserLoginInterceptor());
        }
    }
}
