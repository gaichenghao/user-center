package com.yupi.usercenter.service;
import java.util.Date;

import com.yupi.usercenter.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * 用户服务测试
 * @author gaich
 * @date 2022/7/8
 * @apiNote
 */
@SpringBootTest
public class UserServiceTest {


    //@Resource
    //private UserService userService;
    //
    //
    //@Test
    //public void testAddUser(){
    //    User user=new User();
    //    user.setUsername("testgaich");
    //    user.setUserAccount("123");
    //    user.setAvatarUrl("123");
    //    user.setGender(0);
    //    user.setUserPassword("xxx");
    //    user.setPhone("123");
    //    user.setEmail("123");
    //    user.setUserStatus(0);
    //    user.setCreateTime(new Date());
    //    user.setUpdateTime(new Date());
    //    user.setIsDelete(0);
    //    boolean save = userService.save(user);
    //    System.out.println(user.getId());
    //    Assertions.assertTrue(save);
    //
    //}
    //
    //
    //@Test
    //void userRegister() {
    //    //String useraccount="yupi";
    //    //String userpassword="";
    //    //String chepa="12345678";
    //    //long l = userService.userRegister(useraccount, userpassword, chepa);
    //    //Assertions.assertEquals(-1,l);
    //    //useraccount="yu";
    //    //userService.userRegister(useraccount, userpassword, chepa);
    //    //Assertions.assertEquals(-1,l);
    //    //useraccount="yupi";
    //    //userpassword="12345678";
    //    //userService.userRegister(useraccount, userpassword, chepa);
    //    //Assertions.assertEquals(-1,l);
    //    //useraccount=" yupi ";
    //    //userService.userRegister(useraccount, userpassword, chepa);
    //    //Assertions.assertEquals(-1,l);
    //    //chepa="123456789";
    //    //userService.userRegister(useraccount, userpassword, chepa);
    //    //Assertions.assertEquals(-1,l);
    //    //useraccount=" testgaich";
    //    //userService.userRegister(useraccount, userpassword, chepa);
    //    //Assertions.assertEquals(-1,l);
    //    //useraccount="yupi";
    //    //chepa="12345678";
    //    //userService.userRegister(useraccount, userpassword, chepa);
    //    //Assertions.assertTrue(l>0);
    //
    //}
    //
    //@Test
    //void doLogin() {
    //}
}