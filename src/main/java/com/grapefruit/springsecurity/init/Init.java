/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.springsecurity.init;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 从nacos配置中心获取配置
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-09-26 7:30 下午
 */
@Component
public class Init {

    @NacosValue(value = "${name:}",autoRefreshed = true)
    private String name;

    @PostConstruct
    public void init(){
        System.out.println(name);
    }
}
