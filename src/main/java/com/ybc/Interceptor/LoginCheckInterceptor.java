package com.ybc.Interceptor;

import com.alibaba.fastjson.JSONObject;
import com.ybc.pojo.Result;
import com.ybc.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        log.info("登录操作，放行url:{}",url);
        //若为登录操作，放行
        if(url.contains("login")){
            log.info("登录操作，放行");
            return true;
        }
        //获取请求头中的token
        String jwt = request.getHeader("token");

        if(!(StringUtils.hasLength("jwt"))){
            log.info("令牌为空，返回错误登录信息");
            Result error = Result.error("NOT_LOGIN");
            //转为json格式的字符串
            String notlogin = JSONObject.toJSONString(error);
            response.getWriter().write(notlogin);
            return false;
        }
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败，返回错误登录信息");
            Result error = Result.error("NOT_LOGIN");
            //转为json格式的字符串
            String notlogin = JSONObject.toJSONString(error);
            response.getWriter().write(notlogin);
            return false;
        }
        log.info("放行");
        return true;

    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
