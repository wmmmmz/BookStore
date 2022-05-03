package com.wmz.bookstore.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    int id;
    int count;
    int sumPrice;
    String date;
    Book book;
    User user;
}
