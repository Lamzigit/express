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
    public boolean hasManager(Manager manager) {
        return managerDao.getManagerByName(manager.getName()) != null;
    }

    @Override
    public boolean checkManager(Manager manager){
        String name = manager.getName();
        Manager one = managerDao.getManagerByName(name);

        if(one != null) {
            String onePassword = one.getPassword();
            System.out.print(manager);
            System.out.print(manager.getPassword());
            return manager.getPassword().equals(onePassword);
        }
        else
            return false;
    }

    @Override
    public boolean isSuperManager(Manager manager) {
        return manager.getType() == 1;
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
