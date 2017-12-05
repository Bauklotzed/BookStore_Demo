package dao;

import domain.User;

/**
 * Created by Zed on 2017/11/27.
 */
public interface UserDao {

    void add(User user);

    User find(String id);

    User find(String username, String password);

}
