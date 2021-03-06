package main.com.express.service;

import main.com.express.entity.Manager;

import java.util.List;

/**
 * Created by linzhijie on 2016/12/31.
 */
public interface ManagerService {

    public boolean hasManager(Manager manager);

    public boolean checkManager(Manager manager);

    public boolean isSuperManager(Manager manager);

    public List<Manager> getAllManager();

    public boolean addManager(Manager man);

    public boolean delManager(int id);

    public boolean updManager(Manager man);
}
