package com.wmz.bookstore.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private int id;
    private int count;
    private Book book;
    private User user;
}
