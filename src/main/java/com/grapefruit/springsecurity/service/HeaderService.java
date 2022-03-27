/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.springsecurity.service;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 相关描述
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-06-14 11:44 上午
 */
@Component
public class HeaderService {
    /**
     * 获取请求serviceId
     *
     * @param request 请求体
     * @return token toke
     */
    public String getServiceId(HttpServletRequest request) {
        return request.getHeader("ServiceId");
    }
}
