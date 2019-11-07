package com.xt.springboot.exception;

/**
 * 自定义异常
 *
 * Alt+insert : 切记要把光标放在类中，否则只会出现 Generator->Copyright，一定放在类中。
 */
public class UserNotExistException extends RuntimeException{

    public UserNotExistException() {
        super("用户不存在");
    }
}
