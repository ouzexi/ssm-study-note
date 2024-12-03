package com.atguigu.spring.service.impl;

import com.atguigu.spring.service.BookService;
import com.atguigu.spring.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    private BookService bookService;

    @Override
    // @Transactional // 用的是结账的事务，结账是批量购买，只要有一本不够钱，那么所有书都买不成功，回滚的是结账的操作
    public void checkout(Integer userId, Integer[] bookIds) {
        // 快捷键 iter 遍历可迭代对象
        for (Integer bookId : bookIds) {
            bookService.buyBook(userId, bookId);
        }
    }
}
