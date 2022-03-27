/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * 相关描述
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-06-14 8:28 上午
 */
@RestController
@RequestMapping("/")
public class Index {
    @PostMapping("/index")
    public String index() {
        return LocalDateTime.now().toString();
    }
}
