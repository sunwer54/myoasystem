package com.util;

/**
 * 自定义删除异常
 */
public class DeleteException extends Exception {
    public DeleteException() {
    }
    public DeleteException(String e) {
        super(e);
    }
}
