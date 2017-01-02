package main.com.express.entity;

import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.persistence.*;

/**
 * Created by linzhijie on 2016/12/29.
 */
@Entity
@Table(name = "manager")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name" , length = 45)
    private String name;

    @Column(name = "password" ,length = 45)
    private String password;

    @Column(name = "type")
    private int type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
