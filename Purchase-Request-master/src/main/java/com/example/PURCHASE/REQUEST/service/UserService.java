package com.example.PURCHASE.REQUEST.service;


import com.example.PURCHASE.REQUEST.dto.UserDto;
import com.example.PURCHASE.REQUEST.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String User);
    List<UserDto> findAllUsers();
}
