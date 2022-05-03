package com.wmz.bookstore.controller;

import com.wmz.bookstore.pojo.Book;
import com.wmz.bookstore.pojo.Cart;
import com.wmz.bookstore.pojo.User;
import com.wmz.bookstore.repository.BookRepository;
import com.wmz.bookstore.repository.UserRepository;
import com.wmz.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    /**
     * 跳转购物车前确认登录
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/beforeCart")
    public String beforeCart(Model model, HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("username");
        if(username != null){
            List<Cart> cartList = cartService.queryAllBooksInCartOfUser(username);
            model.addAttribute("cartList", cartList);
            System.out.printf(cartList.toString());
            return "cart";
        }else{
            model.addAttribute("user", new User());
            model.addAttribute("errorMsg", "请先登录");
            return "login";
        }
    }


    /**
     * index页面加入购物车
     * @param model
     * @param bookId
     * @param request
     * @return
     */
    @GetMapping("/addToCart")
    public String addToCart(Model model, int bookId, HttpServletRequest request){
        int count = 0;
        String username = (String) request.getSession().getAttribute("username");
        if(username == null || username.equals("")){
            model.addAttribute("user", new User());
            model.addAttribute("errorMsg", "请先登录");
            return "login";
        }
        int userId = userRepository.findUserByUsername(username).getId();
        Cart cart = cartService.queryCartByBookIdAndUserId(bookId, userId);
        if(cart == null){
            count = 0;
            cartService.addCart( 1, bookId, userId);
        }else{
            count = cart.getCount();
            cartService.addToCart(count + 1, bookId, userId);
        }
        List<Book> bookList = bookRepository.findAll();
        model.addAttribute("bookList", bookList);
        return "index";
    }

    @GetMapping("/updateCart")
    public String updateCart(Model model, int flag, int cartId, HttpServletRequest request){
        Cart cart = cartService.queryCartByCartId(cartId);
        int count = cart.getCount();
        if(count == 1 && flag == -1){
            cartService.deleteCartByCartId(cartId);
        }else{
            cartService.addToCart(count + flag, cart.getBook().getId(), cart.getUser().getId());
            System.out.println( cart.getBook().getId() + " " + cart.getUser().getId());
        }
        List<Cart> cartList = cartService.queryAllBooksInCartOfUser((String) request.getSession().getAttribute("username"));
        model.addAttribute("cartList", cartList);
        return "cart";
    }
}
