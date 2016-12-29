package main.com.express.dao;

import main.com.express.entity.Manager;

import java.util.List;

/**
 * Created by linzhijie on 2016/12/29.
 */
public interface ManagerDao {

    public Manager getManagerById(int id);

    public List<Manager> getAllManager();

    public boolean addManager(Manager man);

    public boolean delManager(int id);

    public boolean updManager(Manager man);
}
