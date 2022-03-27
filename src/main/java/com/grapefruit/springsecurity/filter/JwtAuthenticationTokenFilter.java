package com.grapefruit.springsecurity.filter;

import com.alibaba.druid.sql.visitor.functions.Concat;
import com.grapefruit.springsecurity.constant.Constant;
import com.grapefruit.springsecurity.model.LoginUser;
import com.grapefruit.springsecurity.model.SysUser;
import com.grapefruit.springsecurity.service.LocalTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token过滤器 验证token有效性
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-06-14 11:44 上午
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private LocalTokenService localTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String serviceId = request.getHeader("ServiceId");
        String token = request.getHeader(Constant.AUTHORIZATION);
        if (!StringUtils.isEmpty(token)) {
            /*LoginUser loginUser = new LoginUser();

            SysUser user = new SysUser();
            user.setUserId(1L);
            user.setPassword("123456");
            user.setUserName("grapefruit");
            loginUser.setLoginLocation("CN");
            loginUser.setBrowser("chrome");
            loginUser.setToken("token");
            loginUser.setOs("mac");
            loginUser.setUser(user);*/

            LoginUser loginUser = localTokenService.getLoginUser(request);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        chain.doFilter(request, response);
    }
}
