package com.grapefruit.springsecurity.service;

import com.grapefruit.springsecurity.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * RuoYi首创 自定义权限实现，ss取自SpringSecurity首字母
 *
 * @author ruoyi
 */
/**
 * 客户端工具类
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-06-14 11:44 上午
 */
@Service("ss")
public class PermissionService {
    /**
     * 所有权限标识
     */
    private static final String ALL_PERMISSION = "*:*:*";

    @Autowired
    private HeaderService headerService;

    /**
     * 验证用户是否具备某权限
     *
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    public boolean hasPermi(String permission) {
        if (StringUtils.isEmpty(permission)) {
            return false;
        }

        HttpServletRequest request = ServletUtils.getRequest();

        String serviceId = headerService.getServiceId(request);
        //return hasPermissions(loginUser.getPermissions(), permission);

        return hasPermissions(serviceId, permission);
    }

    /**
     * 判断是否包含权限
     *
     * @param serviceId  权限列表
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    private boolean hasPermissions(String serviceId, String permission) {
        return "grape".equals(serviceId);
    }
}
