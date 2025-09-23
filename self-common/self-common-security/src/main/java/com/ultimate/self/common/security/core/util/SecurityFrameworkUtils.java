package com.ultimate.self.common.security.core.util;

import com.ultimate.self.common.security.service.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 描述：安全服务工具类
 * 作者：kanwj
 * 日期：2025/9/12 10:07
 */
public class SecurityFrameworkUtils {

    public static Long getLoginUserId() {
        LoginUser loginUser = getLoginUser();
        return loginUser != null ? loginUser.getId() : null;
    }

    public static LoginUser getLoginUser() {
        Authentication authentication = getAuthentication();
        if(authentication == null){
            return null;
        }
        return authentication.getPrincipal() instanceof LoginUser ? (LoginUser)authentication.getPrincipal() : null;

    }

    public static Authentication getAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return null;
        }
        return context.getAuthentication();
    }
}
