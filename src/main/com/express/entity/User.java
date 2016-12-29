package main.com.express.entity;

import javax.persistence.*;

/**
 * Created by linzhijie on 2016/12/29.
 */
@Entity
public class User {
    private static final long serialVersionUID = 2702918674046865911L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Username" , length = 50)
    private String username;

    @Column(name = "Phone" , length = 50)
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
