package com.wmz.bookstore.service;

import com.wmz.bookstore.pojo.Cart;

import java.util.List;

public interface CartService {
    List<Cart> queryAllBooksInCartOfUser(String username);

    void addToCart (int newCount, int bookId, int userId);

    Cart queryCartByBookIdAndUserId(int bookId, int userId);

    void addCart (int count, int bookId, int userId);

    Cart queryCartByCartId(int cartId);

    void deleteCartByCartId(int cartId);

}
