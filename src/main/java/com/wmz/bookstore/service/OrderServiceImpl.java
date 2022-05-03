package com.wmz.bookstore.service;

import com.wmz.bookstore.mapper.OrderMapper;
import com.wmz.bookstore.pojo.Cart;
import com.wmz.bookstore.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderMapper orderMapper;
    @Override
    public List<Order> queryAllOrdersOfUser(String username) {
        return orderMapper.queryAllOrdersOfUser(username);
    }

    @Override
    public void addOrder(int count, int sumPrice, String date, int bookId, int userId) {
        orderMapper.addOrder(count, sumPrice, date, bookId, userId);
    }
}
