package com.yupi.usercenter.model;

import lombok.Data;

/**
 * @author gaich
 * @date 2022/7/5
 * @apiNote
 */

@Data
public class User {

    public Long id;
    public String name;
    public Integer age;
    public String email;

}
