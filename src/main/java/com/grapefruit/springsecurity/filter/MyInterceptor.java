/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.springsecurity.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 相关描述
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-06-14 9:41 上午
 */
@Component
public class MyInterceptor implements HandlerInterceptor {
    // 配置拦截器后需注册拦截器 WebMvcConfigurer registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/login/**");
    /**
     *
     *      在拦截器中获取请求体
     *          BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
     *          StringBuilder sb = new StringBuilder();
     *          String line = null;
     *          while ((line = br.readLine()) != null) {
     *          sb.append(line);
     *          }
     *          String body  = sb.toString();
     *
     *          body:{"username": "grapefruit","password": "123456","code": "123","uuid": "ggggg"}
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器:" + request.toString());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        System.out.println("拦截器:" + request.toString());

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
