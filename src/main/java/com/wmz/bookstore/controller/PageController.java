package com.wmz.bookstore.controller;

import com.wmz.bookstore.pojo.Book;
import com.wmz.bookstore.pojo.User;
import com.wmz.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    BookRepository bookRepository;

    /**
     * 页面跳转
     * @param target
     * @return
     */
    @GetMapping("/forward/{target}")
    public String forward(@PathVariable("target") String target){
        return target;
    }

    /**
     * 首页展示书籍列表
     * @param model
     * @return
     */
    @GetMapping("/index")
    public String showBooks(Model model){
        List<Book> bookList = bookRepository.findAll();
        model.addAttribute("bookList", bookList);
        return "index";
    }

    /**
     * 管理界面展示书籍列表
     * @param model
     * @return
     */
    @GetMapping("/adminIndex")
    public String showAdminBooks(Model model, HttpServletRequest request){
        if(request.getSession().getAttribute("username") == null || !request.getSession().getAttribute("username").equals("wmz")){
            model.addAttribute("user", new User());
            model.addAttribute("errorMsg", "请登录管理员账号");
            return "login";
        }
        List<Book> bookList = bookRepository.findAll();
        model.addAttribute("bookList", bookList);
        return "admin";
    }
}
