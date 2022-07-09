package com.yupi.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.usercenter.service.UserService;
import com.yupi.usercenter.model.User;
import com.yupi.usercenter.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.yupi.usercenter.contant.UserConstant.USER_LOGIN_STATE;

/**
 * 用户服务实现类
* @author gaich
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2022-07-08 09:35:08
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {


    @Resource
    private UserMapper userMapper;

    /**
     * 盐值
     */
    public static final String SALT="yupi";



    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        //1 校验
        if(StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)){
            return -1;
        }
        if(userAccount.length()<4){
            return -1;
        }
        if(userPassword.length()<8||checkPassword.length()<8){
            return -1;
        }
        //账户不能包含特殊字符
        String validPattern= "[\\\\u00A0\\\\s\\\"`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if(matcher.find()){
            return -1;
        }
        //密码和校验密码相同
        if(!userPassword.equals(checkPassword)){
            return -1;
        }
        //账户不能重复
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if (count>0){
            return -1;
        }
        //2、加密

        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        //3、加入数据库
        User user=new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        boolean save = this.save(user);
        if (!save){
            return -1;
        }
        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        //1 校验
        if(StringUtils.isAnyBlank(userAccount,userPassword)){
            return null;
        }
        if(userAccount.length()<4){
            return null;
        }
        if(userPassword.length()<8){
            return null;
        }
        //账户不能包含特殊字符
        String validPattern= "[\\\\u00A0\\\\s\\\"`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if(!matcher.find()){
            return null;
        }

        //2、加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        //账户是否存在
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        queryWrapper.eq("userPassword",encryptPassword);
        User user = userMapper.selectOne(queryWrapper);
        //用户不存在
        if (user ==null){
            log.info("user login filed, userAccount match userPassword");
            return null;
        }
        //3、用户脱敏
        User safeUser = getSafeUser(user);
        //4 、记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE,user);
        return safeUser;

    }

    /**
     * 用户脱敏
     * @param user
     * @return
     */
    @Override
    public User getSafeUser(User user){
        User safeUser=new User();
        safeUser.setId(user.getId());
        safeUser.setUsername(user.getUsername());
        safeUser.setUserAccount(user.getUserAccount());
        safeUser.setAvatarUrl(user.getAvatarUrl());
        safeUser.setGender(user.getGender());
        safeUser.setPhone(user.getPhone());
        safeUser.setEmail(user.getEmail());
        safeUser.setUserStatus(user.getUserStatus());
        safeUser.setCreateTime(user.getCreateTime());
        safeUser.setUserRole(user.getUserRole());
        return safeUser;

    }
}




