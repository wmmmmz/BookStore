package com.wmz.bookstore.controller;

import com.wmz.bookstore.pojo.Book;
import com.wmz.bookstore.pojo.Cart;
import com.wmz.bookstore.pojo.Order;
import com.wmz.bookstore.pojo.User;
import com.wmz.bookstore.repository.BookRepository;
import com.wmz.bookstore.service.CartService;
import com.wmz.bookstore.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    CartService cartService;

    @Autowired
    BookRepository bookRepository;

    /**
     * 跳转订单
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/beforeOrder")
    public String beforeOrder(Model model, HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("username");
        if(username == null) {
            model.addAttribute("errorMsg", "请先登陆");
            model.addAttribute("user", new User());
            return "login";
        }else{
            List<Order> orderList = orderService.queryAllOrdersOfUser(username);
            model.addAttribute("orderList", orderList);
            return "order";
        }
    }

    /**
     * 生成订单
     * @param model
     * @param cartId
     * @param request
     * @return
     */
    @GetMapping("/addOrder")
    public String addOrder(Model model, int cartId, HttpServletRequest request){
        Cart cart = cartService.queryCartByCartId(cartId);
        if(cart == null){
            model.addAttribute("errorMsg", "请勿重复提交订单");
            List<Order> orderList = orderService.queryAllOrdersOfUser((String) request.getSession().getAttribute("username"));
            model.addAttribute("orderList", orderList);
            return "order";
        }
        int bookId = cart.getBook().getId();
        int count = cart.getCount();
        Book book = bookRepository.findBookById(bookId);
        int userId = cart.getUser().getId();
        int stock = book.getStock();
        if(stock < count){
            model.addAttribute("errorMsg", "库存不足");
            List<Cart> cartList = cartService.queryAllBooksInCartOfUser((String) request.getSession().getAttribute("username"));
            model.addAttribute("cartList", cartList);
            return "cart";
        }else{
            book.setStock(stock - count);
            bookRepository.save(book);
            cartService.deleteCartByCartId(cartId);
            Date now = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = simpleDateFormat.format(now);
            orderService.addOrder(count, count * book.getPrice(), date, bookId, userId);
            List<Order> orderList = orderService.queryAllOrdersOfUser((String) request.getSession().getAttribute("username"));
            model.addAttribute("orderList", orderList);
            return "order";
        }

    }
}
