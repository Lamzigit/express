package main.com.express.service;

import main.com.express.entity.Address;

import java.util.List;

/**
 * Created by linzhijie on 2016/12/31.
 */
public interface AddressService {
    public Address getAddressByPhone(String phone);

    public List<Address> getAllAddress();

    public boolean addAddress(Address address);

    public boolean updAddress(Address address);

    public boolean delAddress(int id);
}
