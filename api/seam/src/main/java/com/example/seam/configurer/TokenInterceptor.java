package com.example.seam.configurer;

import com.example.seam.util.TokenUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 跨域请求会首先发一个option请求，直接返回正常状态并通过拦截器
        if (Objects.equals(request.getMethod(), "OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        response.setCharacterEncoding("utf-8");
        // 访问图片路径的时候token为空，抛出空指针异常
        try {
            String token = request.getHeader("token");
            if (token.length() != 0) {
                Map<String, Object> verify = TokenUtil.verifyToken(token);
                if (!verify.isEmpty() || verify.size() != 0) {
                    // 通过拦截器
                    return true;
                }
            }
        } catch (NullPointerException e) {
            e.fillInStackTrace();
        }
        response.setContentType("application/json;charset=utf-8");
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("msg", "token verify fail");
            jsonObject.put("code", "500");
            response.getWriter().append(jsonObject.toString());
            // 认证失败，未通过拦截器
        } catch (Exception e) {
            return false;
        }
        /**
         * 可以在此验证用户存不存在等操作
         */
        return false;
    }
}
