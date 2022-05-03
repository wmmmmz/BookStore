package com.wmz.bookstore.mapper;

import com.wmz.bookstore.pojo.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CartMapper {
    List<Cart> queryAllBooksInCartOfUser(String username);

    void addToCart (int newCount, int bookId, int userId);

    Cart queryCartByBookIdAndUserId(int bookId, int userId);

    void addCart (int count, int bookId, int userId);

    Cart queryCartByCartId(int cartId);

    void deleteCartByCartId(int cartId);

}
