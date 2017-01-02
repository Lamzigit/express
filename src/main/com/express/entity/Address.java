package main.com.express.entity;

import javax.persistence.*;

/**
 * Created by linzhijie on 2016/12/29.
 */
@Entity
@Table(name = "address")
public class Address {

    private static final long serialVersionUID = 1304053983694294061L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sAddress" , length = 45,nullable = false)
    private String saddress;

    @Column(name = "rAddress" , length = 45,nullable = false)
    private String raddress;

    @Column(name = "phone" ,length = 45,nullable = false)
    private String phone;

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
}
