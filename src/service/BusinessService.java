package service;

import domain.*;

import java.util.List;

/**
 * Created by Zed on 2017/11/23.
 */
public interface BusinessService {

    //添加分类
    void addCategory(Category category);

    //查找分类
    Category findCategory(String id);

    //得到所有分类
    List<Category> getAllCategory();

    Page getBookPageData(String pagenum);

    Page getBookPageData(String pagenum, String category_id);

    User userLogin(String username, String password);

    Book findBook(String id);

    void buyBook(Cart cart, Book book);

    void createOrder(Cart cart, User user);

    List<Order> clientListOrder(String userid);

    //列出订单明细
    Order findOrder(String orderid);

    List<Order> listOrder(String state);

    void registerUser(User user);

    void addBook(Book book);

    void confirmOrder(String orderid);

}
