package com.ultimate.self.common.security.core.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author kanwj
 * @ClassName LoginErrorException
 * @description: TODO
 * @date 2023年08月04日
 * @version: 1.0
 */
public class LoginErrorException extends AuthenticationException {
    public LoginErrorException(String msg) {
        super(msg);
    }

    public LoginErrorException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
