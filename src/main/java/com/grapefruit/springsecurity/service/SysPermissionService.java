package com.grapefruit.springsecurity.service;

import com.grapefruit.springsecurity.model.SysUser;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户权限处理
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-06-14 11:44 上午
 */
@Component
public class SysPermissionService {
    /*@Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysMenuService menuService;*/

    /**
     * 获取角色数据权限
     *
     * @param user 用户信息
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(SysUser user) {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        /*if (user.isAdmin())
        {
            roles.add("admin");
        }
        else
        {
            roles.addAll(roleService.selectRolePermissionByUserId(user.getUserId()));
        }*/
        roles.add("admin");
        return roles;
    }

    /**
     * 获取菜单数据权限
     *
     * @param user 用户信息
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(SysUser user) {
        Set<String> perms = new HashSet<String>();
        // 管理员拥有所有权限
        /*if (user.isAdmin())
        {
            perms.add("*:*:*");
        }
        else
        {
            perms.addAll(menuService.selectMenuPermsByUserId(user.getUserId()));
        }*/
        perms.add("*:*:*");
        return perms;
    }
}
