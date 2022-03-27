package com.grapefruit.springsecurity.service;

import com.grapefruit.springsecurity.cache.GrapeCache;
import com.grapefruit.springsecurity.constant.Constant;
import com.grapefruit.springsecurity.model.LoginUser;
import com.grapefruit.springsecurity.model.SysUser;
import com.grapefruit.utils.security.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * token处理器
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2021-06-14 11:44 上午
 */
@Component
public class LocalTokenService {
    @Autowired
    private GrapeCache cache;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            // 从token获取uuid token有效的话续期（获取uuid,将uuid保存到缓存，并设置生存时间）
            String uuid = getUUIDFromToken(token);

            // 获取redis缓存中到token-uuid
            Long expire = Optional.ofNullable(redisTemplate.opsForValue().getOperations().getExpire(uuid)).orElse(0L);
            if (expire > 0) {
                // 当redis中当uuid还存在时，对token-uuid续命30分钟
                redisTemplate.opsForValue().set(uuid, uuid, 30L, TimeUnit.MINUTES);
            }

            // 从token获取用户信息
            String userName = getUsernameFromToken(token);

            // 从缓存获取用户
            //return cache.getObjCache(Constant.USER, userName, LoginUser.class);
            LoginUser loginUser = new LoginUser();
            SysUser user = new SysUser();
            user.setUserId(1L);
            user.setPassword("123456");
            user.setUserName("grapefruit");
            loginUser.setLoginLocation("CN");
            loginUser.setBrowser("chrome");
            loginUser.setToken("token");
            loginUser.setOs("mac");
            loginUser.setUser(user);
            return loginUser;
        }
        return null;
    }

    /**
     * 设置用户身份信息
     */
    /*public void setLoginUser(LoginUser loginUser) {
        if (StringUtils.isNotNull(loginUser) && StringUtils.isNotEmpty(loginUser.getToken())) {
            refreshToken(loginUser);
        }
    }*/

    /**
     * 删除用户身份信息
     */
    /*public void delLoginUser(String token) {
        if (StringUtils.isNotEmpty(token)) {
            String userKey = getTokenKey(token);
            redisCache.deleteObject(userKey);
        }
    }*/

    /**
     * 创建令牌
     *
     * @param loginUser 用户信息
     * @return 令牌
     */
    public String createToken(LoginUser loginUser) {
        /*String token = IdUtils.fastUUID();
        loginUser.setToken(token);
        setUserAgent(loginUser);
        refreshToken(loginUser);

        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_KEY, token);*/
        return TokenUtils.generateTokenWithHMAC256(loginUser.getUsername(), "", 30 * 60L);
    }

    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
     *
     * @param loginUser
     * @return 令牌
     */
    /*public void verifyToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            refreshToken(loginUser);
        }
    }*/
    public Boolean verifyToken(String token) {
        return TokenUtils.checkTokenWithHMAC256(token);
        /*long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            refreshToken(loginUser);
        }*/
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     *//*
    public void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUser.getToken());
        redisCache.setCacheObject(userKey, loginUser, expireTime, TimeUnit.MINUTES);
    }*/

    /**
     * 设置用户代理信息
     *
     * @param loginUser 登录信息
     */
    /*public void setUserAgent(LoginUser loginUser) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        loginUser.setIpaddr(ip);
        loginUser.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
        loginUser.setBrowser(userAgent.getBrowser().getName());
        loginUser.setOs(userAgent.getOperatingSystem().getName());
    }*/

    /*private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }*/


    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private String getClaimFromToken(String token, String claim) {
        return TokenUtils.getClaimsFromToken(token).get(claim).asString();
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Constant.USER_NAME);
    }

    /**
     * 从令牌中获取uuid
     *
     * @param token 令牌
     * @return uuid
     */
    public String getUUIDFromToken(String token) {
        return getClaimFromToken(token, Constant.UUID);
    }

    /**
     * 获取请求token
     *
     * @param request 请求
     * @return token token
     */
    private String getToken(HttpServletRequest request) {
        return request.getHeader(Constant.AUTHORIZATION);
    }
}
