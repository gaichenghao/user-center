package com.yupi.usercenter;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest

//@SpringBootTest("UserCenterApplication") 找到入口类名称
class UserCenterApplicationTests {

    @Test
    void testDigest(){
        String s = DigestUtils.md5DigestAsHex(("abcd" + "mypassword").getBytes());
        System.out.println(s);
    }


    @Test
    void contextLoads() {


    }

}
