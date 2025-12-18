package com.shopmind.usercore.service;

import com.shopmind.usercore.dto.request.UpdateUserRequest;
import com.shopmind.usercore.dto.response.UserResponseDTO;
import com.shopmind.usercore.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author hcy18
* @description 针对表【sm_users(用户基本信息表)】的数据库操作Service
* @createDate 2025-12-12 20:21:36
*/
public interface UsersService extends IService<User> {

    /**
     * 根据手机号查用户
     * @param phoneNumber 手机号
     * @return 用户信息
     */
    UserResponseDTO getUserByPhoneNumber(String phoneNumber);

    /**
     * 根据手机号创建新用户
     * @param phoneNumber 手机号
     * @return 用户信息
     */
    UserResponseDTO createUserByPhoneNumber(String phoneNumber);

    /**
     * 根据用户 id 获取用户
     * @param userId 用户 id
     * @return 用户信息
     */
    UserResponseDTO getByUserId(String userId);

    /**
     * 更新用户
     */
    UserResponseDTO updateUser(Long userId, UpdateUserRequest request);
}
