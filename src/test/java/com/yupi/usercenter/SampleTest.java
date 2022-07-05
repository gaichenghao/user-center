package com.yupi.usercenter;

import com.yupi.usercenter.mapper.UserMapper;
import com.yupi.usercenter.model.User;
import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author gaich
 * @date 2022/7/5
 * @apiNote
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SampleTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect(){
        System.out.println("----selectAll method test---");
        List<User> users = userMapper.selectList(null);
        Assert.assertEquals(3,users.size());
        users.forEach(System.out::println);
    }

}
