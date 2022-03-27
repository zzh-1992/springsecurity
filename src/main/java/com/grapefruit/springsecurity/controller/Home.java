/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.springsecurity.controller;

import com.grapefruit.springsecurity.model.LoginBody;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * 相关描述
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-06-14 8:28 上午
 */
@RestController
@RequestMapping("/fruit")
public class Home {

    @PreAuthorize("@ss.hasPermi('home:job:list')")
    @PostMapping("/home")
    public LoginBody home(@RequestBody @Valid LoginBody body){
        System.out.println("welcome===home");
        body.setUuid(LocalDateTime.now().toString());
        return body;
    }

    @PostMapping("/logout")
    public String logout(@RequestBody LoginBody body){
        return body.toString();
    }
}
