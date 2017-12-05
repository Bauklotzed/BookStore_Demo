package dao;

import domain.Order;

import java.util.List;

/**
 * Created by Zed on 2017/11/28.
 */
public interface OrderDao {

    void add(Order order);

    Order find(String id);

    List<Order> getAll(boolean state);

    void update(Order order);

    List<Order> getAll(boolean state, String userid);

    List<Order> getAllOrder(String userid);

}
