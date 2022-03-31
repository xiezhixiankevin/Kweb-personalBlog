package com.xiezhixian.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <Description> NotFoundException
 *
 * @author 26802
 * @version 1.0
 * @ClassName NotFoundException
 * @taskId
 * @see com.xiezhixian.blog.exception
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

    public NotFoundException() {
        new StringBuilder().reverse();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
