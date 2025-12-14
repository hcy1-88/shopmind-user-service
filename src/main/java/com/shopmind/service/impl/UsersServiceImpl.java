package com.shopmind.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopmind.entity.Users;
import com.shopmind.service.UsersService;
import com.shopmind.mapper.UsersMapper;
import org.springframework.stereotype.Service;

/**
* @author hcy18
* @description 针对表【sm_users(用户基本信息表)】的数据库操作Service实现
* @createDate 2025-12-12 20:21:36
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService{

}




