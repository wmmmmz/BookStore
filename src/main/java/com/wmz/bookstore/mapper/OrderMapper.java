package com.wmz.bookstore.mapper;

import com.wmz.bookstore.pojo.Cart;
import com.wmz.bookstore.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {
    List<Order> queryAllOrdersOfUser(String username);

//    void addToCart (int newCount, int bookId, int userId);
//
//    Cart queryCartByBookIdAndUserId(int bookId, int userId);
//
    void addOrder (int count, int sumPrice, String date, int bookId, int userId);
//
//    Cart queryCartByCartId(int cartId);
//
//    void deleteCartByCartId(int cartId);

}
