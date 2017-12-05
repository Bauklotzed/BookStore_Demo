package dao.impl;

import dao.UserDao;
import domain.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import utils.JdbcUtils;

import java.sql.SQLException;

/**
 * Created by Zed on 2017/11/27.
 */
public class UserDaoImpl implements UserDao {

    @Override
    public void add(User user) {
        try{
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "insert into user(id,username,password,phone,cellphone,address,email) values(?,?,?,?,?,?,?)";
            Object params[] = {user.getId(), user.getUsername(), user.getPassword(), user.getPhone(), user.getCellphone(), user.getAddress(), user.getEmail()};
            runner.update(sql, params);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public User find(String id) {
        try{
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from user where id=?";
            return (User) runner.query(sql, new BeanHandler(User.class), id);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public User find(String username, String password) {
        try{
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "select * from user where username=? and password=?";
            Object[] params = {username, password};
            return (User)runner.query(sql, new BeanHandler(User.class), params);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
        UserDao userDao = new UserDaoImpl();
        User user = userDao.find("1");
        System.out.println(user.getUsername()+","+user.getPassword());
    }

}
