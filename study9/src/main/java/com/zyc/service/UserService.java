package com.zyc.service;

import com.github.pagehelper.PageInfo;
import com.zyc.entity.Users;

public interface UserService {

    int addUser(Users user);

    PageInfo<Users> findAllUser(int pageNum, int pageSize);
}

