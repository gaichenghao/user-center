package com.yupi.usercenter.service;

import com.yupi.usercenter.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户服务
* @author gaich
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2022-07-08 09:35:08
*/
public interface UserService extends IService<User> {



    /**
     * 用户注册
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 新用户id
     */
    long userRegister(String userAccount,String userPassword,String checkPassword,String planetCode);

    /**
     * 登录
     * @param userAccount 账户
     * @param userPassword 密码
     * @return 返回脱敏信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     * @param user
     * @return
     */
    User getSafeUser(User user);


    /**
     * 用户注销
     * @return
     */
    int userLogout(HttpServletRequest request);
}
