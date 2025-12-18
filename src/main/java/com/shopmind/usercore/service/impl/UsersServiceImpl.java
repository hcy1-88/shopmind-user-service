package com.shopmind.usercore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopmind.usercore.constant.GenderConst;
import com.shopmind.usercore.dto.request.UpdateUserRequest;
import com.shopmind.usercore.dto.response.UserResponseDTO;
import com.shopmind.usercore.entity.User;
import com.shopmind.usercore.exception.UserServiceException;
import com.shopmind.usercore.properties.UserDefaultProperties;
import com.shopmind.usercore.service.UsersService;
import com.shopmind.usercore.mapper.UsersMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author hcy18
* @description 针对表【sm_users(用户基本信息表)】的数据库操作Service实现
* @createDate 2025-12-12 20:21:36
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, User> implements UsersService{
    @Resource
    private UserDefaultProperties userDefaultProperties;

    /**
     * 根据手机号查询用户（返回时 gender 转中文）
     */
    @Override
    public UserResponseDTO getUserByPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return null;
        }
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.eq(User::getPhoneNumber, phoneNumber)
                .isNull(User::getDeletedAt); // 软删除过滤

        User user = this.getOne(query);
        return convertToDTO(user);
    }

    /**
     * 根据手机号创建用户
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserResponseDTO createUserByPhoneNumber(String phoneNumber) {
        // 先查是否存在
        if (this.getUserByPhoneNumber(phoneNumber) != null) {
            throw new UserServiceException("USER0001");
        }

        User user = new User();
        user.setPhoneNumber(phoneNumber);
        user.setNickname(userDefaultProperties.getNickname());
        user.setAvatar(userDefaultProperties.getAvatar());
        user.setGender(userDefaultProperties.getGender());
        // createdAt / updatedAt 由自动填充处理

        this.save(user);
        return convertToDTO(user);
    }

    @Override
    public UserResponseDTO getByUserId(String userId) {
        return convertToDTO(this.getById(userId));
    }

    /**
     * 修改用户（部分更新，null 不更新）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserResponseDTO updateUser(Long userId, UpdateUserRequest request) {
        if (userId == null) {
            throw new UserServiceException("USER0002");
        }

        // 检查用户是否存在
        User existing = this.getById(userId);
        if (existing == null || existing.getDeletedAt() != null) {
            throw new UserServiceException("USER0003");
        }

        // 构建更新对象（只设置非 null 字段）
        User update = new User();
        update.setId(userId);

        if (request.getNickname() != null) {
            update.setNickname(request.getNickname());
        }
        if (request.getAvatar() != null) {
            update.setAvatar(request.getAvatar());
        }
        if (request.getGender() != null) {
            if (!GenderConst.isValid(request.getGender())) {
                throw new UserServiceException("USER0004");
            }
            update.setGender(request.getGender());
        }
        if (request.getAge() != null) {
            update.setAge(request.getAge());
        }

        // 使用 updateById，MyBatisPlus 会自动忽略 null 字段（需确保字段为 null 而不是 ""）
        this.updateById(update);

        // 重新查一次（因为 updateById 不返回新值）
        User updated = this.getById(userId);
        return convertToDTO(updated);
    }

    // ------------------ 工具方法 ------------------

    private UserResponseDTO convertToDTO(User user) {
        if (user == null) return null;

        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setNickname(user.getNickname());
        dto.setAvatar(user.getAvatar());
        dto.setGender(GenderConst.toChinese(user.getGender()));
        dto.setAge(user.getAge());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        return dto;
    }
}




