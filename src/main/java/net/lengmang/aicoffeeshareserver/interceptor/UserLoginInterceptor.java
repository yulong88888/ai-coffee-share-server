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
import java.util.Enumeration;

@Component
public class UserLoginInterceptor implements HandlerInterceptor {

    private static String appId;

    private static boolean isDev;

    @Value("${appID}")
    private void setAppId(String appId) {
        this.appId = appId;
    }

    @Value("${isDev}")
    private void setMode(boolean isDev) {
        this.isDev = isDev;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (isDev) {
            System.out.println("开发模式中preHandle被调用");
            return true;
        }
        System.out.println("preHandle被调用");
        HttpSession session = request.getSession();
//        if (session.getAttribute("openId").equals("") || session.getAttribute("openId") == null) {
        if (session == null) {
            String link = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                    "appid=" + appId + "&" +
                    "redirect_uri=" + URLEncoder.encode(request.getRequestURL().toString(), "utf-8") + "&" +
                    "response_type=code&" +
                    "scope=snsapi_userinfo&" +
                    "#wechat_redirect";
            response.sendRedirect(link);
            return false;
        } else {
            return true;
        }

//        Enumeration<String> enumeration = request.getParameterNames();
//        if (enumeration.hasMoreElements()) {
//            String openId = request.getParameter("openId");
//            if (openId != null || !openId.equals("")) {
//                if (!request.getSession().getAttribute(openId).equals(openId)) {
//                    String link = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
//                            "appid=" + appId + "&" +
//                            "redirect_uri=" + URLEncoder.encode(request.getRequestURL().toString(), "utf-8") + "&" +
//                            "response_type=code&" +
//                            "scope=snsapi_userinfo&" +
//                            "#wechat_redirect";
//                    response.sendRedirect(link);
//                    return false;
//                }
//                return true;
//            }
//        }
//        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        System.out.println("postHandle被调用");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("afterCompletion被调用");
    }
}
