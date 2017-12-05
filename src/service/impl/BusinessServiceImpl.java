package service.impl;

import dao.BookDao;
import dao.CategoryDao;
import dao.OrderDao;
import dao.UserDao;
import domain.*;
import service.BusinessService;
import utils.DaoFactory;
import utils.WebUtils;
import web.client.OrderServlet;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Zed on 2017/11/23.
 */
public class BusinessServiceImpl implements BusinessService {

    private CategoryDao categoryDao = DaoFactory.getInstance().createDao("dao.impl.CategoryDaoImpl", CategoryDao.class);
    private BookDao bookDao = DaoFactory.getInstance().createDao("dao.impl.BookDaoImpl", BookDao.class);
    private UserDao userDao = DaoFactory.getInstance().createDao("dao.impl.UserDaoImpl", UserDao.class);
    private OrderDao orderDao = DaoFactory.getInstance().createDao("dao.impl.OrderDaoImpl", OrderDao.class);

    @Override
    public void addCategory(Category category) {
        categoryDao.add(category);
    }

    @Override
    public Category findCategory(String id) {
        return categoryDao.find(id);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryDao.getAll();
    }

    public Page getBookPageData(String pagenum){
        int totalrecord = bookDao.getTotalRecord();
        Page page = null;
        if(pagenum == null){
            page = new Page(1, totalrecord);
        }else{
            page = new Page(Integer.parseInt(pagenum), totalrecord);
        }
        List<Book> list = bookDao.getPageData(page.getStartindex(), totalrecord);
        page.setList(list);
        return page;
    }

    @Override
    public Page getBookPageData(String pagenum, String category_id) {
        int totalrecord = bookDao.getTotalRecord(category_id);
        Page page = null;
        if(pagenum == null){
            page = new Page(1, totalrecord);
        }else{
            page = new Page(Integer.parseInt(pagenum), totalrecord);
        }
        List<Book> list = bookDao.getPageData(page.getStartindex(), page.getPagesize(), category_id);
        page.setList(list);
        return page;
    }

    @Override
    public User userLogin(String username, String password) {
        return userDao.find(username, password);
    }

    @Override
    //获得书
    public Book findBook(String id) {
        return bookDao.find(id);
    }

    @Override
    public void buyBook(Cart cart, Book book) {
        cart.add(book);
    }

    @Override
    //生成订单
    public void createOrder(Cart cart, User user) {
        if(cart == null){
            throw new RuntimeException("对不起，您还没有购买任何商品");
        }
        Order order = new Order();
        order.setId(WebUtils.makeID());
        order.setOrdertime(new Date());
        order.setPrice(cart.getPrice());
        order.setState(false);
        order.setUser(user);
        for(Map.Entry<String, CartItem> me : cart.getMap().entrySet()){
            CartItem cartItem = me.getValue();
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setId(WebUtils.makeID());
            orderItem.setQuantity(cartItem.getQuantity());
            order.getOrderItems().add(orderItem);
        }
        orderDao.add(order);
    }

    @Override
    public List<Order> clientListOrder(String userid) {
        return orderDao.getAllOrder(userid);
    }

    @Override
    public Order findOrder(String orderid) {
        return orderDao.find(orderid);
    }

    @Override
    public void registerUser(User user) {
        userDao.add(user);
    }

    @Override
    public void addBook(Book book) {
        bookDao.add(book);
    }

    @Override
    public List<Order> listOrder(String state) {
        return orderDao.getAll(Boolean.parseBoolean(state));
    }

    @Override
    public void confirmOrder(String orderid) {
        Order order = orderDao.find(orderid);
        order.setState(true);
        orderDao.update(order);
    }
}
