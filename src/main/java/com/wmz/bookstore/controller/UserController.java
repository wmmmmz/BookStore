package com.wmz.bookstore.controller;

import com.wmz.bookstore.pojo.Book;
import com.wmz.bookstore.pojo.User;
import com.wmz.bookstore.repository.BookRepository;
import com.wmz.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    /**
     * 传递object至register表单
     * @param model
     * @return
     */
    @GetMapping("/toRegister")
    public String toRegister(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "user-register";
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute User user){
        ModelAndView modelAndView = new ModelAndView();
        User checkUser = userRepository.findUserByUsername(user.getUsername());
        if(checkUser == null){
            userRepository.save(user);
            List<Book> bookList = bookRepository.findAll();
            modelAndView.addObject("bookList", bookList);
            modelAndView.setViewName("index");
        }else {
            modelAndView.addObject("errorMsg","该用户已注册");
            modelAndView.setViewName("user-register");
        }
        return modelAndView;
    }

    /**
     * 传递object至login表单
     * @param model
     * @return
     */
    @GetMapping("toLogin")
    public String toLogin(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    /**
     * 登录
     * @param user
     * @param request
     * @return
     */
    @PostMapping("login")
    public ModelAndView login(@ModelAttribute User user, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        User checkUser = userRepository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        if(checkUser != null){
            request.getSession().setAttribute("username", user.getUsername());
            List<Book> bookList = bookRepository.findAll();
            modelAndView.addObject("bookList", bookList);
            modelAndView.setViewName("index");
        }else{
            modelAndView.addObject("errorMsg", "用户名密码错误");
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }

    /**
     * 注销
     * @param request
     * @return
     */
    @GetMapping("/logOut")
    public ModelAndView logOut(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        request.getSession().setAttribute("username", null);
        List<Book> bookList = bookRepository.findAll();
        modelAndView.addObject("bookList", bookList);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
