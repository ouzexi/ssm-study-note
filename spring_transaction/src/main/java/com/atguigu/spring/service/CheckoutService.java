package com.atguigu.spring.service;

public interface CheckoutService {
    // 结账
    void checkout(Integer userId, Integer[] bookIds);
}
