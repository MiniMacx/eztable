package org.tustcs.eztable.service;

import org.tustcs.eztable.entity.User;

public interface UserService {
    boolean reg(String userName, String userPwd);

    int login(String userName,String userPwd);

}
