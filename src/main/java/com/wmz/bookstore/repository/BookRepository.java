package com.wmz.bookstore.repository;

import com.wmz.bookstore.pojo.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAll();

    Book findBookByBookName(String bookName);

    Book save(Book book);

    Book findBookById(int id);
}
