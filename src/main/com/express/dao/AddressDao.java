package main.com.express.dao;

import main.com.express.entity.Address;

import java.util.List;

/**
 * Created by linzhijie on 2016/12/29.
 */
public interface AddressDao {
    public Address getAddressByPhone(String phone);

    public List<Address> getAllAddress();

    public boolean addAddress(Address address);

    public boolean updAddress(Address address);

    public boolean delAddress(int id);
}
