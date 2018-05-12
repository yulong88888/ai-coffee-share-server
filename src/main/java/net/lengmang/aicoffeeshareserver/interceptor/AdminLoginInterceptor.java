package net.lengmang.aicoffeeshareserver.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

//@Component
public class AdminLoginInterceptor implements HandlerInterceptor {

//    private static String adminOpenId;
//
//    private static boolean isDev;
//
//    @Value("${adminOpenId}")
//    private void setAppId(String adminOpenId) {
//        this.adminOpenId = adminOpenId;
//    }
//
//    @Value("${isDev}")
//    private void setMode(boolean isDev) {
//        this.isDev = isDev;
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
//        if (isDev) {
//            System.out.println("Admin开发模式中preHandle被调用");
//            return true;
//        }
//        System.out.println("adminPreHandle被调用");
//        HttpSession session = request.getSession();
//        if (!session.getAttribute("openId").equals(adminOpenId)) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
//        System.out.println("adminPostHandle被调用");
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        System.out.println("adminAfterCompletion被调用");
//    }
}
