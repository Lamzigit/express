package main.com.express.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by linzhijie on 2016/12/29.
 */
public class Order {
    private static final long serialVersionUID = 2702918674046865911L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "phone" ,length = 45)
    private String phone;

    @Column(name = "sAddress" , length = 45)
    private String saddress;

    @Column(name = "rAddress" , length = 45)
    private String raddress;

    @Column(name = "transNum" , length = 45)
    private String transnum;

    @Column(name = "weight")
    private double weight;

    @Column(name = "price")
    private double price;

    @Column(name = "state" , length = 45)
    private String state;

    @Column(name = "uId")
    private int uid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRaddress() {
        return raddress;
    }

    public void setRaddress(String raddress) {
        this.raddress = raddress;
    }

    public String getSaddress() {
        return saddress;
    }

    public void setSaddress(String saddress) {
        this.saddress = saddress;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTransnum() {
        return transnum;
    }

    public void setTransnum(String transnum) {
        this.transnum = transnum;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
