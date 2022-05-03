package com.wmz.bookstore.service;

import com.wmz.bookstore.pojo.Cart;
import com.wmz.bookstore.pojo.Order;

import java.util.List;

public interface OrderService {
    List<Order> queryAllOrdersOfUser(String username);

    void addOrder (int count, int sumPrice, String date, int bookId, int userId);

}
