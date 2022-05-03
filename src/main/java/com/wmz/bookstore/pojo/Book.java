package com.wmz.bookstore.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    private int id;
    private String bookName;
    private String author;
    private int price;
    private int stock;
}
