package main.com.express.service.serviceImpl;

import main.com.express.dao.UserDao;
import main.com.express.entity.User;
import main.com.express.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by linzhijie on 2016/12/31.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByPhone(String phone) {
        return userDao.getUserByPhone(phone);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public boolean delUser(int id) {
        return userDao.delUser(id);
    }

    @Override
    public boolean updUser(User user) {
        return userDao.updUser(user);
    }

    @Override
    public boolean hasUser(String phone) {
        if(getUserByPhone(phone) == null)
            return false;
        else
            return true;
    }
}
