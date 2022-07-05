package com.yupi.usercenter;

import com.yupi.usercenter.mapper.UserMapper;
import com.yupi.usercenter.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest

//@SpringBootTest("UserCenterApplication") 找到入口类名称
class UserCenterApplicationTests {


    @Resource
    private UserMapper userMapper;
    @Test
    void contextLoads() {

        System.out.println("----selectAll method test---");
        List<User> users = userMapper.selectList(null);
        Assert.assertEquals(3,users.size());
        users.forEach(System.out::println);
    }

}
