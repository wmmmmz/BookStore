package com.wmz.bookstore.service;

import com.wmz.bookstore.mapper.CartMapper;
import com.wmz.bookstore.pojo.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    CartMapper cartMapper;

    @Override
    public List<Cart> queryAllBooksInCartOfUser(String username) {
        return cartMapper.queryAllBooksInCartOfUser(username);
    }

    @Override
    public void addToCart(int newCount, int bookId, int userId) {
        cartMapper.addToCart(newCount, bookId, userId);
    }

    @Override
    public Cart queryCartByBookIdAndUserId(int bookId, int userId) {
        return cartMapper.queryCartByBookIdAndUserId(bookId, userId);
    }

    @Override
    public void addCart(int count, int bookId, int userId) {
        cartMapper.addCart(count, bookId, userId);
    }

    @Override
    public Cart queryCartByCartId(int cartId) {
        return cartMapper.queryCartByCartId(cartId);
    }

    @Override
    public void deleteCartByCartId(int cartId) {
        cartMapper.deleteCartByCartId(cartId);
    }
}
