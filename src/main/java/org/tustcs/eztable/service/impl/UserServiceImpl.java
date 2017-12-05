package org.tustcs.eztable.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tustcs.eztable.dao.UserMapper;
import org.tustcs.eztable.entity.User;
import org.tustcs.eztable.enums.ResultEnums;
import org.tustcs.eztable.exception.UserException;
import org.tustcs.eztable.service.UserService;
import org.tustcs.eztable.utils.MD5Utils;

import javax.annotation.Resource;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public boolean reg(String userName,String userPwd) {
        if(userName.length()<4){
            throw new UserException(ResultEnums.USER_TOOSHORT);
        }
        userPwd=(MD5Utils.getMD5(MD5Utils.getMD5(MD5Utils.getMD5(userPwd))));

        User u=new User();
        if(userMapper.selectByUserName(userName)!=null){
            throw new UserException(ResultEnums.USER_EXISTED);
        }

        u.setUserPwd(userPwd);
        u.setUserName(userName);

        return userMapper.insertSelective(u)>0;
    }

    @Override
    public int login(String userName, String userPwd) {
        String password=MD5Utils.getMD5(MD5Utils.getMD5(MD5Utils.getMD5(userPwd)));
        User u=userMapper.findOne(userName,password);
        if (u == null){
            throw new UserException(ResultEnums.USER_NOTFOUND);
        }
        return u.getUserId();
    }
}
