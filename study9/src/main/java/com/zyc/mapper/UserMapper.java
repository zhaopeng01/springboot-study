package com.zyc.mapper;

import com.zyc.entity.Users;

import java.util.List;

public interface UserMapper {


    int insert(Users users);


    List<Users> selectUsers();

}
