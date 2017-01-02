package main.com.express.entity;

import javax.persistence.*;

/**
 * Created by linzhijie on 2016/12/29.
 */
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "phone" ,length = 45,nullable = false)
    private String phone;

    @Column(name = "sAddress" , length = 45,nullable = false)
    private String saddress;

    @Column(name = "rAddress" , length = 45,nullable = false)
    private String raddress;

    @Column(name = "transNum" , length = 45,nullable = false)
    private String transnum;

    @Column(name = "weight" , nullable = false)
    private double weight;

    @Column(name = "price" , nullable = false)
    private double price;

    @Column(name = "state" , length = 45 , nullable = false)
    private String state;

    @Column(name = "uId" , nullable = false)
    private Integer uid;

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
