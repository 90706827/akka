package com.jangni.akka.module;

import lombok.Data;

/**
 * @ClassName User
 * @Description 用户对象
 * @Author Mr.Jangni
 * @Date 2019/4/17 14:40
 * @Version 1.0
 **/
@Data
public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
