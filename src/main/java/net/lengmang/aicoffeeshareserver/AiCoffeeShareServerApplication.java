package net.lengmang.aicoffeeshareserver;

import net.lengmang.aicoffeeshareserver.interceptor.AdminLoginInterceptor;
import net.lengmang.aicoffeeshareserver.interceptor.UserLoginInterceptor;
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
            //校验对API数据库操作用户的登录状态
            registry.addInterceptor(new UserLoginInterceptor()).addPathPatterns("/api/**");
            registry.addInterceptor(new AdminLoginInterceptor()).addPathPatterns("/api/admin/**");
        }
    }
}
