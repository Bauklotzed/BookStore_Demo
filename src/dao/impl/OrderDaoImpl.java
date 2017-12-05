package dao.impl;

import dao.OrderDao;
import domain.Book;
import domain.Order;
import domain.OrderItem;
import domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JdbcUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Created by Zed on 2017/11/28.
 */
public class OrderDaoImpl implements OrderDao {

    @Override
    public void add(Order order) {
        try{
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            //1.把order的基本信息保存到order表
            String sql = "insert into orders(id,ordertime,price,state,user_id) values(?,?,?,?,?)";
            Object[] params = {order.getId(),order.getOrdertime(),order.getPrice(),order.isState(),order.getUser().getId()};
            runner.update(sql, params);
            //2.把order中的订单保存到orderitem表中
            Set<OrderItem> set = order.getOrderItems();
            for(OrderItem item : set){
                sql = "insert into orderitem(id,quantity,price,order_id,book_id) values(?,?,?,?,?)";
                params = new Object[]{item.getId(),item.getQuantity(),item.getPrice(),order.getId(),item.getBook().getId()};
                runner.update(sql, params);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order find(String id) {
        try{
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            //1.找出订单的基本信息
            String sql = "select * from orders where id=?";
            Order order = (Order) runner.query(sql, new BeanHandler(Order.class), id);
            //2.找出订单中所有的订单项
            sql = "select * from orderitem where order_id=?";
            List<OrderItem> list = (List<OrderItem>) runner.query(sql, new BeanListHandler(OrderItem.class), id);
            for(OrderItem item : list){
                sql = "select book.* from orderitem,book where orderitem.id=? and orderitem.book_id=book.id";
                Book book = (Book)runner.query(sql, new BeanHandler(Book.class), item.getId());
                item.setBook(book);
            }
            //把找出的订单项放进order
            order.getOrderItems().addAll(list);
            //3.找出订单属于哪个用户
            sql = "select * from orders,user where orders.id=? and orders.user_id=user.id";
            User user = (User)runner.query(sql, new BeanHandler(User.class), order.getId());
            order.setUser(user);
            return order;
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getAll(boolean state) {
        try{
            int x;
            if(state){
                x = 1;
            }else{
                x = 0;
            }
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from orders where state = ?";
            List<Order> list = (List<Order>) runner.query(sql, new BeanListHandler(Order.class), x);
            for(Order order : list){
                sql = "select user.* from orders,user where orders.id=? and orders.user_id=user.id";
                User user = (User)runner.query(sql, new BeanHandler(User.class), order.getId());
                order.setUser(user);
            }
            return list;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getAll(boolean state, String userid) {
        return null;
    }

    @Override
    public List<Order> getAllOrder(String userid) {
        try{
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from orders where user_id=?";
            List<Order> list = (List<Order>)runner.query(sql, new BeanListHandler(Order.class), userid);
            for(Order order : list){
                sql = "select * from user where id=?";
                User user = (User)runner.query(sql, new BeanHandler(User.class), userid);
                order.setUser(user);
            }
            return list;
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Order order) {
        try{
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "update orders set state=? where id=?";
            Object params[] = {order.isState(), order.getId()};
            runner.update(sql, params);
        } catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
        OrderDao orderDao = new OrderDaoImpl();
        Order order = orderDao.find("2d689146-c97d-4d53-a1ec-854c9156f586");
        System.out.println(order.getOrderItems().toString());
    }

}
