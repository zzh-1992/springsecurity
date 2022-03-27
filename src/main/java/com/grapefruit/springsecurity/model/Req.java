/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.springsecurity.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 相关描述
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-06-14 11:44 上午
 */
@Data
public class Req {
    @NotBlank
    @Length(max = 5)
    private String name;
    private String password;
    private String token;
}
