package com.build.ecommerce.core.jwt.error;

import com.build.ecommerce.core.error.ExceptionCode;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public abstract class SecurityException extends AuthenticationException {

    private final ExceptionCode exceptionCode;

    protected SecurityException(String message, ExceptionCode exceptionCode) {
        super(message);
        this.exceptionCode = exceptionCode;
    }
}
