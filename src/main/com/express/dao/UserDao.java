package main.com.express.dao;

import main.com.express.entity.User;

import java.util.List;

/**
 * Created by linzhijie on 2016/12/29.
 */
public interface UserDao {

    public User getUserByPhone(String phone);

    public List<User> getAllUser();

    public boolean addUser(User user);

    public boolean delUser(int id);

    public boolean updUser(User user);
}
