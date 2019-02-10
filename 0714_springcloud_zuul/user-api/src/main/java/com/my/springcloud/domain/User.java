package com.my.springcloud.domain;

import java.io.Serializable;

/**
 * 用户模块
 *
 * @author Instuition
 * @date 2019/2/1 14:59
 */
public class User implements Serializable {

    private static final long serialVersionUID = 3572554268375167202L;
    private Long id;

    private String name;

    public User() {
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
