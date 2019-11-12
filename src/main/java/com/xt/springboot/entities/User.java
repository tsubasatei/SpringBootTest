package com.xt.springboot.entities;

import javax.persistence.*;

/**
 * 使用 JPA 配置映射关系
 * @Entity ：这是一个实体类（和数据表映射的类）
 * @Table : 指定映射的数据库表名，省略默认就是 user
 */
@Entity
@Table(name="user")
public class User {

    /**
     * @Id ：主键
     * @GeneratedValue ： 自增主键
     *
     * @Column ：普通列
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
