package com.shopmind.usercore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopmind.usercore.entity.UserPreference;
import com.shopmind.usercore.service.UsersPreferencesService;
import com.shopmind.usercore.mapper.UsersPreferencesMapper;
import org.springframework.stereotype.Service;

/**
* @author hcy18
* @description 针对表【sm_users_preferences(用户偏好设置表)】的数据库操作Service实现
* @createDate 2025-12-12 20:21:36
*/
@Service
public class UsersPreferencesServiceImpl extends ServiceImpl<UsersPreferencesMapper, UserPreference>
    implements UsersPreferencesService{

}




