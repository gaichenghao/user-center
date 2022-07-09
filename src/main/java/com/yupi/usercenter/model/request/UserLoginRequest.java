package com.yupi.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author gaich
 * @date 2022/7/9
 * @apiNote 用户注册请求体
 */
@Data
public class UserLoginRequest implements Serializable {



    private static final long serialVersionUID=3191241716373120793L;
    private String userAccount;
    private String userPassword;
}
