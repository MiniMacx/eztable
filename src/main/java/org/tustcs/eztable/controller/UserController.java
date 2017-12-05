package org.tustcs.eztable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tustcs.eztable.entity.Token;
import org.tustcs.eztable.enums.ResultEnums;
import org.tustcs.eztable.exception.UserException;
import org.tustcs.eztable.service.TokenService;
import org.tustcs.eztable.service.UserService;
import org.tustcs.eztable.utils.Res;
import org.tustcs.eztable.utils.ResUtil;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/user",produces = {"application/json;charset=UTF-8"})
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private TokenService tokenService;

    @RequestMapping(value = "/reg",method = RequestMethod.POST)
    @ResponseBody
    public Res reg(String userName,String userPwd){
        if(userService.reg(userName,userPwd)){
            return ResUtil.success();
        }else {
            throw new UserException(ResultEnums.ERROR);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Res login(String userName,String userPwd){
        int userId=userService.login(userName,userPwd);
        if(userId>0){
            Token tok=tokenService.createToken(userId);
            return ResUtil.success(tok);
        }else {
            throw new UserException(ResultEnums.USER_NOTFOUND);
        }
    }
}
