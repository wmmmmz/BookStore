package com.wmz.bookstore.controller;

import com.wmz.bookstore.pojo.Book;
import com.wmz.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    /**
     * 增加库存
     * @param bookName
     * @return
     */
    @GetMapping("/addStock")
    public ModelAndView addStock(String bookName){
        ModelAndView modelAndView = new ModelAndView();
        Book book = bookRepository.findBookByBookName(bookName);
        book.setStock(book.getStock() + 1);
        bookRepository.save(book);
        List<Book> bookList = bookRepository.findAll();
        modelAndView.addObject("bookList", bookList);
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    /**
     * 修改价格
     * @param price
     * @param bookName
     * @return
     */
    @GetMapping("/updatePrice")
    public ModelAndView updatePrice(int price, String bookName){
        ModelAndView modelAndView = new ModelAndView();
        Book newBook = bookRepository.findBookByBookName(bookName);
        newBook.setPrice(price);
        bookRepository.save(newBook);
        List<Book> bookList = bookRepository.findAll();
        modelAndView.addObject("bookList", bookList);
        modelAndView.setViewName("admin");
        return modelAndView;
    }
}
