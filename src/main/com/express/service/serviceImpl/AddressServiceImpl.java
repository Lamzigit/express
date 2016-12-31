package main.com.express.service.serviceImpl;

import main.com.express.dao.AddressDao;
import main.com.express.entity.Address;
import main.com.express.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by linzhijie on 2016/12/31.
 */
@Service
public class AddressServiceImpl implements AddressService{

    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Autowired
    private AddressDao addressDao;

    @Override
    public Address getAddressByPhone(String phone) {
        return addressDao.getAddressByPhone(phone);
    }

    @Override
    public List<Address> getAllAddress() {
        return addressDao.getAllAddress();
    }

    @Override
    public boolean addAddress(Address address) {
        return addressDao.addAddress(address);
    }

    @Override
    public boolean updAddress(Address address) {
        return addressDao.updAddress(address);
    }

    @Override
    public boolean delAddress(int id) {
        return addressDao.delAddress(id);
    }
}
