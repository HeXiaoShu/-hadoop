package com.service.impl;

import com.base.TkServiceImpl;
import com.model.User;
import org.springframework.stereotype.Service;
import com.service.UserService;

/**
 * UserServiceImpl
 * @author xiaoshu
 * @date 2021-05-01 21:05:04
 */
@Service("UserService")
public class UserServiceImpl extends TkServiceImpl<User> implements UserService {

}