package com.yupi.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yupi.usercenter.common.BaseResponse;
import com.yupi.usercenter.common.ErrorCode;
import com.yupi.usercenter.common.ResultUtils;
import com.yupi.usercenter.exception.BusinessException;
import com.yupi.usercenter.model.User;
import com.yupi.usercenter.model.request.UserLoginRequest;
import com.yupi.usercenter.model.request.UserRegisterRequest;
import com.yupi.usercenter.service.UserService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.yupi.usercenter.contant.UserConstant.ADMIN_ROLE;
import static com.yupi.usercenter.contant.UserConstant.USER_LOGIN_STATE;

/**
 * 用户接口
 * @author gaich
 * @date 2022/7/9
 * @apiNote
 */

@RestController
@RequestMapping("/user")
@Slf4j
//@CrossOrigin(origins = {"http://user.code-nav.cn"},allowCredentials = "true")
public class UserController {


    @Resource
    private UserService userService;

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        if (userRegisterRequest==null){
            //return ResultUtils.error(ErrorCode.PARAMS_ERROR);
            throw new  BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode=userRegisterRequest.getPlanetCode();
        if(StringUtils.isAnyBlank(userAccount,userPassword,checkPassword,planetCode)){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        return ResultUtils.success(result);
    }

    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){

        if (userLoginRequest==null){
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        log.info("已访问接口"+userAccount+"--"+userPassword);
        if(StringUtils.isAnyBlank(userAccount,userPassword)){
            return null;
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }


    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request){
        if (request==null){
            return null;
        }
        int result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request){
        Object attribute = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser=(User)attribute;
        if(currentUser==null){
           throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        // TODO: 2022/7/11 校验用户是否合法
        User user = userService.getById(currentUser.getId());
        User safeUser = userService.getSafeUser(user);
        return ResultUtils.success(safeUser);
    }




    @GetMapping("/search")
    public BaseResponse<List<User>>  searchUsers(String username,HttpServletRequest request){
        if(!isAdmin(request)){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<User> queryWrapper=new QueryWrapper();
        if(StringUtils.isNotBlank(username)){
            queryWrapper.like("username",username);
        }

        List<User> list = userService.list(queryWrapper);
        List<User> collect = list.stream().map(user -> userService.getSafeUser(user)).collect(Collectors.toList());
        return ResultUtils.success(collect);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody long id,HttpServletRequest request){
        if(!isAdmin(request)){
            return null;
        }
        if(id<=0){
            return null;
        }
        boolean remove = userService.removeById(id);
        return ResultUtils.success(remove);

    }

    /**
     * 是否为管理员
     * @param request
     * @return
     */
    private boolean isAdmin(HttpServletRequest request){
        //仅管理员可查询
        Object userObject = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user=(User) userObject;
        if(user==null || user.getUserRole()!=ADMIN_ROLE){
            return false;
        }
        return true;
    }


}
