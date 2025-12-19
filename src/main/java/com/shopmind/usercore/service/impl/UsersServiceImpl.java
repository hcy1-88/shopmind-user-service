package com.shopmind.usercore.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author hcy18
* @description 针对表【sm_users(用户基本信息表)】的数据库操作Service实现
* @createDate 2025-12-12 20:21:36
*/
@Slf4j
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
    public UserResponseDTO getByUserId(Long userId) {
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
        User update = convertToEntity(userId, request);
        // 使用 updateById，MyBatisPlus 会自动忽略 null 字段（需确保字段为 null 而不是 ""）
        this.updateById(update);

        // 重新查一次（因为 updateById 不返回新值）
        User updated = this.getById(userId);
        return convertToDTO(updated);
    }

    @Override
    public void updatePassword(Long userId, String passwordHashed) {
        User user = new User();
        user.setId(userId);
        user.setPassword(passwordHashed);
        this.updateById(user);
    }

    @Override
    public void setPasswordByPhoneNumber(String phoneNumber, String passwordHashed) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .eq(User::getPhoneNumber, phoneNumber)
                .isNull(User::getDeletedAt); // 软删除过滤（重要！）

        User entity = this.getOne(wrapper);
        if (entity == null) {
            log.error("手机号 {} 未注册", phoneNumber);
            throw new UserServiceException("USER0005");
        }
        entity.setPassword(passwordHashed);
        this.updateById(entity);
    }

    // ------------------ 工具方法 ------------------

    /**
     * 返回响应用
     */
    private UserResponseDTO convertToDTO(User user) {
        if (user == null) return null;
        UserResponseDTO dto = new UserResponseDTO();
        BeanUtil.copyProperties(user, dto, CopyOptions.create().setIgnoreNullValue(true));
        dto.setPasswordHash(user.getPassword());
        return dto;
    }

    /**
     * 入库用
     */
    private User convertToEntity(Long userId, UpdateUserRequest request) {
        User user = new User();
        user.setId(userId);
        BeanUtil.copyProperties(
                request,
                user,
                CopyOptions.create().ignoreNullValue()  // 跳过 null 字段
        );
        if (request.getGender() != null) {
            if (!GenderConst.isValid(request.getGender())) {
                throw new UserServiceException("USER0004");
            }
            user.setGender(request.getGender());
        }
        return user;
    }
}




