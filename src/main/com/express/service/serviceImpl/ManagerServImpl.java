package main.com.express.service.serviceImpl;

import main.com.express.dao.ManagerDao;
import main.com.express.entity.Manager;
import main.com.express.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by linzhijie on 2016/12/31.
 */
@Service
public class ManagerServImpl implements ManagerService{

    @Autowired
    private ManagerDao managerDao;

    @Override
    public Manager getManagerById(int id) {
        return managerDao.getManagerById(id);
    }

    @Override
    public List<Manager> getAllManager() {
        return managerDao.getAllManager();
    }

    @Override
    public boolean addManager(Manager man) {
        return managerDao.addManager(man);
    }

    @Override
    public boolean delManager(int id) {
        return managerDao.delManager(id);
    }

    @Override
    public boolean updManager(Manager man) {
        return managerDao.updManager(man);
    }
}
