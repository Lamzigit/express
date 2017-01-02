package main.com.express.service;

import main.com.express.entity.User;

import java.util.List;

/**
 * Created by linzhijie on 2016/12/31.
 */
public interface UserService {

    public User getUserByPhone(String phone);

    public List<User> getAllUser();

    public boolean addUser(User user);

    public boolean delUser(int id);

    public boolean updUser(User user);

    public boolean hasUser(String phone);
}
